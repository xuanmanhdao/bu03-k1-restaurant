package com.devbu03.base.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.devbu03.base.dto.UserDetailSecurityDTO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;

  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;

  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;

  public String generateToken(UserDetailSecurityDTO userDetailSecurityDTO,
      Collection<SimpleGrantedAuthority> authorities) {
    return buildToken(userDetailSecurityDTO, authorities, jwtExpiration);
  }

  public String generateRefreshToken(UserDetailSecurityDTO userDetailSecurityDTO) {
    return buildToken(userDetailSecurityDTO, new ArrayList<>(), refreshExpiration);
  }

  private String buildToken(
      UserDetailSecurityDTO userDetailSecurityDTO,
      Collection<SimpleGrantedAuthority> authorities,
      long expiration) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
    return JWT.create()
        .withSubject(userDetailSecurityDTO.getEmail())
        .withClaim("id", userDetailSecurityDTO.getId())
        .withClaim("name", userDetailSecurityDTO.getName())
        .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(
            Collectors.toList()))
        .withIssuedAt(new Date(System.currentTimeMillis()))
        .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
        .sign(algorithm);
  }

}
