package com.devbu03.base.controller;

import com.devbu03.base.dto.OrderDetailDTO;
import com.devbu03.base.response.Response;
import com.devbu03.base.response.ResponseService;
import com.devbu03.base.service.orderdetail.OrderDetailService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order-detail")
public class OrderDetailController extends BaseController<OrderDetailService> {

  public OrderDetailController(OrderDetailService service, ResponseService responseService) {
    super(service, responseService);
  }

  /**
   * Lấy tất cả orderDetail theo orderId
   *
   * @param id
   * @return
   */

  @GetMapping("/customer/get/{id}")
  public Response<List<OrderDetailDTO>> get(@PathVariable("id") Long id) {
    return getResponseService().success(getService().getOrderDetailsByOrderId(id));
  }

}
