package com.LatinasSexCam.infrastructure.repository;

import com.LatinasSexCam.domain.model.User;
import com.LatinasSexCam.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findById(long id);
}
