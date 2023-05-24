package com.devbu03.base.service.role;

import com.devbu03.base.dto.RoleDTO;
import com.devbu03.base.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {

  RoleDTO getRoleDTOFromRoleEntity(RoleEntity roleEntity);

  RoleEntity getRoleEntityFromRoleDTO(RoleDTO roleDTO);

  void updateRole(RoleDTO roleDTO, @MappingTarget RoleEntity roleEntity);
}
