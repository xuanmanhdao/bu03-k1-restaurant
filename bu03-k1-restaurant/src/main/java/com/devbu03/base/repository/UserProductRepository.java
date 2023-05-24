package com.devbu03.base.repository;

import com.devbu03.base.entity.UserProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProductRepository extends JpaRepository<UserProductEntity,Long> {

}
