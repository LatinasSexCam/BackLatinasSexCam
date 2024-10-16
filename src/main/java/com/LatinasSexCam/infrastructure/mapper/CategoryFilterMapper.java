package com.LatinasSexCam.infrastructure.mapper;

import com.LatinasSexCam.domain.model.CategoryFilter;
import com.LatinasSexCam.infrastructure.entity.CategoryFilterEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryFilterMapper {

    public CategoryFilterEntity toEntity(CategoryFilter categoryFilter) {
        if (categoryFilter == null) {
            return null;
        }
        return CategoryFilterEntity.builder()
                .idCategoryFilter(categoryFilter.getIdCategoryFilter())
                .name(categoryFilter.getName())
                .build();
    }

    public CategoryFilter toDomain(CategoryFilterEntity entity) {
        if (entity == null) {
            return null;
        }
        return CategoryFilter.builder()
                .idCategoryFilter(entity.getIdCategoryFilter())
                .name(entity.getName())
                .build();
    }
}
