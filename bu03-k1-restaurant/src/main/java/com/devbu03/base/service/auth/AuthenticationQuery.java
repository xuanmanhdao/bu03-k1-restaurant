package com.devbu03.base.service.auth;

import com.devbu03.base.dto.UserDTO;
import com.devbu03.base.dto.UserDetailSecurityDTO;
import java.util.Optional;

public interface AuthenticationQuery {

  Optional<UserDetailSecurityDTO> findByEmail(String email);

  UserDetailSecurityDTO saveUser(UserDTO userDTO);
}
