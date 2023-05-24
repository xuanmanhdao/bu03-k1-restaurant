package com.devbu03.base.repository;

import com.devbu03.base.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

  @Query(value = "SELECT CASE WHEN EXISTS "
      + " (SELECT 1 FROM cart WHERE created_by = :userId) "
      + " THEN 1 ELSE 0 END FROM dual", nativeQuery = true)
  int checkCartExistenceByUser(@Param("userId") long userId);

  @Query(value = "select cd.id from Cart cd where cd.CREATED_BY=:userId", nativeQuery = true)
  long getCartIdByUserId(@Param("userId") long userId);
}
