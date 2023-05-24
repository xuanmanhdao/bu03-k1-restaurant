package com.devbu03.base.service.product;

import com.devbu03.base.common.Helper;
import com.devbu03.base.dto.ProductDTO;
import com.devbu03.base.entity.ProductEntity;
import com.devbu03.base.repository.ProductRepository;
import com.devbu03.base.exception.CommandExceptionBuilder;
import com.devbu03.base.response.ErrorCode;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.devbu03.base.auth.AuthenticationDetailResponse;
import com.devbu03.base.common.enums.RoleEnum;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;
  private final ProductMapper mapper;

  @Override
  public Page<ProductDTO> findAllWithRole(Authentication authentication, int page, int size) {
    List<ProductDTO> result = null;
    if (authentication == null) {
      result = repository.getProductEntitiesByImageAndNameAndPrice();
    } else {
      AuthenticationDetailResponse authenticationDetailResponse = (AuthenticationDetailResponse) authentication.getDetails();
      Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
      List<String> listRole = authorities.stream().map(au -> au.getAuthority())
          .collect(Collectors.toList());
      List<ProductEntity> list = null;
      if (listRole.contains(RoleEnum.ROLE_OWNER.getName()) || listRole.contains(
          RoleEnum.ROLE_STAFF.getName())) {
        list = repository.findAll();
        result = list.stream()
            .map(mapper::getProductDTOFromEntity).collect(Collectors.toList());
      } else {
        result = repository.getProductEntitiesByImageAndNameAndPrice();
      }
    }
    Pageable pageable = PageRequest.of(page - 1, size);
    int start = Math.toIntExact(pageable.getOffset());
    int end = (start + pageable.getPageSize()) > result.size() ? result.size()
        : (start + pageable.getPageSize());
    final Page<ProductDTO> dtoPage = new PageImpl<>(result.subList(start, end), pageable,
        result.size());
    return dtoPage;
  }

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final MessageSource messageSource;

  @Override
  public ProductDTO findById(Long id) {
    ProductEntity resultQuery = productRepository.findById(id).get();
    ProductDTO result = productMapper.getProductDTOFromEntity(resultQuery);

    return result;
  }

  @Override
  @Transactional
  public BigDecimal updateQuantity(Long id, int quantity) {
    if (quantity <= 0) {
      throw CommandExceptionBuilder.exception(
          new ErrorCode(102, messageSource.getMessage("error.product.quantity",
              new Object[]{id}, LocaleContextHolder.getLocale())));
    }
    ProductEntity productEntity = productRepository.findById(id)
        .orElseThrow(() -> CommandExceptionBuilder.exception(
            new ErrorCode(100, messageSource.getMessage("error.product.notFoundId",
                new Object[]{id}, LocaleContextHolder.getLocale()))));
    Integer quantityActual = productEntity.getQuantity();
    if (quantityActual < quantity) {
      throw CommandExceptionBuilder.exception(new ErrorCode(101,
          messageSource.getMessage("error.product.quantityNotEnough",
              new Object[]{productEntity.getName()}, LocaleContextHolder.getLocale())));
    } else {
      productEntity.setQuantity(quantityActual - quantity);
      productRepository.save(productEntity);
    }
    return productEntity.getPrice();
  }

  /**
   * Lấy tất cả Product theo orderDetailId
   *
   * @param orderDetailId
   * @return
   */

  @Override
  public ProductDTO getProductByOrderDetailId(Long orderDetailId) {
    return productRepository.getProductByOrderDetailId(orderDetailId);
  }

  /**
   * Thêm mới một món ăn
   *
   * @param productDTO
   * @return
   */
  @Override
  public ProductEntity saveNewProduct(ProductDTO productDTO) {
    ProductEntity productEntity = productMapper.getProductEntityFromDTO(productDTO);
    productEntity.setCreatedAt(Helper.getLocalDateTimeNow());
    return productRepository.save(productEntity);
  }

  /**
   * Sửa món ăn dựa theo id
   *
   * @param productDTO
   * @param id
   * @return
   */
  @Override
  public ProductEntity updateProduct(ProductDTO productDTO, long id) {
    ProductEntity productEntity = productRepository.findById(id).get();
    productEntity.setUpdatedAt(Helper.getLocalDateTimeNow());
    productMapper.updateProductEntityFromDTO(productDTO, productEntity);
    return productRepository.save(productEntity);
  }

  /**
   * Xóa nhiều món ăn dựa trên list id
   *
   * @param ids
   */
  @Override
  @Transactional
  public void delListProductByListId(Long[] ids) {
    productRepository.delListProductByListId(new HashSet<>(Arrays.asList(ids)));
  }

  /**
   * thêm sản phẩm yêu thích
   *
   * @param status
   * @param productId
   */
  @Override
  public void addFavoriteProduct(int status, long productId) {
    productRepository.addFavoriteProduct(status, productId);
  }

  /**
   * lấy thông tin product
   *
   * @param id
   * @return
   */
  @Override
  public ProductDTO getProductById(long id) {
    ProductEntity productEntity = productRepository.findById(id).get();
    return productMapper.getProductDTOFromEntity(productEntity);
  }

  /**
   * tìm kiếm với nhiều điều kiện trc đó kiểm tra xem thuộc tính nào null sẽ bị bỏ qua
   *
   * @param productDTO
   * @return
   */
  @Override
  public List<ProductDTO> findProduct(Optional<ProductDTO> productDTO) {
    Long categoryId = productDTO.map(ProductDTO::getCategoryId).orElse(null);
    Integer status = productDTO.map(ProductDTO::getStatus).orElse(null);
    String name = productDTO.map(ProductDTO::getName).orElse(null);
    BigDecimal price = productDTO.map(ProductDTO::getPrice).orElse(null);
    Integer totalSold = productDTO.map(ProductDTO::getTotalSold).orElse(null);
    String description = productDTO.map(ProductDTO::getDescription).orElse(null);
    Integer quantity = productDTO.map(ProductDTO::getQuantity).orElse(null);
    LocalDateTime createdAt = productDTO.map(ProductDTO::getCreatedAt).orElse(null);
    LocalDateTime updatedAt = productDTO.map(ProductDTO::getUpdatedAt).orElse(null);
    Long createdBy = productDTO.map(ProductDTO::getCreatedBy).orElse(null);
    Long updatedBy = productDTO.map(ProductDTO::getUpdatedBy).orElse(null);

    List<ProductEntity> productEntity = productRepository.findProduct(
        categoryId, status, name, price, totalSold, description,
        quantity, createdAt, updatedAt, createdBy, updatedBy);
    return productMapper.getListProductDTOFromEntity(productEntity);
  }
}

