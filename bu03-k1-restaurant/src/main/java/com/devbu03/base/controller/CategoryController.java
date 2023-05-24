package com.devbu03.base.controller;

import com.devbu03.base.dto.CategoryDTO;
import com.devbu03.base.response.Response;
import com.devbu03.base.response.ResponseService;
import com.devbu03.base.service.category.CategoryService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController extends BaseController<CategoryService> {

  public CategoryController(CategoryService service, ResponseService responseService) {
    super(service, responseService);
  }

  @GetMapping("/")
  public Response<List<CategoryDTO>> getAll() {
    List<CategoryDTO> list = super.getService().getAll();
    return super.success(list);
  }

  @GetMapping("/all")
  public Response<List<CategoryDTO>> getAllDto() {
    List<CategoryDTO> list = super.getService().getAllDto();
    return super.success(list);
  }

  @GetMapping("/{id}")
  public Response<CategoryDTO> getById(@PathVariable("id") Long id) {
    CategoryDTO dto = super.getService().getById(id);
    return super.success(dto);
  }

  @PostMapping("/owner")
  public Response<CategoryDTO> create(@RequestBody CategoryDTO dto) {
    CategoryDTO result = super.getService().create(dto);
    return super.success(result);
  }

  @DeleteMapping("/owner/{id}")
  public Response delete(@PathVariable("id") Long id) {
    super.getService().delete(id);
    return super.success();
  }

  @PutMapping("/owner/{id}")
  public Response<CategoryDTO> update(@PathVariable("id") Long id,
      @RequestBody CategoryDTO dto) {
    CategoryDTO result = super.getService().update(id, dto);
    return super.success(result);
  }

  @GetMapping("/search")
  public Response<List<CategoryDTO>> search(@RequestParam("type") String type,
      @RequestParam("des") String description) {
    List<CategoryDTO> result = this.getService().search(type, description);
    return super.success(result);
  }
}
