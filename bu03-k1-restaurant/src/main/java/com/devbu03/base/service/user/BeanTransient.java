package com.devbu03.base.service.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class BeanTransient {

  @Bean(name = "passwordEncode")
  public BCryptPasswordEncoder setPassword() {
    return new BCryptPasswordEncoder();
  }

}
