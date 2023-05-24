package com.devbu03.base.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.devbu03.base.auth.AuthenticationRequest;
import com.devbu03.base.auth.AuthenticationResponse;
import com.devbu03.base.common.Helper;
import com.devbu03.base.dto.RoleDTO;
import com.devbu03.base.dto.UserDetailSecurityDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthenticationServerImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;

  private final AuthenticationQuery authenticationQuery;

  private final MessageSource messageSource;

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;

  @Override
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

    return generateTokenFromUserEmail(request.getEmail());
  }

  @Override
  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    Optional<Cookie> refreshTokenCookie = Optional.ofNullable(
        WebUtils.getCookie(request, "refreshToken"));
    ResponseEntity<String> responseEntity = null;

    if (refreshTokenCookie.isPresent()) {
      String refreshToken = refreshTokenCookie.get().getValue();
      Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
      JWTVerifier jwtVerifier = JWT.require(algorithm).build();
      DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
      String userEmail = decodedJWT.getSubject();

      if (userEmail != null) {
        AuthenticationResponse authResponse = generateTokenFromUserEmail(userEmail);
        Helper.setTokenToCookie(authResponse, response);
        String successMessage = messageSource.getMessage("success.refreshToken", null,
            LocaleContextHolder.getLocale());
        responseEntity = new ResponseEntity<>(successMessage, HttpStatus.OK);
      }
    }

    if (responseEntity == null) {
      String errorMessage = messageSource.getMessage("error.refreshToken", null,
          LocaleContextHolder.getLocale());
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      responseEntity = new ResponseEntity<>(errorMessage, headers, HttpStatus.NOT_FOUND);
    }

    ObjectMapper objectMapper = new ObjectMapper();
    String responseJson = objectMapper.writeValueAsString(responseEntity.getBody());
    response.setStatus(responseEntity.getStatusCode().value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.getWriter().write(responseJson);
  }

  @Override
  public AuthenticationResponse generateTokenFromUserEmail(String userEmail) {
    UserDetailSecurityDTO userDetailSecurityDTO = authenticationQuery.findByEmail(userEmail)
        .orElseThrow();

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    Set<RoleDTO> roles = new HashSet<>();
    userDetailSecurityDTO.getRoleDTOS().stream()
        .forEach(c -> roles.add(new RoleDTO(c.getName())));
    roles.stream().forEach(i -> authorities.add(new SimpleGrantedAuthority(i.getName())));

    String jwtToken = jwtService.generateToken(userDetailSecurityDTO, authorities);
    String jwtRefreshToken = jwtService.generateRefreshToken(userDetailSecurityDTO);

    return AuthenticationResponse.builder()
        .token(jwtToken)
        .refreshToken(jwtRefreshToken)
        .build();
  }

}
