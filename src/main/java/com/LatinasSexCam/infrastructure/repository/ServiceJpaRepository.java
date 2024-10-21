package com.LatinasSexCam.infrastructure.repository;


import com.LatinasSexCam.infrastructure.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceJpaRepository extends JpaRepository<ServiceEntity, Long> {

    ServiceEntity findById(long id);

    List<ServiceEntity> findByIdServiceIn(List<Long> ids);
}
