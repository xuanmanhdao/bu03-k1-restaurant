package com.devbu03.base.repository;

import com.devbu03.base.entity.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

  @Query(value = "SELECT o FROM OrderEntity o WHERE o.createdBy = ?1")
  public List<OrderEntity> getOrderHistoryByUser(Long userId);

  @Query(value = "SELECT SUM(o.TOTAL), TRUNC(o.CREATED_AT) "
      + "FROM ORDERS o "
      + "WHERE o.STATUS = 4 "
      + "GROUP BY TRUNC(o.CREATED_AT) "
      + "HAVING TRUNC(o.CREATED_AT) "
      + "BETWEEN TRUNC(TO_DATE(:createdBegin,'YYYY-MM-DD')) "
      + "AND TRUNC(TO_DATE(:createdEnd,'YYYY-MM-DD'))", nativeQuery = true)
  List<Object[]> getTotalPriceOfDays(
      @Param("createdBegin") String createdBegin,
      @Param("createdEnd") String createdEnd
  );


  @Query("SELECT sum(o.total)"
      + "FROM OrderEntity o "
      + "WHERE MONTH(o.createdAt) = :month AND YEAR(o.createdAt) = :year "
      + "AND o.status = 4")
  Integer getTotalPriceOfMonth(
      @Param("month") Integer month,
      @Param("year") Integer year
  );

  @Query(value = " WITH cte AS ("
      + "SELECT od.PRODUCT_ID, p.NAME, SUM(od.QUANTITY) AS TONG , TRUNC(o.CREATED_AT) AS dates, "
      + "RANK() OVER(ORDER BY SUM(od.QUANTITY) DESC) AS i "
      + "FROM ORDER_DETAIL od "
      + "JOIN ORDERS o ON od.ORDER_ID = o.ID "
      + "JOIN PRODUCT p ON od.PRODUCT_ID = p.ID "
      + "WHERE o.STATUS = 4 "
      + "GROUP BY od.PRODUCT_ID, p.NAME, TRUNC(o.CREATED_AT) "
      + "HAVING TRUNC(o.CREATED_AT) = TRUNC(TO_DATE(:createdAt, 'YYYY-MM-DD'))) "
      + "SELECT c.PRODUCT_ID, c.NAME, c.TONG , c.dates "
      + "FROM cte c "
      + "WHERE c.i = 1", nativeQuery = true)
  List<Object[]> totalOfDays(
      @Param("createdAt") String createdAt
  );

  @Query(value = "WITH cte AS ( "
      + "SELECT od.PRODUCT_ID, p.NAME, SUM(od.QUANTITY) AS TONG , TRUNC(o.CREATED_AT) AS dates, "
      + "RANK() OVER(ORDER BY SUM(od.QUANTITY) DESC) AS i "
      + "FROM ORDER_DETAIL od "
      + "JOIN ORDERS o ON od.ORDER_ID = o.ID "
      + "JOIN PRODUCT p ON od.PRODUCT_ID = p.ID "
      + "WHERE o.STATUS = 4 "
      + "GROUP BY od.PRODUCT_ID, p.NAME, TRUNC(o.CREATED_AT) "
      + "HAVING EXTRACT(MONTH FROM TRUNC(o.CREATED_AT)) = :month AND EXTRACT(YEAR FROM TRUNC(o.CREATED_AT)) = :year "
      + ") "
      + "SELECT c.PRODUCT_ID, c.NAME, c.TONG "
      + "FROM cte c "
      + "WHERE c.i = 1", nativeQuery = true)
  List<Object[]> totalOfMonths(
      @Param("month") Integer month,
      @Param("year") Integer year
  );

  @Query(value = "SELECT EXTRACT(MONTH FROM o.CREATED_AT) AS MONTH_YEAR, SUM(o.TOTAL) "
      + "FROM ORDERS o "
      + "WHERE EXTRACT(YEAR FROM TRUNC(o.CREATED_AT)) = :year "
      + "AND o.STATUS = 4 "
      + "GROUP BY EXTRACT(MONTH FROM o.CREATED_AT)", nativeQuery = true)
  List<Object[]> getTotalPriceOfYear(
      @Param("year") Integer year
  );
}
