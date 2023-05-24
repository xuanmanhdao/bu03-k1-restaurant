package com.devbu03.base.service.category;

import com.devbu03.base.dto.CategoryDTO;
import com.devbu03.base.entity.CategoryEntity;
import com.devbu03.base.exception.CommandExceptionBuilder;
import com.devbu03.base.repository.CategoryRepository;
import com.devbu03.base.response.ErrorCode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper mapper;
  private final MessageSource messageSource;

  @Override
  public List<CategoryDTO> getAll() {
    List<CategoryEntity> categoryEntities = categoryRepository.findAll();
    return this.getDtoFromEntity(categoryEntities);
  }

  @Override
  public CategoryDTO getById(Long id) {
    List<CategoryEntity> entities = categoryRepository.getCategoryById(id);
    Map<Long, CategoryDTO> map = new HashMap<>();
    CategoryDTO root = null;
    for (CategoryEntity entity : entities) {
      map.put(entity.getId(), mapper.toDto(entity));
    }
    for (CategoryEntity entity : entities) {
      if (entity.getParentId() == null || entity.getId() == id) {
        root = map.get(entity.getId());
      } else {
        CategoryDTO parent = map.get(entity.getParentId());
        CategoryDTO dto = map.get(entity.getId());
        parent.getListChild().add(dto);
      }
    }
    return root;
  }

  @Override
  public CategoryDTO create(CategoryDTO dto) {
    Long categoryId = createCategory(dto, dto.getParentId());
    return this.getById(categoryId);
  }

  private Long createCategory(CategoryDTO dto, Long parentId) {
    dto.setParentId(parentId);
    dto.setCreatedAt(new Date());
    CategoryEntity saved = categoryRepository.save(mapper.toEntity(dto));
    Long id = saved.getId();
    for (CategoryDTO category : dto.getListChild()) {
      createCategory(category, id);
    }
    return id;
  }


  @Override
  public void delete(Long id) {
    List<CategoryEntity> categories = categoryRepository.getCategoryById(id);
    for (CategoryEntity category : categories) {
      categoryRepository.delete(category);
    }
  }

  @Override
  public CategoryDTO update(Long id, CategoryDTO dto) {
    CategoryEntity category = categoryRepository.findById(id).orElse(null);
    if (category == null) {
      throw CommandExceptionBuilder.exception(
          new ErrorCode(HttpStatus.NOT_FOUND.value(),
              messageSource.getMessage("error.categoryNotFound", null,
                  LocaleContextHolder.getLocale())));
    }
    category.setType(dto.getType());
    category.setDescription(dto.getDescription());
    category.setUpdatedAt(new Date());
    category.setParentId(dto.getParentId());
    CategoryEntity saved = categoryRepository.save(category);
    return mapper.toDto(saved);
  }

  @Override
  public List<CategoryDTO> search(String type, String description) {
    List<CategoryEntity> categoryEntities = categoryRepository.getCategoryByLikeTypeAndDes(type,
        description);
    List<CategoryDTO> result = this.getDtoFromEntityForSearch(categoryEntities, type, description);
    return result;
  }

  @Override
  public List<CategoryDTO> getAllDto() {
    List<CategoryEntity> list = categoryRepository.findAll();
    List<CategoryDTO> result = list.stream().map(mapper::toDto).collect(Collectors.toList());
    return result;
  }

  private List<CategoryDTO> getDtoFromEntity(List<CategoryEntity> entities) {
    Map<Long, CategoryDTO> map = new HashMap<>();
    for (CategoryEntity entity : entities) {
      CategoryDTO dto = mapper.toDto(entity);
      map.put(entity.getId(), dto);
    }
    //buid tree
    List<CategoryDTO> roots = new ArrayList<>();
    CategoryDTO categoryDTO = null;
    for (CategoryEntity entity : entities) {
      if (entity.getParentId() == null) {
        categoryDTO = map.get(entity.getId());
        roots.add(categoryDTO);
      } else {
        CategoryDTO categoryDtoParent = map.get(entity.getParentId());
        //truong hop: co parentId nhung khong co category tuong ung voi parentId
        if (categoryDtoParent == null) {
          categoryDTO = map.get(entity.getId());
          roots.add(categoryDTO);
          continue;
        }
        categoryDTO = map.get(entity.getId());
        categoryDtoParent.getListChild().add(categoryDTO);
      }
    }
    return roots;
  }


  private List<CategoryDTO> getDtoFromEntityForSearch(List<CategoryEntity> entities, String type,
      String description) {
    Map<Long, CategoryDTO> map = new HashMap<>();
    for (CategoryEntity entity : entities) {
      CategoryDTO dto = mapper.toDto(entity);
      map.put(entity.getId(), dto);
    }
    //buid tree
    List<CategoryDTO> roots = new ArrayList<>();
    CategoryDTO categoryDTO = null;
    for (CategoryEntity entity : entities) {
      if (entity.getParentId() == null) {
        categoryDTO = map.get(entity.getId());
        roots.add(categoryDTO);
      } else if (this.checkResultSearch(entity, type, description)) {
        categoryDTO = map.get(entity.getId());
        roots.add(categoryDTO);
        CategoryDTO dtoCopy = this.cloneCategoryDto(categoryDTO);
        Optional.ofNullable(map.get(categoryDTO.getParentId()))
            .ifPresent(parent -> parent.getListChild().add(dtoCopy));
      } else {
        CategoryDTO categoryDtoParent = map.get(entity.getParentId());
        //truong hop: co parentId nhung khong co category tuong ung voi parentId
        if (categoryDtoParent == null) {
          categoryDTO = map.get(entity.getId());
          roots.add(categoryDTO);
          continue;
        }
        categoryDTO = map.get(entity.getId());
        categoryDtoParent.getListChild().add(categoryDTO);
      }
    }
    return roots;
  }

  private boolean checkResultSearch(CategoryEntity category, String type, String description) {
    Optional.ofNullable(type).orElse("");
    Optional.ofNullable(description).orElse("");
    if (category.getType().contains(type) && category.getDescription().contains(description)) {
      return true;
    }
    return false;
  }

  private CategoryDTO cloneCategoryDto(CategoryDTO category) {
    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setId(category.getId());
    categoryDTO.setType(category.getType());
    categoryDTO.setDescription(category.getDescription());
    categoryDTO.setCreatedAt(category.getCreatedAt());
    categoryDTO.setUpdatedAt(category.getUpdatedAt());
    categoryDTO.setParentId(category.getParentId());
    return categoryDTO;
  }
}
