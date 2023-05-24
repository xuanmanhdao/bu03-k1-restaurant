package com.devbu03.base.service.product;

import com.devbu03.base.dto.ProductDTO;
import com.devbu03.base.entity.ProductEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
  
  ProductEntity getProductEntityFromDTO(ProductDTO dto);

  ProductDTO getProductDTOFromEntity(ProductEntity entity);

  void updateProductEntityFromDTO(ProductDTO dto, @MappingTarget ProductEntity entity);

  List<ProductDTO> getListProductDTOFromEntity(List<ProductEntity> optionalEntity);
}
