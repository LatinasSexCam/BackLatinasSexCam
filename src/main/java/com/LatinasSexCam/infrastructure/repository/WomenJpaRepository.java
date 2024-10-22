package com.LatinasSexCam.infrastructure.repository;

import com.LatinasSexCam.domain.model.CategoryFilter;
import com.LatinasSexCam.domain.model.Women;
import com.LatinasSexCam.infrastructure.entity.CategoryFilterEntity;
import com.LatinasSexCam.infrastructure.entity.UserEntity;
import com.LatinasSexCam.infrastructure.entity.WomenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface WomenJpaRepository extends JpaRepository<WomenEntity, Long> {

   Optional<WomenEntity> findByUser_IdUser(Long idUser);
    Optional<WomenEntity> findByUser_UserName(String userName);
    List<WomenEntity> findByCategoryFilters_NameIn(List<String> categoryNames);

    long countByCategoryFilters(Set<CategoryFilterEntity> categoryFilters);

    List<WomenEntity>findByServices_TitleIn(List<String> serviceTitle);
}
