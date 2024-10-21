package com.LatinasSexCam.application.service;

import com.LatinasSexCam.domain.model.Services;
import com.LatinasSexCam.domain.model.Women;
import com.LatinasSexCam.domain.ports.ServiceRepositoryPort;
import com.LatinasSexCam.domain.ports.WomenRepositoryPort;
import com.LatinasSexCam.infrastructure.dto.response.ServicesResponseDTO;
import com.LatinasSexCam.infrastructure.dto.response.WomensResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicesService {
    private final WomenRepositoryPort womenRepositoryPort;
    private final ServiceRepositoryPort serviceRepositoryPort;

    public List<WomensResponseDTO> getServicesByWomen(List<String> serviceTitle) {
        System.out.println("Fetching women with services: " + serviceTitle);
        List<Women> womens = womenRepositoryPort.findByServices_TitleIn(serviceTitle);
        return womens.stream()
                .map(WomensResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<ServicesResponseDTO> getAllServices() {
        List<Services> services = serviceRepositoryPort.findAllServices();
        return services.stream()
                .map(ServicesResponseDTO::new)
                .collect(Collectors.toList());
    }
}
