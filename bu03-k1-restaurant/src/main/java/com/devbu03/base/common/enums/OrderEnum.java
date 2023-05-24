package com.devbu03.base.common.enums;

public enum OrderEnum {
  ORDER_PENDING_CONFIRMATION(1),
  ORDER_CONFIRMED(2),
  ORDER_SHIPPING(3),
  ORDER_PAID(4),
  ORDER_CANCELLED(0);

  private final Integer value;

  OrderEnum(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }
}
