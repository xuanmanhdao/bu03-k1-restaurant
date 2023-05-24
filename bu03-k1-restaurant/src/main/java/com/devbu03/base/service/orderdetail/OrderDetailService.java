package com.devbu03.base.service.orderdetail;

import com.devbu03.base.dto.OrderDetailDTO;
import java.util.List;

public interface OrderDetailService {

  List<OrderDetailDTO> findAllByOrderId(Long orderId);

  public OrderDetailDTO create(OrderDetailDTO dto, Long orderId);

  void deleteByOrderId(Long id);


  /**
   * Lấy tất cả orderDetail theo id order
   *
   * @param id
   * @return
   */
  List<OrderDetailDTO> getOrderDetailListByOrderId(Long id);

  List<OrderDetailDTO> getOrderDetailsByOrderId(Long id);


}
