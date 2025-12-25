package com.tiba.backend;

import com.tiba.backend.user.entity.Role;
import com.tiba.backend.user.entity.User;
import com.tiba.backend.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(BackendApplication.class, args);
  }

  @Bean
  CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return args -> {
      if (userRepository.findByUsername("admin").isEmpty()) {

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(Role.ADMIN);

        userRepository.save(admin);

        System.out.println("✅ Admin created");
      }

      if (userRepository.findByUsername("user").isEmpty()) {

        User user1 = new User();
        user1.setUsername("user");
        user1.setPassword(passwordEncoder.encode("user123"));
        user1.setRole(Role.USER);

        userRepository.save(user1);

        System.out.println("✅ User created");
      }
    };
  }
}
