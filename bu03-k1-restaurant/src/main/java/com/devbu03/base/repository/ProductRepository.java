package com.devbu03.base.repository;

import com.devbu03.base.dto.ProductDTO;
import com.devbu03.base.entity.ProductEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  @Query("SELECT new com.devbu03.base.dto.ProductDTO(p.id, p.image, p.name, p.price, p.quantity) "
      + "FROM ProductEntity p "
      + "ORDER BY p.id DESC")
  List<ProductDTO> getProductEntitiesByImageAndNameAndPrice();

  @Query(value =
      "SELECT new com.devbu03.base.dto.ProductDTO"
          + "(p.id, p.status, p.image, p.name, p.price, p.quantity, p.description, p.totalSold) "
          + "FROM ProductEntity p "
          + "JOIN OrderDetailEntity od "
          + "ON p.id = od.productId "
          + "WHERE od.id = ?1")
  public ProductDTO getProductByOrderDetailId(Long orderDetailId);

  @Modifying
  @Query("delete from ProductEntity p where p.id in ?1")
  void delListProductByListId(Set<Long> ids);

  @Query(value = "update PRODUCT set STATUS= ?1 where ID= ?2", nativeQuery = true)
  void addFavoriteProduct(int status, long productId);

  @Query(value = "select * from PRODUCT p where "
      + "(?1 is null or p.CATEGORY_ID = ?1 ) and "
      + "(?2 is null or p.STATUS = ?2) and "
      + "(?3 is null or p.NAME like '%' || ?3 || '%' ) and "
      + "(?4 is null or p.PRICE = ?4 ) and "
      + "(?5 is null or p.TOTAL_SOLD = ?5 ) and "
      + "(?6 is null or p.DESCRIPTION = '%' || ?6 || '%'  ) and "
      + "(?7 is null or p.QUANTITY = ?7 ) and "
      + "(?8 is null or p.CREATED_AT = ?8 ) and "
      + "(?9 is null or p.UPDATED_AT = ?9 ) and "
      + "(?10 is null or p.CREATED_BY = ?10 ) and "
      + "(?11 is null or p.UPDATED_BY = ?11 ) "
      , nativeQuery = true)
  List<ProductEntity> findProduct(
      long categoryId, int status, String name,
      BigDecimal price, int totalSold, String description,
      int Quantity, LocalDateTime CreatedAt,
      LocalDateTime UpdateAt, long CreatedBy, long UpdateBy);
}

