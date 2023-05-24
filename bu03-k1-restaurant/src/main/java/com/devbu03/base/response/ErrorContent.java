package com.devbu03.base.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorContent {

  private String message;
  private List<ErrorCode> errorCodes;
}
