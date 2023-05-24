package com.devbu03.base.service.auth;

import com.devbu03.base.dto.UserDetailSecurityDTO;
import java.util.Collection;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public interface JwtService {

  String generateToken(UserDetailSecurityDTO userDetailSecurityDTO,
      Collection<SimpleGrantedAuthority> authorities);

  String generateRefreshToken(UserDetailSecurityDTO userDetailSecurityDTO);
}
