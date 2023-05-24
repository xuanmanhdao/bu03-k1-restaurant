package com.devbu03.base.controller;

import com.devbu03.base.auth.AuthenticationDetailResponse;
import com.devbu03.base.dto.CartDetailDTO;
import com.devbu03.base.response.ResponseService;
import com.devbu03.base.service.cartdetail.CartDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart-detail")
public class CartDetailController extends BaseController<CartDetailService> {

  public CartDetailController(CartDetailService service, ResponseService responseService) {
    super(service, responseService);
  }

  /**
   * thêm món ăn mới vào giỏ hàng http://localhost:8080/api/v1/cart-detail/save
   *
   * @param cartDetailDTO
   * @return
   */
  @PostMapping("/save")
  public ResponseEntity<?> saveNewCartDetail(@RequestBody CartDetailDTO cartDetailDTO,
      Authentication authentication) {
    AuthenticationDetailResponse details = (AuthenticationDetailResponse) authentication.getDetails();
    return success(getService().saveNewCartDetail(cartDetailDTO, details.getId()));
  }

  /**
   * thay đổi số lượng món ăn trong giỏ
   *
   * @param quantity
   * @param productId
   * @return
   */

  @PutMapping("/update-quantity")
  public ResponseEntity<?> updateQuantityCartDetail(
      @RequestParam("quantity") int quantity,
      @RequestParam("productId") long productId,
      Authentication authentication) {
    AuthenticationDetailResponse details = (AuthenticationDetailResponse) authentication.getDetails();
    getService().updateCartDetail(quantity, productId, details.getId());
    return success();
  }

  /**
   * Xóa nhiều món ăn trong giỏ cùng lúc dựa trên list ids http://localhost:8080/api/v1/product?ids=87,94
   *
   * @param ids
   * @return
   */
  @DeleteMapping("")
  public ResponseEntity<?> delListCartDetailByListId(@RequestParam("ids") Long[] ids,
      Authentication authentication) {
    AuthenticationDetailResponse details = (AuthenticationDetailResponse) authentication.getDetails();
    getService().delListCartDetailByListId(ids, details.getId());
    return success();
  }

  /**
   * xem giỏ hàng
   *
   * @return
   */
  @GetMapping("/get-cart-detail")
  public ResponseEntity<?> getAllCartDetailByUserId(Authentication authentication) {
    AuthenticationDetailResponse details = (AuthenticationDetailResponse) authentication.getDetails();
    return success(getService().getAllCartDetailByUserId(details.getId()));
  }

}
