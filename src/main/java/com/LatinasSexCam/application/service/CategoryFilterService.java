package com.LatinasSexCam.application.service;

import com.LatinasSexCam.domain.model.CategoryFilter;
import com.LatinasSexCam.domain.ports.CategoryFilterRepositoryPort;
import com.LatinasSexCam.infrastructure.CategoryFilterRepositoryAdapter;
import com.LatinasSexCam.infrastructure.entity.CategoryFilterEntity;
import com.LatinasSexCam.infrastructure.mapper.CategoryFilterMapper;
import com.LatinasSexCam.infrastructure.repository.WomenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryFilterService {

    private final CategoryFilterRepositoryPort categoryFilterRepositoryPort;
    private final WomenJpaRepository womenRepository;
    private final CategoryFilterMapper categoryFilterMapper;

    public Map<String, Long> getAllFiltersWithCount() {
        List<CategoryFilter> filters = categoryFilterRepositoryPort.findAll();
        Map<String, Long> filterCounts = new HashMap<>();

        for (CategoryFilter filter : filters) {
            String filterName = filter.getName();
            // Convertir CategoryFilter a CategoryFilterEntity
            CategoryFilterEntity filterEntity = categoryFilterMapper.toEntity(filter);
            Set<CategoryFilterEntity> filterEntities = Set.of(filterEntity);
            long count = womenRepository.countByCategoryFilters(filterEntities);
            filterCounts.put(filterName, count);
        }

        return filterCounts;
    }
}
