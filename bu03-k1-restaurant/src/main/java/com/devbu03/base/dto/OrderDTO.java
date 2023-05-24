package com.devbu03.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDTO {

  private Long id;
  private BigDecimal total;
  private Integer status;
  private LocalDateTime createdAt;

  private Long createdBy;
  private String address;
  private List<OrderDetailDTO> items = new ArrayList<>();

  @Override
  public String toString() {
    return "OrderDTO{" +
        "id=" + id +
        ", total=" + total +
        ", status=" + status +
        ", createdAt=" + createdAt +
        ", createdBy=" + createdBy +
        '}';
  }

  public OrderDTO(BigDecimal total) {
    this.total = total;
  }
}
