package com.devbu03.base.service.auth;

import com.devbu03.base.common.Helper;
import com.devbu03.base.common.enums.RoleEnum;
import com.devbu03.base.dto.RoleDTO;
import com.devbu03.base.dto.UserDTO;
import com.devbu03.base.dto.UserDetailSecurityDTO;
import com.devbu03.base.dto.UserRoleDTO;
import com.devbu03.base.exception.CommandExceptionBuilder;
import com.devbu03.base.response.ErrorCode;
import com.devbu03.base.service.cart.CartService;
import com.devbu03.base.service.role.RoleService;
import com.devbu03.base.service.user.UserService;
import com.devbu03.base.service.userRole.UserRoleService;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationQueryImpl implements AuthenticationQuery {

  private final UserService userService;

  private final UserRoleService userRoleService;

  private final RoleService roleService;

  private final MessageSource messageSource;

  private final CartService cartService;

  @Override
  public Optional<UserDetailSecurityDTO> findByEmail(String email) {
    UserDTO userQuery = userService.findByEmail(email);

    if (userQuery == null) {
      throw new InternalAuthenticationServiceException(
          messageSource.getMessage("error.userNotFound", null, LocaleContextHolder.getLocale()));
    }

    List<UserRoleDTO> userRoleQuery = userRoleService.findByUserId(userQuery.getId());

    Set<RoleDTO> roleDTOS = new HashSet<>();
    for (UserRoleDTO userRoleDTO : userRoleQuery) {
      RoleDTO roleQuery = roleService.findById(userRoleDTO.getRoleId());
      roleDTOS.add(roleQuery);
    }

    UserDetailSecurityDTO result = new UserDetailSecurityDTO();
    result.setId(userQuery.getId());
    result.setName(userQuery.getName());
    result.setEmail(userQuery.getEmail());
    result.setPassword(userQuery.getPassword());
    result.setPhone(userQuery.getPhone());
    result.setRoleDTOS(roleDTOS);
    result.setCreatedAt(userQuery.getCreatedAt());
    result.setUpdatedAt(userQuery.getUpdatedAt());

    return Optional.of(result);
  }

  @Override
  public UserDetailSecurityDTO saveUser(UserDTO userDTO) {
    UserDTO userQuery = userService.findByEmail(userDTO.getEmail());

    if (userQuery == null) {
      userDTO.setCreatedAt(Helper.getCurrentTime());
      UserDTO resultQuery = userService.saveCustomer(userDTO);
      RoleDTO roleCustomer = roleService.findByName(RoleEnum.ROLE_CUSTOMER.getName());

      UserRoleDTO userRoleSave = new UserRoleDTO();
      userRoleSave.setUserId(resultQuery.getId());
      userRoleSave.setRoleId(roleCustomer.getId());

      userRoleService.add(userRoleSave);

      UserDetailSecurityDTO result = this.findByEmail(resultQuery.getEmail()).get();

      cartService.saveNewCart(result.getId());
      return result;
    } else {
      return null;
    }

  }
}
