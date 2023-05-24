package com.devbu03.base.exception;

import com.devbu03.base.response.ErrorCode;
import com.devbu03.base.response.ErrorContent;
import com.devbu03.base.response.Response;
import com.devbu03.base.response.ResponseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
public class CustomeAccessDeniedHandler implements AccessDeniedHandler {

  private final MessageSource messageSource;

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    ErrorContent errorContent = new ErrorContent();
    errorContent.setMessage(accessDeniedException.getMessage());
    ErrorCode errorCode = new ErrorCode(HttpStatus.FORBIDDEN.value(),
        messageSource.getMessage("error.user.authozied", null,
            LocaleContextHolder.getLocale()));
    errorContent.setErrorCodes(Collections.singletonList(errorCode));
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(new ObjectMapper().writeValueAsString(errorContent));
  }
}
