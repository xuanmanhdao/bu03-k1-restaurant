package com.devbu03.base.service.userRole;

import java.util.List;
import com.devbu03.base.dto.UserRoleDTO;
import java.util.Optional;

public interface UserRoleService {

  UserRoleDTO add(UserRoleDTO userRoleDTO);

  UserRoleDTO update(Long id, UserRoleDTO userRoleDTO);

  List<UserRoleDTO> findByUserId(Long userId);

  Optional<Long> getIdByUserIdAndRoleId(Long userId, Long roleId);

  /**
   * Lấy roleId theo userId
   *
   * @param userId
   * @return
   */

  public Optional<Long> getRoleIdByUserId(Long userId);

  public void remove(Long userRoleId);

  public List<Long> getUserRoleIdByUserId(Long userId);

  public List<Long> getIdFromUserIdWithoutCustomer(Long userId);

}
