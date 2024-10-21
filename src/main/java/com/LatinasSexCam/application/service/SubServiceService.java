package com.LatinasSexCam.application.service;

import com.LatinasSexCam.domain.model.SubService;
import com.LatinasSexCam.domain.ports.SubServiceRepositoryPort;
import com.LatinasSexCam.infrastructure.dto.response.SubServiceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubServiceService {
    private final SubServiceRepositoryPort subServiceRepositoryPort;

    public List<SubServiceResponseDTO> getSubServicesByServiceId(long serviceId){
        List<SubService> subServices = subServiceRepositoryPort.findByService_IdService(serviceId);
        return subServices.stream()
                .map(SubServiceResponseDTO::new)
                .collect(Collectors.toList());
    }

}
