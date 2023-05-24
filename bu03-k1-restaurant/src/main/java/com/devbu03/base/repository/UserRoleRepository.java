package com.devbu03.base.repository;

import com.devbu03.base.entity.UserRoleEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

  List<UserRoleEntity> findByUserId(Long userId);

  @Query(value = "SELECT u.ID FROM USER_ROLE u WHERE u.USER_ID = ?1 AND u.ROLE_ID = ?2 ",
      nativeQuery = true)
  Optional<Long> getIdByUserIdAndRoleId(Long userId, Long roleId);

  @Query(value = "SELECT ROLE_ID FROM USER_ROLE WHERE USER_ID = ?1", nativeQuery = true)

  public Optional<Long> getRoleIdByUserId(Long userId);


  @Query(value = "SELECT ROLE_ID FROM USER_ROLE WHERE USER_ID = ?1", nativeQuery = true)
  public List<Long> getRoleListByUserId(Long userId);

  @Query(value = "SELECT ID FROM USER_ROLE WHERE USER_ID = ?1 AND ROLE_ID != 1", nativeQuery = true)
  public List<Long> getIdFromUserIdWithoutCustomer(Long userId);

  @Query(value = "SELECT ur.ID FROM USER_ROLE ur WHERE ur.USER_ID = ?1 ", nativeQuery = true)
  public List<Long> getUserRoleIdByUserId(Long userId);

}
