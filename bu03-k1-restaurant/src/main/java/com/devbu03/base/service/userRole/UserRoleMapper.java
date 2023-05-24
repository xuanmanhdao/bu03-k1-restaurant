package com.devbu03.base.service.userRole;

import com.devbu03.base.dto.UserRoleDTO;
import com.devbu03.base.entity.UserRoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserRoleMapper {

  UserRoleEntity getUserRoleEntityFromUserRoleDTO(UserRoleDTO userRoleDTO);

  UserRoleDTO getUserRoleDTOFromUserRoleEntity(UserRoleEntity userRoleEntity);

  void updateUserRole(UserRoleDTO userRoleDTO, @MappingTarget UserRoleEntity userRoleEntity);

}
