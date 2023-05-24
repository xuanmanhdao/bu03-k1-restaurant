package com.devbu03.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProductEntity extends BaseEntity implements Serializable {

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "product_id")
  private Long productId;
}
