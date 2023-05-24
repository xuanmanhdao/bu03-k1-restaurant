package com.devbu03.base.service.product;

import com.devbu03.base.dto.ProductDTO;
import com.devbu03.base.entity.ProductEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface ProductService {

  BigDecimal updateQuantity(Long id, int quantity);

  ProductDTO findById(Long id);

  Page<ProductDTO> findAllWithRole(Authentication authentication, int page, int size);

  /**
   * Lấy product theo orderDetailId
   *
   * @param orderDetailId
   * @return
   */
  public ProductDTO getProductByOrderDetailId(Long orderDetailId);

  /**
   * thêm một món ăn mới vào menu
   *
   * @param productDTO
   * @return
   */
  ProductEntity saveNewProduct(ProductDTO productDTO);

  /**
   * Sửa một món ăn theo id
   *
   * @param productDTO
   * @param id
   * @return
   */
  ProductEntity updateProduct(ProductDTO productDTO, long id);

  /**
   * Xóa nhiều món ăn cùng một lúc dựa trên list id
   *
   * @param listId
   */
  void delListProductByListId(Long[] listId);

  /**
   * thêm sản phẩm yêu thích
   *
   * @param status
   * @param productId
   */
  void addFavoriteProduct(int status, long productId);

  /**
   * Tìm kiếm nhiều thuộc tính
   *
   * @param productDTO
   * @return
   */
  List<ProductDTO> findProduct(Optional<ProductDTO> productDTO);

  /**
   * Lấy product theo id
   *
   * @param id
   * @return
   */
  ProductDTO getProductById(long id);
}

