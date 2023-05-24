package com.devbu03.base.controller;

import com.devbu03.base.response.ResponseService;
import com.devbu03.base.service.userproduct.UserProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-product")
public class UserProductController extends BaseController<UserProductService> {

  public UserProductController(UserProductService service, ResponseService responseService) {
    super(service, responseService);
  }
}
