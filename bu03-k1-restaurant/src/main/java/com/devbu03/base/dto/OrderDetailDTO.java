package com.devbu03.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDetailDTO {

  private Long id;

  private Long orderId;

  private ProductDTO product;

  private Long productId;

  private Integer quantity;

  private BigDecimal price;

  public OrderDetailDTO(Long id, Long orderId, ProductDTO product, Integer quantity,
      BigDecimal price) {
    this.id = id;
    this.orderId = orderId;
    this.product = product;
  }

  public OrderDetailDTO(Long id, Long orderId, Integer quantity,
      BigDecimal price) {
    this.id = id;
    this.orderId = orderId;
    this.quantity = quantity;
    this.price = price;
  }


}
