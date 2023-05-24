package com.devbu03.base.service.translate;

import com.devbu03.base.response.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranslateServiceImpl implements TranslateService{
private final MessageSource messageSource;

  @Override
  public String translate(String messageCode) {
    return this.messageSource.getMessage(messageCode, null, messageCode, null);
  }
  @Override
  public List<ErrorCode> translateWithErrorCodes(List<ErrorCode> errorCodes) {
    List<ErrorCode> list = new ArrayList<>();
    errorCodes.forEach(
        e ->list.add(new ErrorCode(e.getCode(),this.translate(e.getMessage())))
    );
    return list;
  }
}
