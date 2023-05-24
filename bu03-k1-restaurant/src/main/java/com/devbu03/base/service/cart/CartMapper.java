package com.devbu03.base.service.cart;

import com.devbu03.base.dto.CartDTO;
import com.devbu03.base.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CartMapper {

  CartDTO getCartDTOFromEntity(CartEntity entity);

  CartEntity getCartEntityFromDTO(CartDTO dto);

  void updateCartEntityFromDTO(CartDTO dto, @MappingTarget CartEntity entity);

}

