package com.devbu03.base.response;

import com.devbu03.base.exception.CommandException;
import com.devbu03.base.service.translate.TranslateService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

  private final TranslateService translateService;

  @Override
  public <T> Response<T> success(T data) {
    SuccessContent<T> content = new SuccessContent<>(
        this.translateService.translate("success.default"), data);
    return new Response(content, HttpStatus.OK);
  }

  @Override
  public <T> Response<T> success(String messageCode, T data) {
    SuccessContent<T> content = new SuccessContent<>(this.translateService.translate(messageCode),
        data);
    return new Response(content, HttpStatus.OK);
  }

  @Override
  public <T> Response<T> success() {
    SuccessContent<T> content = new SuccessContent<>();
    content.setMessage(this.translateService.translate("success.default"));
    return new Response(content, HttpStatus.OK);
  }

  @Override
  public <T> Response<T> success(String messageCode) {
    SuccessContent<T> content = new SuccessContent<>();
    content.setMessage(this.translateService.translate(messageCode));
    return new Response(content, HttpStatus.OK);
  }

  @Override
  public <T> Response<T> error(CommandException exception) {
    ErrorContent content = new ErrorContent();
    List<ErrorCode> errorCodes = this.translateService.translateWithErrorCodes(
        exception.getErrorCodes());
    content.setMessage(this.translateService.translate(exception.getMessage()));
    content.setErrorCodes(errorCodes);
    return new Response(content, HttpStatus.OK);
  }

  @Override
  public <T> Response<T> errorWithErrorCode(CommandException exception) {
    ErrorContent content = new ErrorContent();
    content.setErrorCodes(exception.getErrorCodes());
    content.setMessage(this.translateService.translate(exception.getMessage()));
    return new Response(content, HttpStatus.OK);
  }
}
