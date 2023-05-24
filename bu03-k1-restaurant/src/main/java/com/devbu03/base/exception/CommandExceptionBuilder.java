package com.devbu03.base.exception;

import com.devbu03.base.response.ErrorCode;
import java.util.Collections;
import java.util.List;

public class CommandExceptionBuilder {

  private CommandExceptionBuilder() {
  }

  public static CommandException exception(ErrorCode errorCode) {
    return new CommandException("error.default", Collections.singletonList(errorCode));
  }

  public static CommandException exception(List<ErrorCode> errorCodes) {
    return new CommandException("error.default", errorCodes);
  }
}
