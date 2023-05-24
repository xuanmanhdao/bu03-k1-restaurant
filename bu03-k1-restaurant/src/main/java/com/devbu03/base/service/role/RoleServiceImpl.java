package com.devbu03.base.service.role;

import com.devbu03.base.dto.RoleDTO;
import com.devbu03.base.entity.RoleEntity;
import com.devbu03.base.repository.RoleRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  private final RoleMapper roleMapper;

  @Override
  public List<RoleDTO> getAll() {
    return roleRepository.findAll().stream().map(roleMapper::getRoleDTOFromRoleEntity).collect(
        Collectors.toList());
  }

  @Override
  public RoleDTO findById(Long id) {
    RoleEntity resultQuery = roleRepository.findById(id).get();
    RoleDTO result = roleMapper.getRoleDTOFromRoleEntity(resultQuery);
    return result;
  }

  @Override
  public RoleDTO findByName(String name) {
    RoleEntity resultQuery = roleRepository.findByName(name).get();
    RoleDTO result = roleMapper.getRoleDTOFromRoleEntity(resultQuery);
    return result;
  }


  /**
   * Lấy id của role theo tên quyền
   *
   * @param roleEnum
   * @return
   */

  @Override
  public Long getRoleIdByRoleName(String roleEnum) {
    return roleRepository.getRoleIdByRoleName(roleEnum).get();
  }

  @Override
  public List<RoleEntity> getRoleFromUserId(Long userId) {
    return roleRepository.getRoleFromUserId(userId);
  }

}
