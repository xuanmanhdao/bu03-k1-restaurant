package com.devbu03.base.exception;

import com.devbu03.base.response.ErrorCode;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CommandException extends RuntimeException {

  List<ErrorCode> errorCodes;

  public CommandException(String message) {
    super(message);
  }

  public CommandException(String message, List<ErrorCode> errorCodes) {
    super(message);
    this.errorCodes = errorCodes;
  }


}
