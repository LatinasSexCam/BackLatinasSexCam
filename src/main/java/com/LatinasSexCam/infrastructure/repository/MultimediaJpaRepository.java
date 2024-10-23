package com.LatinasSexCam.infrastructure.repository;

import com.LatinasSexCam.infrastructure.entity.MultimediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MultimediaJpaRepository extends JpaRepository<MultimediaEntity, Long> {
    List<MultimediaEntity> findByWomen_User_UserName(String userName);
}
