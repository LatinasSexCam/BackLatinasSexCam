package com.LatinasSexCam.application.service;


import com.LatinasSexCam.domain.model.Women;
import com.LatinasSexCam.domain.ports.WomenRepositoryPort;
import com.LatinasSexCam.infrastructure.dto.response.WomensResponseDTO;
import com.LatinasSexCam.infrastructure.entity.WomenEntity;
import com.LatinasSexCam.infrastructure.repository.WomenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WomenService {
    private final WomenRepositoryPort womenRepositoryPort;

    public List<Women> getWomenByCategories(List<String> categoryNames) {
        return womenRepositoryPort.findByCategoryNames(categoryNames);
    }

    public List<WomensResponseDTO> getWomens(){
        List<Women> womens = womenRepositoryPort.findAll();
        return womens.stream()
                .map(WomensResponseDTO::new)
                .collect(Collectors.toList());
    }
}
