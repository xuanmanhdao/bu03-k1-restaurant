package com.devbu03.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

  private Long id;

  private Long categoryId;

  private Integer status;

  private String image;

  private String name;

  private BigDecimal price;

  private Integer quantity;

  private String description;

  private Integer totalSold;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private Long createdBy;

  private Long updatedBy;

  public ProductDTO(String name, BigDecimal price) {
    this.name = name;
    this.price = price;
  }

  public ProductDTO(String image, String name, BigDecimal price) {
    this.image = image;
    this.name = name;
    this.price = price;
  }

  public ProductDTO(Long id, Integer status, String image, String name, BigDecimal price,
      Integer quantity, String description, Integer totalSold) {
    this.id = id;
    this.status = status;
    this.image = image;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.description = description;
    this.totalSold = totalSold;
  }

  public ProductDTO(Long id, String image, String name, BigDecimal price, Integer quantity) {
    this.id = id;
    this.image = image;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "ProductDTO{" +
        "id=" + id +
        ", categoryId=" + categoryId +
        ", status=" + status +
        ", image='" + image + '\'' +
        ", name='" + name + '\'' +
        ", price=" + price +
        ", quantity=" + quantity +
        ", description='" + description + '\'' +
        ", totalSold=" + totalSold +
        '}';
  }
}
