package com.devbu03.base.service.category;

import com.devbu03.base.dto.CategoryDTO;
import java.util.List;

public interface CategoryService {

  public List<CategoryDTO> getAll();

  public CategoryDTO getById(Long id);

  public CategoryDTO create(CategoryDTO dto);

  public void delete(Long id);

  public CategoryDTO update(Long id, CategoryDTO dto);

  List<CategoryDTO> search(String type, String description);

  List<CategoryDTO> getAllDto();
}
