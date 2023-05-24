package com.devbu03.base.common;

import com.devbu03.base.auth.AuthenticationResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.springframework.http.HttpHeaders;

public class Helper {

  public static Timestamp getCurrentTime() {
    return new Timestamp(System.currentTimeMillis());
  }

  public static void setTokenToCookie(AuthenticationResponse authenticationResponse,
      HttpServletResponse response) {
    Cookie cookieRefreshToken = new Cookie("refreshToken",
        authenticationResponse.getRefreshToken());
    cookieRefreshToken.setPath("/");
    cookieRefreshToken.setHttpOnly(true);
    response.addCookie(cookieRefreshToken);

    response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + authenticationResponse.getToken());
  }

  public static LocalDateTime getLocalDateTimeNow() {
    return LocalDateTime.now();
  }
}
