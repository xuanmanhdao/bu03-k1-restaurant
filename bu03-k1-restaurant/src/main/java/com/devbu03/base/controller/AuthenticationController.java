package com.devbu03.base.controller;

import com.devbu03.base.auth.AuthenticationRequest;
import com.devbu03.base.auth.AuthenticationResponse;
import com.devbu03.base.common.Helper;
import com.devbu03.base.dto.UserDTO;
import com.devbu03.base.dto.UserDetailSecurityDTO;
import com.devbu03.base.exception.CommandExceptionBuilder;
import com.devbu03.base.response.ErrorCode;
import com.devbu03.base.response.ResponseService;
import com.devbu03.base.service.auth.AuthenticationQuery;
import com.devbu03.base.service.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController extends BaseController<AuthenticationService> {

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationQuery authenticationQuery;

  private final MessageSource messageSource;

  protected AuthenticationController(AuthenticationService service,
      ResponseService responseService,
      PasswordEncoder passwordEncoder,
      AuthenticationQuery authenticationQuery,
      MessageSource messageSource) {
    super(service, responseService);
    this.passwordEncoder = passwordEncoder;
    this.authenticationQuery = authenticationQuery;
    this.messageSource = messageSource;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthenticationRequest request,
      HttpServletResponse response) {
    AuthenticationResponse authenticationResponse = getService().authenticate(
        request);
    Helper.setTokenToCookie(authenticationResponse, response);

    return super.success(messageSource.getMessage("success.login", null,
        LocaleContextHolder.getLocale()), authenticationResponse);
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody UserDTO request, HttpServletResponse response) {
    String passwordOrigin = request.getPassword();
    request.setPassword(passwordEncoder.encode(passwordOrigin));
    UserDetailSecurityDTO userDetailSecurityDTO = authenticationQuery.saveUser(request);
    if (userDetailSecurityDTO != null) {
      AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
          .email(userDetailSecurityDTO.getEmail())
          .password(passwordOrigin)
          .build();

      AuthenticationResponse authenticationResponse = getService().authenticate(
          authenticationRequest);

      Helper.setTokenToCookie(authenticationResponse, response);

      return super.success(messageSource.getMessage("success.register", null,
          LocaleContextHolder.getLocale()), authenticationResponse);
    } else {
      throw CommandExceptionBuilder.exception(
          new ErrorCode(HttpStatus.BAD_REQUEST.value(),
              messageSource.getMessage("error.emailAlreadyExists", null,
                  LocaleContextHolder.getLocale())));
    }
  }

  @PostMapping("/refresh-token")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    getService().refreshToken(request, response);
  }
}
