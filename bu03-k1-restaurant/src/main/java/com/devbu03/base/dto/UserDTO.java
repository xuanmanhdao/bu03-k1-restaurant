package com.devbu03.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@Builder
public class UserDTO {
  private Long id;

  private List<String> roleName;
  private String email;

  private String password;

  private String name;
  private String phone;

  private Date createdAt;

  private Date updatedAt;
}

