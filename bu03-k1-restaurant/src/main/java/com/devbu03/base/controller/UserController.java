package com.devbu03.base.controller;

import com.devbu03.base.dto.UserDTO;
import com.devbu03.base.response.Response;
import com.devbu03.base.response.ResponseService;
import com.devbu03.base.service.user.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController<UserService> {

  public UserController(UserService service, ResponseService responseService) {
    super(service, responseService);
  }

  /**
   * thêm nhân viên
   *
   * @param userDTO
   * @return
   * @throws Exception
   */

  @PostMapping("/owner/add")
  public Response<UserDTO> addStaff(@RequestBody UserDTO userDTO) throws Exception {
    return getResponseService().success("Create Staff Success !",
        getService().addStaff(userDTO));
  }

  /**
   * cập nhật thông tin
   *
   * @param userDTO
   * @param id
   * @return
   */

  @PutMapping("/owner/update/{id}")
  public Response<UserDTO> updateStaff(@RequestBody UserDTO userDTO,
      @PathVariable("id") Long id) {
    return getResponseService().success("Update Success !",
        getService().updateStaff(id, userDTO));
  }

  /**
   * Lấy tất cả nhân viên
   *
   * @return
   */

  @GetMapping("/owner/list")
  public Response<List<UserDTO>> getAllStaff() {
    return getResponseService().success(getService().getAllStaff());
  }

  /**
   * Tìm nhân viên theo id
   *
   * @param id
   * @return
   */

  @GetMapping("/owner/get/{id}")
  public Response<UserDTO> getStaffById(@PathVariable("id") Long id) {
    return getResponseService().success(getService().getStaffById(id).get());
  }

  /**
   * Xóa nhân viên theo id
   *
   * @param id
   * @return
   */

  @DeleteMapping("/owner/delete/{id}")
  public Response<?> removeStaffBy(@PathVariable("id") Long id) {
    getService().removeStaff(id);
    return getResponseService().success();
  }


}
