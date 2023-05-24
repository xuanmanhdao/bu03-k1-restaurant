package com.devbu03.base.service.cartdetail;

import com.devbu03.base.dto.CartDetailDTO;
import com.devbu03.base.entity.CartDetailEntity;
import java.util.Set;

public interface CartDetailService {

  /**
   * thêm mới sản phầm vào giỏ hàng
   *
   * @param cartDetailDTO
   * @return
   */
  CartDetailEntity saveNewCartDetail(CartDetailDTO cartDetailDTO, long productId);

  /**
   * chỉnh số lượng.
   *
   * @param quantity
   * @param productId
   * @param userId
   * @return
   */
  void updateCartDetail(int quantity, long productId, long userId);

  /**
   * xóa nhiều cartdetail theo list id
   *
   * @param listId
   */
  void delListCartDetailByListId(Long[] listId,long userId);

  /**
   * kiểm tra sự tồn tại món ăn trong cartdetail
   *
   * @param productId
   * @return
   */
  int checkCartDetailExistenceProduct(long productId,long userId);

  /**
   * lấy các món ăn của giỏ hàng theo userId
   *
   * @param userId
   * @return
   */
  Set<CartDetailDTO> getAllCartDetailByUserId(long userId);
}
