package com.devbu03.base.service.order;

import com.devbu03.base.dto.OrderCTE;
import com.devbu03.base.dto.OrderDTO;
import com.devbu03.base.dto.OrderDetailDTO;
import com.devbu03.base.dto.ProductDTO;
import com.devbu03.base.entity.OrderEntity;
import com.devbu03.base.exception.CommandExceptionBuilder;
import com.devbu03.base.repository.OrderRepository;
import com.devbu03.base.response.ErrorCode;
import com.devbu03.base.service.orderdetail.OrderDetailService;
import com.devbu03.base.service.product.ProductService;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final OrderDetailService orderDetailService;
  private final ProductService productService;
  private final MessageSource messageSource;
  private final OrderMapper orderMapper;

  @Override
  public OrderDTO findInvoiceByOrderId(Long id) {
    OrderEntity orderQuery = orderRepository.findById(id).orElse(null);
    if (orderQuery != null) {
      List<OrderDetailDTO> orderDetailResult = new ArrayList<>();
      List<OrderDetailDTO> orderDetailsQuery = orderDetailService.findAllByOrderId(
          orderQuery.getId());
      for (OrderDetailDTO orderDetails : orderDetailsQuery) {
        ProductDTO productQuery = productService.findById(orderDetails.getProductId());
        orderDetails.setProduct(productQuery);
        orderDetailResult.add(orderDetails);
      }
      OrderDTO result = new OrderDTO();
      result.setId(orderQuery.getId());
      result.setTotal(orderQuery.getTotal());
      result.setStatus(orderQuery.getStatus());
      result.setAddress(orderQuery.getAddress());
      result.setCreatedAt(orderQuery.getCreatedAt());
      result.setCreatedBy(orderQuery.getCreatedBy());
      result.setItems(orderDetailResult);

      return result;
    }
    throw CommandExceptionBuilder.exception(
        new ErrorCode(404, messageSource.getMessage("error.orderNotFound", null,
            LocaleContextHolder.getLocale())));
  }

  @Override
  public void updateStatusOrderByOrderId(Long id, Integer status) {
    OrderEntity orderEntity = orderRepository.findById(id)
        .orElseThrow(
            () -> CommandExceptionBuilder.exception(
                new ErrorCode(404, messageSource.getMessage("error.orderNotFound",
                    null, LocaleContextHolder.getLocale()))));
    orderEntity.setStatus(status);
    orderRepository.save(orderEntity);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public OrderDTO create(OrderDTO dto) {
    dto.setCreatedAt(LocalDateTime.now());
    dto.setStatus(1);
    OrderEntity order = orderMapper.toEntity(dto);
    OrderEntity saved = orderRepository.save(order);
    BigDecimal total = BigDecimal.valueOf(0);
    OrderDetailDTO orderDetailDTO;
    for (OrderDetailDTO orderDetail : dto.getItems()) {
      orderDetailDTO = orderDetailService.create(orderDetail, saved.getId());
      total = total.add(orderDetailDTO.getPrice());
    }
    saved.setTotal(total);
    return this.findOrderById(saved.getId());
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    orderDetailService.deleteByOrderId(id);
    orderRepository.deleteById(id);
  }

  private OrderDTO findOrderById(Long orderId) {
    OrderEntity order = orderRepository.findById(orderId).get();
    List<OrderDetailDTO> orderDetailsDto = orderDetailService.getOrderDetailsByOrderId(orderId);
    OrderDTO orderDTO = orderMapper.toDto(order);
    orderDTO.setItems(orderDetailsDto);
    return orderDTO;
  }

  /**
   * Lấy tất cả lịch sử đặt hàng theo id người dùng
   *
   * @param userId
   * @return
   */

  @Override
  public List<OrderDTO> getOrderHistoryByUser(Long userId) {
    List<OrderEntity> orderEntityList = orderRepository.getOrderHistoryByUser(userId);
    return orderEntityList.stream().map(orderMapper::getOrderDTOFromOrder)
        .collect(Collectors.toList());
  }

  /**
   * Lấy tất cả order, orderdetail, product theo userId
   *
   * @param userId
   * @return
   */

  @Override
  public List<OrderDTO> getAllOrder(Long userId) {
    List<OrderDTO> orders = new ArrayList<>();
    //Lấy tất cả order theo id nguoi dùng
    List<OrderEntity> orderEntityList = orderRepository.getOrderHistoryByUser(userId);

    List<OrderDTO> orderDTOList = orderEntityList.stream().map(orderMapper::getOrderDTOFromOrder)
        .collect(
            Collectors.toList());
    for (OrderDTO orderDTO : orderDTOList) {
      orderDTO.setItems(orderDetailService.getOrderDetailListByOrderId(orderDTO.getId()));
      orders.add(orderDTO);
    }
    return orders;

  }

  @Override
  public List<OrderDTO> getMaxTotalPrice(String createdBegin, String createdEnd) {
    List<Object[]> objects = orderRepository.getTotalPriceOfDays(createdBegin, createdEnd);
    return this.convertToTotalPriceDTO(objects);
  }

  @Override
  public Integer getTotalPriceOfMonth(Integer month, Integer year) {
    return orderRepository.getTotalPriceOfMonth(month, year);
  }

  @Override
  public List<OrderCTE> totalOfDays(String createdAt) {
    List<Object[]> objects = orderRepository.totalOfDays(createdAt);
    return this.convertToOrderCTE(objects);
  }

  @Override
  public List<OrderCTE> totalOfMonths(Integer month, Integer year) {
    List<Object[]> objects = orderRepository.totalOfMonths(month, year);
    return this.convertToOrderCTE(objects);
  }

  @Override
  public List<OrderCTE> getTotalPriceOfYear(Integer year) {
    List<Object[]> objects = orderRepository.getTotalPriceOfYear(year);
    return this.converToOrderCTEToGetPriceOfYear(objects);
  }

  private List<OrderDTO> convertToTotalPriceDTO(List<Object[]> objects) {
    List<OrderDTO> list = new ArrayList<>();
    for (Object[] o : objects) {
      OrderDTO totalPriceDTO = new OrderDTO();
      totalPriceDTO.setTotal(((BigDecimal) o[0]));
      totalPriceDTO.setCreatedAt(((Timestamp) o[1]).toLocalDateTime());
      list.add(totalPriceDTO);
    }
    return list;
  }

  private List<OrderCTE> convertToOrderCTE(List<Object[]> objects) {
    List<OrderCTE> list = new ArrayList<>();
    for (Object[] o : objects) {
      OrderCTE orderCTE = new OrderCTE();
      orderCTE.setProductId((BigDecimal) o[0]);
      orderCTE.setName((String) o[1]);
      orderCTE.setQuantity((Float) o[2]);
      list.add(orderCTE);
    }
    return list;
  }

  private List<OrderCTE> converToOrderCTEToGetPriceOfYear(List<Object[]> objects) {
    List<OrderCTE> list = new ArrayList<>();
    for (Object[] o : objects) {
      OrderCTE orderCTE = new OrderCTE();
      orderCTE.setCreatedAt((BigDecimal) o[0]);
      orderCTE.setTotal((BigDecimal) o[1]);
      list.add(orderCTE);
    }
    return list;
  }

  @Override
  public List<OrderDTO> getListOrder() {
    List<OrderEntity> queryResult = orderRepository.findAll();
    List<OrderDTO> result = queryResult
        .stream().map(orderMapper::getOrderDTOFromOrder)
        .collect(Collectors.toList());
    return result;
  }

  @Override
  public OrderDTO getOrderById(Long id) {
    Optional<OrderEntity> queryResult = orderRepository.findById(id);
    if (queryResult.isPresent()) {
      OrderDTO result = orderMapper.getOrderDTOFromOrder(queryResult.get());
      return result;
    }
    throw CommandExceptionBuilder.exception(
        new ErrorCode(404, messageSource.getMessage("error.orderNotFound", null,
            LocaleContextHolder.getLocale())));
  }
}
