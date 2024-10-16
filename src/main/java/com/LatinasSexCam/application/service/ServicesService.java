package com.LatinasSexCam.application.service;

import com.LatinasSexCam.domain.model.Women;
import com.LatinasSexCam.domain.ports.WomenRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesService {
    private final WomenRepositoryPort womenRepositoryPort;

    public List<Women> getServicesByWomen(List<String> serviceTitle){
        System.out.println("Fetching women with services: " + serviceTitle);
        return womenRepositoryPort.findByServices_TitleIn(serviceTitle);
    }

}
