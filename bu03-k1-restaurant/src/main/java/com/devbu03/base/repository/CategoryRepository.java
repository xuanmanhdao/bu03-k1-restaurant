package com.devbu03.base.repository;

import com.devbu03.base.entity.CategoryEntity;
import java.util.List;
import java.util.Objects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

  @Query(value = "SELECT * FROM CATEGORY c \n"
      + "CONNECT BY PRIOR c.ID = c.PARENT_ID \n"
      + "START WITH c.ID = :id", nativeQuery = true)
  public List<CategoryEntity> getCategoryById(@Param("id") Long id);

  @Query(value = "SELECT DISTINCT * FROM CATEGORY c \n"
      + "CONNECT BY PRIOR c.ID = c.PARENT_ID\n"
      + "START WITH (:types IS NULL OR CONCAT('', c.\"TYPE\")"
      + " LIKE CONCAT('%', CONCAT(:types,'%')))\n"
      + "\tAND (:des IS NULL OR CONCAT('', c.DESCRIPTION) "
      + "LIKE CONCAT('%', CONCAT(:des,'%')))", nativeQuery = true)
  public List<CategoryEntity> getCategoryByLikeTypeAndDes(@Param("types") String type,
      @Param("des") String description);

}
