package com.LatinasSexCam.infrastructure.repository;

import com.LatinasSexCam.infrastructure.entity.SubServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubServiceJpaRepository extends JpaRepository<SubServiceEntity, Long> {

    SubServiceEntity findById(long id);
    List<SubServiceEntity> findByService_IdService(Long idService);
}
