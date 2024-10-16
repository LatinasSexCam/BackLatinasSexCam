package com.LatinasSexCam.infrastructure.repository;


import com.LatinasSexCam.infrastructure.entity.CategoryFilterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryFilterJpaRepository extends JpaRepository<CategoryFilterEntity, Long> {

    List<CategoryFilterEntity> findAll();
}
