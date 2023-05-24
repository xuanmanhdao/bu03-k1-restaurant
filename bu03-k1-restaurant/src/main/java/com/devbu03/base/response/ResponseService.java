package com.devbu03.base.response;

import com.devbu03.base.exception.CommandException;

public interface ResponseService {

  <T> Response<T> success(T data);

  <T> Response<T> success(String message, T data);

  <T> Response<T> success();

  <T> Response<T> success(String message);

  <T> Response<T> error(CommandException exception);

  <T> Response<T> errorWithErrorCode(CommandException exception);

}
