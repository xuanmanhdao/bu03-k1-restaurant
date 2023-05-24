package com.devbu03.base.service.order;

import com.devbu03.base.dto.OrderCTE;
import com.devbu03.base.dto.OrderDTO;
import java.util.List;

public interface OrderService {

  OrderDTO findInvoiceByOrderId(Long id);

  void updateStatusOrderByOrderId(Long id, Integer status);

  public OrderDTO create(OrderDTO dto);

  public void deleteById(Long id);

  /**
   * Lấy tất cả dữ liệu đặt hàng theo id người dùng
   *
   * @param userId
   * @return
   */
  public List<OrderDTO> getOrderHistoryByUser(Long userId);

  /**
   * Lấy tất cả order theo người dùng ,trả về orderdetail, product tương ứng
   *
   * @param userId
   * @return
   */
  public List<OrderDTO> getAllOrder(Long userId);


  List<OrderDTO> getMaxTotalPrice(String createdBegin, String createdEnd);

  Integer getTotalPriceOfMonth(Integer month, Integer year);

  List<OrderCTE> totalOfDays(String createdAt);

  List<OrderCTE> totalOfMonths(Integer month, Integer year);

  List<OrderCTE> getTotalPriceOfYear(Integer year);

  List<OrderDTO> getListOrder();

  OrderDTO getOrderById(Long id);
}
