package com.devbu03.base.service.cart;

import com.devbu03.base.entity.CartEntity;

public interface CartService {

  /**
   * tạo cart mới khi chưa có. mỗi user chỉ tồn tại 1 cart xuyên suốt.
   *
   * @param userId
   * @return
   */
  CartEntity saveNewCart(long userId);

  /**
   * Kiểm tra user đang đăng nhập đã có cart chưa.
   *
   * @param userId
   * @return
   */
  int checkCartExistenceByUser(long userId);

  /**
   * lấy id cart bằng userID
   * @param userId
   * @return
   */
  long getCartIdByUserId(long userId);
}
