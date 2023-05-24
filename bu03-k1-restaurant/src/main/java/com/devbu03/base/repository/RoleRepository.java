package com.devbu03.base.repository;

import com.devbu03.base.entity.RoleEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

  @Query(value = "SELECT ID FROM ROLE WHERE NAME = ?1", nativeQuery = true)
  public Optional<Long> getRoleIdByRoleName(String name);

  Optional<RoleEntity> findByName(String name);

  @Query(value = "SELECT r.* FROM ROLE r \n"
      + " JOIN USER_ROLE ur ON r.ID = ur.ROLE_ID \n"
      + " WHERE ur.USER_ID = ?1 ", nativeQuery = true)
  public List<RoleEntity> getRoleFromUserId(Long userId);

}
