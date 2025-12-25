package com.tiba.backend.auth.service;

import com.tiba.backend.auth.dto.AuthData;
import com.tiba.backend.auth.dto.AuthRequest;
import com.tiba.backend.auth.dto.AuthResponse;
import com.tiba.backend.auth.dto.UserInfo;
import com.tiba.backend.security.JwtService;
import com.tiba.backend.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

  @Value("${jwt.expiration}")
  private Long expiration;

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  public AuthResponse login(AuthRequest request) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

    User user = (User) authentication.getPrincipal();
    String token = jwtService.generateToken(user);

    AuthData authData =
        new AuthData(
            token,
            "Bearer",
            expiration,
            new UserInfo(user.getId(), user.getUsername(), user.getRole().name()));

    return new AuthResponse(true, "Authentication successful", authData, null, LocalDateTime.now());
  }
}
