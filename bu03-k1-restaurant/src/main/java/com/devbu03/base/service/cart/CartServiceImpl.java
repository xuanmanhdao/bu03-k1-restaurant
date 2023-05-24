package com.devbu03.base.service.cart;

import com.devbu03.base.entity.CartEntity;
import com.devbu03.base.repository.CartRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

  private final CartRepository cartRepository;

  /**
   * Tạo một cart mới khi user chưa tồn tại. Mỗi user chỉ có 1 cart
   *
   * @param userId
   * @return
   */
  @Override
    public CartEntity saveNewCart(long userId) {
    if (checkCartExistenceByUser(userId) == 0) {
      CartEntity cartEntity = new CartEntity();
      cartEntity.setCreatedBy(userId);
      cartEntity.setTotalPrice(BigDecimal.valueOf(0));
      return cartRepository.save(cartEntity);
    }
    return null;
  }

  /**
   * Kiểm tra xem user đã có cart tồn tại chưa
   *
   * @param userId
   * @return
   */
  @Override
  public int checkCartExistenceByUser(long userId) {
    return cartRepository.checkCartExistenceByUser(userId);
  }

  /**
   * lấy id cart
   * @param userId
   * @return
   */
  @Override
  public long getCartIdByUserId(long userId) {
    return cartRepository.getCartIdByUserId(userId);
  }
}
