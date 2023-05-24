package com.devbu03.base.service.role;

import com.devbu03.base.dto.RoleDTO;
import com.devbu03.base.entity.RoleEntity;
import java.util.List;

public interface RoleService {

  List<RoleDTO> getAll();

  RoleDTO findById(Long id);

  RoleDTO findByName(String name);

  /**
   * Lấy roleId theo tên quyền
   *
   * @param roleEnum
   * @return
   */

  Long getRoleIdByRoleName(String roleEnum);

  public List<RoleEntity> getRoleFromUserId(Long userId);


}
