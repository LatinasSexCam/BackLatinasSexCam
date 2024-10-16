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
public class WomenService {
    private final WomenRepositoryPort womenRepositoryPort;

    public List<WomensResponseDTO> getWomenByCategories(List<String> categoryNames) {
        List<Women> womens = womenRepositoryPort.findByCategoryNames(categoryNames);
        return womens.stream()
                .map(WomensResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<WomensResponseDTO> getWomens(){
        List<Women> womens = womenRepositoryPort.findAll();
        return womens.stream()
                .map(WomensResponseDTO::new)
                .collect(Collectors.toList());
    }
}
