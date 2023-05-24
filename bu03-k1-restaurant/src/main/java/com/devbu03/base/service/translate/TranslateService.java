package com.devbu03.base.service.translate;

import com.devbu03.base.response.ErrorCode;
import java.util.List;

public interface TranslateService {
    public String translate(String messageCode);
    public List<ErrorCode> translateWithErrorCodes(List<ErrorCode> errorCodes);
}
