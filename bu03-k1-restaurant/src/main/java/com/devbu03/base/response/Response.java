package com.devbu03.base.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class Response<T> extends ResponseEntity<T> {

  public Response(HttpStatusCode status) {
    super(status);
  }

  public Response(T body, HttpStatus status) {
    super(body, status);
  }
}
