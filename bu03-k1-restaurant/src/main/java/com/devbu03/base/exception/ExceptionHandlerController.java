package com.devbu03.base.exception;

import com.devbu03.base.response.ErrorCode;
import com.devbu03.base.response.ErrorContent;
import com.devbu03.base.response.Response;
import com.devbu03.base.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequiredArgsConstructor
@ControllerAdvice
public class ExceptionHandlerController {

  private final ResponseService responseService;

  @ExceptionHandler({CommandException.class})
  @ResponseStatus(HttpStatus.OK)
  public Response<ErrorContent> handlerCommandException(CommandException exception) {
    return this.responseService.error(exception);
  }

  @ExceptionHandler({Exception.class})
  @ResponseStatus(HttpStatus.OK)
  public Response<ErrorContent> handlerOtherException(Exception exception) {
    CommandException commandException = CommandExceptionBuilder.exception(new ErrorCode(500,
        exception.getMessage()));
    exception.printStackTrace();
    return this.responseService.errorWithErrorCode(commandException);
  }
}
