package com.devbu03.base.repository;
import com.devbu03.base.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);

  @Query(value = "SELECT ID FROM ("
      + "  SELECT ID FROM USERS u  ORDER BY ID DESC "
      + ") WHERE ROWNUM = 1", nativeQuery = true)
  public Optional<Long> getLastUserId();

  @Query(value = "SELECT u FROM UserEntity u WHERE u.email = ?1")
  public UserEntity checkEmailExists(String email);

  @Query(value = "SELECT u.* "
      + "FROM USERS u  "
      + "JOIN USER_ROLE  ur ON u.ID = ur.USER_ID "
      + "WHERE ur.ROLE_ID = 3", nativeQuery = true)
  public List<UserEntity> getAllStaffs();

  @Query(value = "SELECT u.* "
      + "FROM USERS u  "
      + "JOIN USER_ROLE  ur ON u.ID = ur.USER_ID "
      + "WHERE ur.ROLE_ID = 3 "
      + "AND u.ID = ?1 ", nativeQuery = true)
  public Optional<UserEntity> getStaffById(Long id);
}
