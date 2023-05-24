package com.devbu03.base.service.user;

import com.devbu03.base.dto.UserDTO;
import com.devbu03.base.entity.RoleEntity;
import com.devbu03.base.entity.UserEntity;
import com.devbu03.base.exception.CommandExceptionBuilder;
import com.devbu03.base.repository.UserRepository;
import com.devbu03.base.response.ErrorCode;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import com.devbu03.base.common.enums.RoleEnum;
import com.devbu03.base.dto.UserRoleDTO;
import com.devbu03.base.service.role.RoleService;
import com.devbu03.base.service.userRole.UserRoleService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  final UserRoleService userRoleService;

  final RoleService roleService;
  @Qualifier("passwordEncode")
  final BCryptPasswordEncoder bCryptPasswordEncoder;

  RoleEnum roleEnum = RoleEnum.ROLE_STAFF;

  /**
   * Thêm nhân viên mới
   *
   * @param userDTO
   * @return
   */
  @Override
  public UserDTO addStaff(UserDTO userDTO) {

    if (userRepository.checkEmailExists(userDTO.getEmail()) != null) {
      throw CommandExceptionBuilder.exception(new ErrorCode(502, "Email đã tồn tại"));
    }
    Long roleId = roleService.getRoleIdByRoleName(roleEnum.getName());

    UserEntity userEntity = userMapper.getUserEntityFromUserDTO(userDTO);
    userEntity.setPassword(
        bCryptPasswordEncoder.encode(
            userDTO.getPassword())
    )
    ;
    userEntity.setCreatedAt(
        Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
    userRepository.save(userEntity);

    Long lastUserId = userRepository.getLastUserId().get();

    userRoleService.add(new UserRoleDTO(null, roleId, lastUserId));

    UserDTO rs = userMapper.getUserDTOFromUserEntity(userEntity);
    rs.setRoleName(Collections.singletonList(roleEnum.getName()));
    return rs;
  }

  /**
   * cập nhật thông tin nhân viên
   *
   * @param id
   * @param userDTO
   * @return
   */

  @Override
  public UserDTO updateStaff(Long id, UserDTO userDTO) {
    UserEntity userEntity = userRepository.findById(id).get();
    List<Long> urId = userRoleService.getIdFromUserIdWithoutCustomer(
        userEntity.getId());
    for (Long ur : urId) {
      userRoleService.remove(ur);
    }
    for (String role : userDTO.getRoleName()) {
      //Tìm id của role mới
      Long newRoleId = roleService.getRoleIdByRoleName(role);
      userRoleService.add(new UserRoleDTO(null, newRoleId, userEntity.getId()));
    }
    userEntity.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
    userEntity.setName(userDTO.getName());
    userEntity.setEmail(userDTO.getEmail());
    userEntity.setPhone(userDTO.getPhone());
    userEntity.setUpdatedAt(
        Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
    userMapper.updateUser(userDTO, userEntity);

    userRepository.save(userEntity);
    UserDTO dto = userMapper.getUserDTOFromUserEntity(userEntity);
    dto.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
    return dto;
  }

  @Override
  public List<UserDTO> getAllStaff() {
    return userRepository.getAllStaffs().stream().map(userMapper::getUserDTOFromUserEntity).collect(
        Collectors.toList());
  }

  /**
   * Xóa nhân viên theo id
   *
   * @param id
   */

  @Override
  public void removeStaff(Long id) {
    userRepository.deleteById(id);
    List<Long> userRoleId = userRoleService.getUserRoleIdByUserId(id);
    for (Long ids : userRoleId) {
      userRoleService.remove(ids);
    }
  }

  /**
   * Tìm nhân viên theo id
   *
   * @param id
   * @return
   */

  @Override
  public Optional<UserDTO> getStaffById(Long id) {
    List<String> roleName = new ArrayList<>();
    UserDTO userDTO = userMapper.getUserDTOFromUserEntity(userRepository.findById(id).get());
    for (RoleEntity role : roleService.getRoleFromUserId(id)) {
      roleName.add(role.getName());
    }
    userDTO.setRoleName(roleName);
    return Optional.ofNullable(userDTO);
  }

  @Override
  public UserDTO findByEmail(String email) {
    UserEntity resultQuery = userRepository.findByEmail(email).orElse(null);
    UserDTO result = userMapper.getUserDTOFromUserEntity(resultQuery);
    return result;
  }

  @Override
  public UserDTO saveCustomer(UserDTO userDTO) {
    UserEntity userEntity = userMapper.getUserEntityFromUserDTO(userDTO);
    UserEntity resultQuery = userRepository.save(userEntity);
    UserDTO result = userMapper.getUserDTOFromUserEntity(resultQuery);
    return result;
  }


}
