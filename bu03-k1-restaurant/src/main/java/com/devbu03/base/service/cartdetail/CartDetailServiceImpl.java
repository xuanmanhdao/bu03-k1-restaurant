package com.devbu03.base.service.cartdetail;

import com.devbu03.base.dto.CartDetailDTO;
import com.devbu03.base.entity.CartDetailEntity;
import com.devbu03.base.exception.CommandExceptionBuilder;
import com.devbu03.base.repository.CartDetailRepository;
import com.devbu03.base.response.ErrorCode;
import com.devbu03.base.service.cart.CartService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartDetailServiceImpl implements CartDetailService {

  private final CartDetailRepository cartDetailRepository;
  private final CartDetailMapper cartDetailMapper;
  private final CartService cartService;
  private final MessageSource messageSource;

  /**
   * Thêm món vào giỏ hàng kiểm tra món tồn tại rồi thì món ấy +1
   *
   * @param cartDetailDTO
   * @return
   */
  @Override
  public CartDetailEntity saveNewCartDetail(CartDetailDTO cartDetailDTO, long userId) {
    if (cartService.checkCartExistenceByUser(userId) == 1) {
      if (checkCartDetailExistenceProduct(cartDetailDTO.getProductId(), userId) == 0) {
        cartDetailDTO.setCartId(cartService.getCartIdByUserId(userId));
        CartDetailEntity cartDetailEntity = cartDetailMapper.getCartDetailEntityFromDTO(
            cartDetailDTO);
        return cartDetailRepository.save(cartDetailEntity);
      } else if (checkCartDetailExistenceProduct(cartDetailDTO.getProductId(), userId) == 1) {
        updateCartDetail(1, cartDetailDTO.getProductId(), userId);
        throw CommandExceptionBuilder.exception(
            new ErrorCode(200, messageSource.getMessage("success.productAlreadyExists", null,
                LocaleContextHolder.getLocale())));
      }
    } else {
      cartService.saveNewCart(userId);
      throw CommandExceptionBuilder.exception(
          new ErrorCode(203, messageSource.getMessage("error.cartNotFound", null,
              LocaleContextHolder.getLocale())));
    }
    return null;
  }

  /**
   * thay đổi số lượng món ăn trong sản phẩm
   *
   * @param quantity
   * @param productId
   * @param userId
   */
  @Override
  public void updateCartDetail(int quantity, long productId, long userId) {
    System.out.println("id: " + userId);
    cartDetailRepository.updateQuantity(quantity, productId, userId);
  }

  /**
   * xóa nhiều món trong giỏ bằng listId
   *
   * @param listId
   */
  @Override
  public void delListCartDetailByListId(Long[] listId, long userId) {
    cartDetailRepository.delListCartDetailByListId(new HashSet<>(Arrays.asList(listId)), userId);
  }

  /**
   * kiểm tra món ăn đã tồn tại trong giỏ chưa
   *
   * @param productId
   * @return
   */
  @Override
  public int checkCartDetailExistenceProduct(long productId, long userId) {
    return cartDetailRepository.checkCartDetailExistenceProduct(productId, userId);
  }

  /**
   * lấy các món ăn trong giỏ theo userId
   *
   * @param userId
   * @return
   */
  @Override
  public Set<CartDetailDTO> getAllCartDetailByUserId(long userId) {
    Set<CartDetailDTO> cartlist = new HashSet<>();
    for (Object[] ob : cartDetailRepository.getAllCartDetailByUserId(userId)) {
      CartDetailDTO cartDetailDTO = new CartDetailDTO();
      cartDetailDTO.setCartId(Long.valueOf(ob[2].toString()));
      cartDetailDTO.setId(Long.valueOf(ob[1].toString()));
      cartDetailDTO.setProductName(ob[0].toString());
      cartDetailDTO.setProductId(Long.valueOf(ob[3].toString()));
      cartDetailDTO.setPrice(BigDecimal.valueOf(Long.parseLong((ob[4].toString()))));
      cartDetailDTO.setQuantity(Integer.valueOf(ob[5].toString()));
      cartlist.add(cartDetailDTO);
    }
    return (cartlist);
  }
}
