package com.devbu03.base.common.enums;

public enum RoleEnum {
  ROLE_OWNER("OWNER"),
  ROLE_STAFF("STAFF"),
  ROLE_CUSTOMER("CUSTOMER");

  private final String name;

  RoleEnum(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
