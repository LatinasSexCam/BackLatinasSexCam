package com.LatinasSexCam.infrastructure.repository;


import com.LatinasSexCam.infrastructure.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceJpaRepository extends JpaRepository<ServiceEntity, Long> {

    ServiceEntity findById(long id);
}
