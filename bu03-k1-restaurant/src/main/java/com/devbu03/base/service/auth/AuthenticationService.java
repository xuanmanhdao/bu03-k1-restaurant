package com.devbu03.base.service.auth;

import com.devbu03.base.auth.AuthenticationRequest;
import com.devbu03.base.auth.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthenticationService {

  AuthenticationResponse authenticate(AuthenticationRequest request);

  void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

  AuthenticationResponse generateTokenFromUserEmail(String userEmail);
}
