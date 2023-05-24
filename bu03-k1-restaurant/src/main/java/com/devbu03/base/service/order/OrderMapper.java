package com.devbu03.base.service.order;

import com.devbu03.base.dto.OrderDTO;
import com.devbu03.base.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

  OrderEntity toEntity(OrderDTO dto);

  OrderDTO toDto(OrderEntity entity);

  OrderDTO getOrderDTOFromOrder(OrderEntity orderEntity);

  OrderEntity getOrderFromOrderDTO(OrderDTO orderDTO);

  void updateOrder(OrderDTO orderDTO, @MappingTarget OrderEntity orderEntity);

}
