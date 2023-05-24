package com.devbu03.base.controller;

import com.devbu03.base.auth.AuthenticationDetailResponse;
import com.devbu03.base.response.ResponseService;
import com.devbu03.base.service.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController extends BaseController<CartService> {

  public CartController(CartService service, ResponseService responseService) {
    super(service, responseService);
  }

  /**
   * thêm giỏ mới vào cho user chưa có http://localhost:8080/api/v1/cart/save
   *
   * @return
   */
  @PostMapping("/save")
  ResponseEntity<?> saveNewCart(Authentication authentication) {
    AuthenticationDetailResponse details = (AuthenticationDetailResponse) authentication.getDetails();
    return success(getService().saveNewCart(details.getId()));
  }
}
