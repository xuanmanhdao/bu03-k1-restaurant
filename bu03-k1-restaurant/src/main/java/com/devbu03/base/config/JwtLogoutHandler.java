package com.devbu03.base.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

@Service
public class JwtLogoutHandler implements LogoutHandler {

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {
    SecurityContextHolder.clearContext();

    Optional<Cookie> refreshTokenCookie = Optional.ofNullable(
        WebUtils.getCookie(request, "refreshToken"));
    if (refreshTokenCookie.isPresent()) {
      refreshTokenCookie.get().setValue("");
      refreshTokenCookie.get().setPath("/");
      refreshTokenCookie.get().setMaxAge(0);
      response.addCookie(refreshTokenCookie.get());
    }
  }
}
