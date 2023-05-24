package com.devbu03.base.controller;

import com.devbu03.base.auth.AuthenticationDetailResponse;
import com.devbu03.base.dto.ProductDTO;
import com.devbu03.base.response.Response;
import com.devbu03.base.response.ResponseService;
import com.devbu03.base.service.product.ProductService;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/api/v1/product")
public class ProductController extends BaseController<ProductService> {

  public ProductController(ProductService service, ResponseService responseService) {
    super(service, responseService);
  }

  @GetMapping("/")
  public Response<Page<ProductDTO>> findAllWithRole(Authentication authentication,
      @RequestParam int page, @RequestParam int size) {
    return super.success(getService().findAllWithRole(authentication, page, size));
  }

  /**
   * thêm món ăn mới vào menu http://localhost:8080/api/v1/product/save
   *
   * @param productDTO
   * @return
   */
  @PostMapping("/owner/save")
  ResponseEntity<?> saveNewProduct(@RequestBody ProductDTO productDTO,
      Authentication authentication) {
    productDTO.setCreatedBy(((AuthenticationDetailResponse) authentication.getDetails()).getId());
    return success(getService().saveNewProduct(productDTO));
  }

  /**
   * Sửa món ăn theo id http://localhost:8080/api/v1/product/89
   *
   * @param productDTO
   * @param id
   * @return
   */
  @PutMapping("/staff/{id}")
  ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable("id") long id,
      Authentication authentication) {
    productDTO.setCreatedBy(((AuthenticationDetailResponse) authentication.getDetails()).getId());
    return success(getService().updateProduct(productDTO, id));
  }


  /**
   * Xóa nhiều món ăn cùng lúc dựa trên list ids http://localhost:8080/api/v1/product?ids=87,94
   *
   * @param ids
   * @return
   */
  @DeleteMapping("/owner")
  public ResponseEntity<?> delListProductByListId(@RequestParam("ids") Long[] ids) {
    getService().delListProductByListId(ids);
    return success();
  }

  /**
   * thêm sản phẩm yêu thích
   *
   * @param productId
   * @return
   */
  @PutMapping("/owner")
  public ResponseEntity<?> addFavoriteProduct(@RequestParam("status") int status,
      @RequestParam("productId") long productId) {
    getService().addFavoriteProduct(status, productId);
    return success();
  }

  /**
   * lấy product theo id
   *
   * @param id
   * @return
   */
  @GetMapping("/staff/get-product{id}")
  public ResponseEntity<?> getProductbyId(@PathVariable("id") long id) {
    return success(getService().getProductById(id));
  }

  /**
   * tìm kiếm món ăn theo điều kiện loại bỏ các điều kiện null.
   *
   * @param productDTO
   * @return
   */

  @GetMapping("/staff/find/")
  public ResponseEntity<?> findProduct(@RequestBody ProductDTO productDTO) {
    Optional<ProductDTO> productDTOOptional = Optional.ofNullable(productDTO);
    return success(getService().findProduct(productDTOOptional));
  }
}
