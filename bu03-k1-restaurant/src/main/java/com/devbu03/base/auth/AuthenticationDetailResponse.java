package com.devbu03.base.auth;

import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDetailResponse {
  private Long id;

  private String email;

  private String name;

  Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
}
