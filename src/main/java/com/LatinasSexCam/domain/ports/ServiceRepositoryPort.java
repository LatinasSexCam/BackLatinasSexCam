package com.LatinasSexCam.domain.ports;

import com.LatinasSexCam.domain.model.Services;

import java.util.List;

public interface ServiceRepositoryPort {

    Services findById(long id);
    List<Services> findAllServices();
    List<Services> findByIdServiceIn(List<Long> ids);
}
