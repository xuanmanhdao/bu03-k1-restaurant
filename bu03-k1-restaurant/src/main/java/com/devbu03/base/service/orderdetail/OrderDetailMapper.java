package com.devbu03.base.service.orderdetail;

import com.devbu03.base.dto.OrderDetailDTO;
import com.devbu03.base.entity.OrderDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderDetailMapper {

  OrderDetailDTO getOrderDetailDTOFromOrderDetailEntity(OrderDetailEntity orderDetailEntity);

  OrderDetailEntity getOrderDetailEntityFromOrderDetailDTO(OrderDetailDTO orderDetailDTO);

  OrderDetailEntity toEntity(OrderDetailDTO dto);

  OrderDetailDTO toDto(OrderDetailEntity entity);

}
