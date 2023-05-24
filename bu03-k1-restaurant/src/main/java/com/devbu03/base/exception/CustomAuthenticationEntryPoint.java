package com.devbu03.base.exception;

import com.devbu03.base.response.ErrorCode;
import com.devbu03.base.response.ErrorContent;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

@AllArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final MessageSource messageSource;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    ErrorContent errorContent = new ErrorContent();
    errorContent.setMessage(authException.getMessage());
    ErrorCode errorCode = null;
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    if (status != null) {
      Integer statusCode = Integer.valueOf(status.toString());
      if (statusCode == HttpStatus.NOT_FOUND.value()) {
        errorCode = new ErrorCode(HttpStatus.NOT_FOUND.value(),
            messageSource.getMessage("error.urlNotFound", null,
                LocaleContextHolder.getLocale()));
      } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
        errorCode = new ErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            messageSource.getMessage("error.server", null,
                LocaleContextHolder.getLocale()));
      }
    } else {
      errorCode = new ErrorCode(HttpStatus.UNAUTHORIZED.value(),
          messageSource.getMessage("error.notAuthored", null,
              LocaleContextHolder.getLocale()));
    }
    errorContent.setErrorCodes(Collections.singletonList(errorCode));
    response.setStatus(HttpStatus.OK.value());
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(new ObjectMapper().writeValueAsString(errorContent));
  }
}
