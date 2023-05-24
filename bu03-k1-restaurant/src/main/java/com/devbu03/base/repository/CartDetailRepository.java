package com.devbu03.base.repository;

import com.devbu03.base.entity.CartDetailEntity;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetailEntity, Long> {

  @Transactional
  @Modifying
  @Query(value = "BEGIN"
      + "    update CART_DETAIL cd "
      + "    set QUANTITY= :quantity, "
      + "    PRICE   =(:quantity) * (select p.PRICE "
      + "                            from PRODUCT p "
      + "                            where p.ID = cd.PRODUCT_ID)"
      + "   where cd.PRODUCT_ID= :productId "
      + "         and cd.CART_ID "
      + "                        IN ( SELECT c.ID "
      + "                        FROM CART c "
      + "                        WHERE c.CREATED_BY = :userId "
      + "); "
      + "    UPDATE CART c "
      + "    SET c.TOTAL_PRICE = ( "
      + "        SELECT SUM(cd.PRICE) "
      + "        FROM CART_DETAIL cd "
      + "        WHERE cd.CART_ID = c.ID) "
      + "    WHERE c.CREATED_BY= :userId ; "
      + "    Commit; "
      + " END;", nativeQuery = true)
  void updateQuantity(
      @Param("quantity") int quantity,
      @Param("productId") long productId,
      @Param("userId") long userId);

  @Transactional
  @Modifying
  @Query(value = "BEGIN "
      + "    delete from CART_DETAIL cd  where cd.PRODUCT_ID in(?1)"
      + "         and cd.CART_ID IN ( SELECT c.ID "
      + "                             FROM CART c "
      + "                             WHERE c.CREATED_BY = ?2 "
      + "); "
      + "    UPDATE CART c "
      + "    SET c.TOTAL_PRICE = (SELECT SUM(cd.PRICE) "
      + "                         FROM CART_DETAIL cd "
      + "                         WHERE cd.CART_ID = c.ID) "
      + "    WHERE c.CREATED_BY = ?2 ; "
      + "    COMMIT; "
      + "         END; ", nativeQuery = true)
  void delListCartDetailByListId(Set<Long> ids, long userId);

  @Query(value = "SELECT CASE WHEN EXISTS "
      + " (SELECT 1 FROM cart_detail "
      + " WHERE PRODUCT_ID = :productId and"
      + "      CART_ID in (SELECT c.ID "
      + "                  FROM CART c "
      + "                  WHERE c.CREATED_BY = :userId )) "
      + " THEN 1 ELSE 0 END FROM dual ", nativeQuery = true)
  int checkCartDetailExistenceProduct(
      @Param("productId") long productId,
      @Param("userId") long userId);

  @Query(value = " select p.NAME,cd.* "
      + " from CART_DETAIL cd "
      + "         join CART c on cd.CART_ID = c.ID "
      + "         join PRODUCT p on cd.PRODUCT_ID = p.ID "
      + " where c.CREATED_BY = :userId", nativeQuery = true)
  Set<Object[]> getAllCartDetailByUserId(@Param("userId") long userId);
}
