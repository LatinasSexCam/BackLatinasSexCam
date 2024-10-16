package com.LatinasSexCam.domain.ports;

import com.LatinasSexCam.domain.model.Service;

import java.util.List;

public interface ServiceRepositoryPort {

    Service findById(long id);
    List<Service> findAll();
}
