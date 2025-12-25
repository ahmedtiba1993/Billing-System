package com.tiba.backend.user.service;

import com.tiba.backend.user.entity.User;
import com.tiba.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
}
