package com.devbu03.base.service.user;

import com.devbu03.base.dto.UserDTO;
import java.util.List;
import java.util.Optional;

public interface UserService {

  /**
   * thêm nhân viên mới
   *
   * @param userDTO
   * @return
   */

  UserDTO addStaff(UserDTO userDTO) throws Exception;

  /**
   * Sửa thông tin nhân viên
   *
   * @param id
   * @param userDTO
   * @return
   */

  UserDTO updateStaff(Long id, UserDTO userDTO);

  /**
   * Lấy tất cả nhân viên
   *
   * @return
   */

  List<UserDTO> getAllStaff();

  /**
   * Xóa nhân viên theo id
   *
   * @param id
   */

  void removeStaff(Long id);

  /**
   * Lấy tất cả nhân viên theo id
   *
   * @param id
   * @return
   */

  Optional<UserDTO> getStaffById(Long id);


  UserDTO findByEmail(String email);

  UserDTO saveCustomer(UserDTO userDTO);
}
