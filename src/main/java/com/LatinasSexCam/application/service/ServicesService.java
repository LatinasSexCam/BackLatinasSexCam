package com.LatinasSexCam.application.service;

import com.LatinasSexCam.domain.model.Women;
import com.LatinasSexCam.domain.ports.WomenRepositoryPort;
import com.LatinasSexCam.infrastructure.dto.response.WomensResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServicesService {
    private final WomenRepositoryPort womenRepositoryPort;

    public List<WomensResponseDTO> getServicesByWomen(List<String> serviceTitle){
        System.out.println("Fetching women with services: " + serviceTitle);
        List<Women> womens = womenRepositoryPort.findByServices_TitleIn(serviceTitle);
        return womens.stream()
                .map(WomensResponseDTO::new)
                .collect(Collectors.toList());
    }

}
