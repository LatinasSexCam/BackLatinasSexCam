package com.LatinasSexCam.domain.ports;

import com.LatinasSexCam.domain.model.SubService;

import java.util.List;

public interface SubServiceRepositoryPort {

    SubService findById(long id);
    List<SubService> findByService_IdService(long idService);
}
