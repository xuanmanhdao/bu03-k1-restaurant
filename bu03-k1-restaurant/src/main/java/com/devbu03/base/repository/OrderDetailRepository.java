package com.devbu03.base.repository;

import com.devbu03.base.dto.OrderDetailDTO;
import com.devbu03.base.entity.OrderDetailEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {

  @Query(value =
      "SELECT new com.devbu03.base.dto.OrderDetailDTO(od.id, od.orderId, "
          + "od.quantity, od.price) "
          + "FROM OrderDetailEntity od "
          + "JOIN ProductEntity p "
          + "ON od.productId = p.id "
          + "WHERE od.orderId = ?1")
  List<OrderDetailDTO> getOrderDetailsByOrderId(Long id);

  List<OrderDetailEntity> findAllByOrderId(Long orderId);

  void deleteByOrderId(Long id);
}
