package com.devbu03.base.service.user;

import com.devbu03.base.dto.UserDTO;
import com.devbu03.base.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

  UserDTO getUserDTOFromUserEntity(UserEntity userEntity);

  UserEntity getUserEntityFromUserDTO(UserDTO userDTO);

  void updateUser(UserDTO userDTO, @MappingTarget UserEntity userEntity);

}
