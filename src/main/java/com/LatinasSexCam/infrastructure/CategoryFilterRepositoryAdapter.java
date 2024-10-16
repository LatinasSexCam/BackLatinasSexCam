package com.LatinasSexCam.infrastructure;

import com.LatinasSexCam.domain.model.CategoryFilter;
import com.LatinasSexCam.domain.model.SubService;
import com.LatinasSexCam.domain.ports.CategoryFilterRepositoryPort;
import com.LatinasSexCam.infrastructure.entity.CategoryFilterEntity;
import com.LatinasSexCam.infrastructure.entity.SubServiceEntity;
import com.LatinasSexCam.infrastructure.mapper.WomenCategoryFilterMapper;
import com.LatinasSexCam.infrastructure.repository.CategoryFilterJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryFilterRepositoryAdapter implements CategoryFilterRepositoryPort {
    private final CategoryFilterJpaRepository categoryFilterRepository;
    private final WomenCategoryFilterMapper womenCategoryFilterMapper;
    @Override
    public List<CategoryFilter> findAll() {
        return categoryFilterRepository.findAll().stream()
                .map(womenCategoryFilterMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Void save(CategoryFilter categoryFilter) {
        categoryFilterRepository.save(toEntity(categoryFilter));
        return null;
    }

    public CategoryFilterEntity toEntity(CategoryFilter categoryFilter) {
        if (categoryFilter == null) {
            return null;
        }
        return CategoryFilterEntity.builder()
                .idCategoryFilter(categoryFilter.getIdCategoryFilter())
                .name(categoryFilter.getName())
                .women(womenCategoryFilterMapper.toEntityWomen(categoryFilter.getWomen()))  // Conversión de Women a WomenEntity
                .build();
    }

    public Set<CategoryFilterEntity> toEntity(Set<CategoryFilter> categoryFilters) {
        return categoryFilters.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    public Set<CategoryFilter> toDomain(Set<CategoryFilterEntity> entities) {
        return entities.stream()
                .map(womenCategoryFilterMapper::toDomain)
                .collect(Collectors.toSet());
    }



}
