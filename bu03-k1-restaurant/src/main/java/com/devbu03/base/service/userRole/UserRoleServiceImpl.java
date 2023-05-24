package com.devbu03.base.service.userRole;

import com.devbu03.base.dto.UserRoleDTO;
import com.devbu03.base.entity.UserRoleEntity;
import com.devbu03.base.repository.UserRoleRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

  final UserRoleRepository userRoleRepository;

  final UserRoleMapper userRoleMapper;

  @Override
  public UserRoleDTO add(UserRoleDTO userRoleDTO) {
    UserRoleEntity userRoleEntity = userRoleRepository.save(
        userRoleMapper.getUserRoleEntityFromUserRoleDTO(userRoleDTO));
    return userRoleMapper.getUserRoleDTOFromUserRoleEntity(userRoleEntity);
  }

  @Override
  public UserRoleDTO update(Long id, UserRoleDTO userRoleDTO) {
    UserRoleEntity userRoleEntity = userRoleRepository.findById(id).get();
    userRoleMapper.updateUserRole(userRoleDTO, userRoleEntity);
    userRoleRepository.save(userRoleEntity);
    return userRoleMapper.getUserRoleDTOFromUserRoleEntity(userRoleEntity);
  }

  @Override
  public List<UserRoleDTO> findByUserId(Long userId) {
    List<UserRoleEntity> resultQuery = userRoleRepository.findByUserId(userId);
    List<UserRoleDTO> result = resultQuery.stream()
        .map(userRoleMapper::getUserRoleDTOFromUserRoleEntity)
        .collect(Collectors.toList());
    return result;
  }

  @Override
  public Optional<Long> getIdByUserIdAndRoleId(Long userId, Long roleId) {
    return userRoleRepository.getIdByUserIdAndRoleId(userId, roleId);
  }

  /**
   * Lấy roleId theo UserId
   *
   * @param userId
   * @return
   */

  @Override
  public Optional<Long> getRoleIdByUserId(Long userId) {
    return userRoleRepository.getRoleIdByUserId(userId);
  }

  @Override
  public void remove(Long userRoleId) {
    userRoleRepository.deleteById(userRoleId);
  }

  @Override
  public List<Long> getUserRoleIdByUserId(Long userId) {
    return userRoleRepository.getUserRoleIdByUserId(userId);
  }

  @Override
  public List<Long> getIdFromUserIdWithoutCustomer(Long userId) {
    return userRoleRepository.getIdFromUserIdWithoutCustomer(userId);
  }


}
