package com.devbu03.base.service.orderdetail;
import com.devbu03.base.dto.OrderDetailDTO;
import com.devbu03.base.entity.OrderDetailEntity;
import com.devbu03.base.repository.OrderDetailRepository;
import com.devbu03.base.service.product.ProductService;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.devbu03.base.dto.ProductDTO;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

  private static Map<Long, Object> lockMap = new HashMap<>();
  private final ProductService productService;
  private final OrderDetailRepository orderDetailRepository;
  private final OrderDetailMapper mapper;
  private final OrderDetailMapper orderDetailMapper;

  private static Object getLockForId(Long id) {
    if (!lockMap.containsKey(id)) {
      lockMap.put(id, new Object());
    }
    return lockMap.get(id);
  }

  @Override
  public OrderDetailDTO create(OrderDetailDTO dto, Long orderId) {
    Long id = dto.getProductId();
    Integer quantity = dto.getQuantity();
    Object lock = OrderDetailServiceImpl.getLockForId(id);
    BigDecimal price = null;
    synchronized (lock) {
      //giam quantity o product tuong ung
      price = productService.updateQuantity(id, quantity);
    }
    OrderDetailEntity entity = this.toEntity(dto, orderId);
    entity.setPrice(price.multiply(new BigDecimal(quantity)));
    OrderDetailEntity saved = orderDetailRepository.save(entity);
    return mapper.toDto(saved);
  }

  @Override
  public List<OrderDetailDTO> getOrderDetailsByOrderId(Long orderId) {
    List<OrderDetailEntity> orderDetails = orderDetailRepository.findAllByOrderId(orderId);
    List<OrderDetailDTO> listDto = orderDetails.stream().map(entity -> mapper.toDto(entity))
        .collect(Collectors.toList());
    return listDto;
  }

  @Override
  public void deleteByOrderId(Long id) {
    orderDetailRepository.deleteByOrderId(id);
  }

  private OrderDetailEntity toEntity(OrderDetailDTO dto, Long orderId) {
    OrderDetailEntity entity = new OrderDetailEntity();
    entity.setOrderId(orderId);
    entity.setPrice(dto.getPrice());
    entity.setQuantity(dto.getQuantity());
    entity.setProductId(dto.getProductId());
    return entity;
  }

  @Override
  public List<OrderDetailDTO> findAllByOrderId(Long orderId) {
    List<OrderDetailEntity> resultQuery = orderDetailRepository.findAllByOrderId(orderId);
    List<OrderDetailDTO> result = resultQuery.stream()
        .map(orderDetailMapper::getOrderDetailDTOFromOrderDetailEntity)
        .collect(Collectors.toList());

    return result;
  }

  /**
   * Lấy tất cả orderDetail theo orderId
   *
   * @param id
   * @return
   */

  @Override
  public List<OrderDetailDTO> getOrderDetailListByOrderId(Long id) {
    List<OrderDetailDTO> orderDetailDTOList = new ArrayList<>();
    for (OrderDetailDTO orderDetailDTO : orderDetailRepository.getOrderDetailsByOrderId(id)) {
      ProductDTO dto = productService.getProductByOrderDetailId(orderDetailDTO.getId());
      orderDetailDTO.setProduct(dto);
      orderDetailDTOList.add(orderDetailDTO);
    }
    return orderDetailDTOList;
  }
}
