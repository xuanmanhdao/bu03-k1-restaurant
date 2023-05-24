package com.devbu03.base.service.auth;

import com.devbu03.base.dto.UserDetailSecurityDTO;
import com.devbu03.base.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthenticationMapper {

  UserEntity getUserEntityFromUserDetailSecurityDTO(UserDetailSecurityDTO userDetailSecurityDTO);
}
