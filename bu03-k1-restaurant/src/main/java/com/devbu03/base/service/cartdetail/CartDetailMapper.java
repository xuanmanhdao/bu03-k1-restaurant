package com.devbu03.base.service.cartdetail;


import com.devbu03.base.dto.CartDetailDTO;
import com.devbu03.base.entity.CartDetailEntity;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CartDetailMapper {

  CartDetailDTO getCartDetailDTOFromEntity(CartDetailEntity entity);

  CartDetailEntity getCartDetailEntityFromDTO(CartDetailDTO dto);

  Set<CartDetailDTO> getListCartDetailEntityFromDTO(Set<CartDetailEntity> listEntity);

}
