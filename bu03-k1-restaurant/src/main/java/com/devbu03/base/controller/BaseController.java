package com.devbu03.base.controller;

import com.devbu03.base.response.Response;
import com.devbu03.base.response.ResponseService;

public abstract class BaseController<S> {

  private final S service;
  private final ResponseService responseService;

  protected BaseController(S service, ResponseService responseService) {
    this.service = service;
    this.responseService = responseService;
  }

  public S getService() {
    return this.service;
  }

  public ResponseService getResponseService() {
    return responseService;
  }
  public <T> Response<T> success() {
    return this.responseService.success();
  }

  public <T> Response<T> success(String messageCode) {
    return this.responseService.success(messageCode);
  }

  public <T> Response<T> success(T data) {
    return this.responseService.success(data);
  }

  public <T> Response<T> success(String messageCode, T data) {
    return this.responseService.success(messageCode, data);
  }
}
