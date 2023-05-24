package com.devbu03.base.service.category;

import com.devbu03.base.dto.CategoryDTO;
import com.devbu03.base.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
  public CategoryEntity toEntity(CategoryDTO dto){
    CategoryEntity entity = new CategoryEntity();
    entity.setId(dto.getId());
    entity.setType(dto.getType());
    entity.setDescription(dto.getDescription());
    entity.setCreatedAt(dto.getCreatedAt());
    entity.setUpdatedAt(dto.getUpdatedAt());
    entity.setParentId(dto.getParentId());
    return entity;
  }

  public CategoryDTO toDto(CategoryEntity entity){
    CategoryDTO dto = new CategoryDTO();
    dto.setId(entity.getId());
    dto.setType(entity.getType());
    dto.setDescription(entity.getDescription());
    dto.setCreatedAt(entity.getCreatedAt());
    dto.setUpdatedAt(entity.getUpdatedAt());
    dto.setParentId(entity.getParentId());
    return dto;
  }
}
