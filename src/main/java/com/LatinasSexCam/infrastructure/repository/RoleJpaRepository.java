package com.LatinasSexCam.infrastructure.repository;

import com.LatinasSexCam.domain.model.Role;
import com.LatinasSexCam.infrastructure.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findById(long id);
}
