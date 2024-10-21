package com.LatinasSexCam.infrastructure.repository;

import com.LatinasSexCam.infrastructure.entity.MultimediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultimediaJpaRepository extends JpaRepository<MultimediaEntity, Long> {

/*    List<MultimediaEntity> findByWomenId(Long womenId);*/
}
