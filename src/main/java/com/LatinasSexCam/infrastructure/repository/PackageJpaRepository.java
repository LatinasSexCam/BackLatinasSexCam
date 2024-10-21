package com.LatinasSexCam.infrastructure.repository;

import com.LatinasSexCam.infrastructure.entity.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PackageJpaRepository extends JpaRepository<PackageEntity, Long> {

    List<PackageEntity> findAll();
    Optional<PackageEntity> findById(long id);

}