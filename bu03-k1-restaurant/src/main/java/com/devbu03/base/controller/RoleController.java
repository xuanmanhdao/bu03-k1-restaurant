package com.devbu03.base.controller;

import com.devbu03.base.dto.RoleDTO;
import com.devbu03.base.response.Response;
import com.devbu03.base.response.ResponseService;
import com.devbu03.base.service.role.RoleService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController extends BaseController<RoleService> {

  public RoleController(RoleService service, ResponseService responseService) {
    super(service, responseService);
  }

  @GetMapping("/list")
  public Response<List<RoleDTO>> getAll() {
    return getResponseService().success(getService().getAll());
  }
  @GetMapping("/get/{id}")
  public Response<RoleDTO> getRoleById(@PathVariable("id") Long id) {
    return getResponseService().success(getService().findById(id));
  }
}
