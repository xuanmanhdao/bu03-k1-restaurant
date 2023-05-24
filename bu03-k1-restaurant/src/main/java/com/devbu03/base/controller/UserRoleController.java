package com.devbu03.base.controller;

import com.devbu03.base.response.ResponseService;
import com.devbu03.base.service.userRole.UserRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-role")
public class UserRoleController extends BaseController<UserRoleService> {

  public UserRoleController(UserRoleService service, ResponseService responseService) {
    super(service, responseService);
  }
}
