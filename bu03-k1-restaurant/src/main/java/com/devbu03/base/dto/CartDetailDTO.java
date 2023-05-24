package com.devbu03.base.dto;

import java.math.BigDecimal;
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
public class CartDetailDTO {
  private Long id;

  private Long cartId;

  private String productName;

  private Long productId;

  private Integer quantity;

  private BigDecimal price;
}
