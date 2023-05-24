package com.devbu03.base.dto;

import java.util.ArrayList;
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
@AllArgsConstructor
@Builder
public class CategoryDTO {

  private Long id;
  private String type;
  private String description;
  private Date createdAt;
  private Date updatedAt;
  private Long parentId;
  private List<CategoryDTO> listChild = new ArrayList<>();

}
