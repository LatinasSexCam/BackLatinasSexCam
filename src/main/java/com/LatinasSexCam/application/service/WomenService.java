package com.LatinasSexCam.application.service;

import com.LatinasSexCam.domain.model.Women;
import com.LatinasSexCam.infrastructure.entity.WomenEntity;
import com.LatinasSexCam.infrastructure.repository.WomenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WomenService {
    private final WomenJpaRepository womenJpaRepository;

    public List<WomenEntity> getWomenByCategories(List<String> categoryNames) {
        return womenJpaRepository.findByCategoryFilters_NameIn(categoryNames);
    }
}
