package com.LatinasSexCam.infrastructure.mapper;

import com.LatinasSexCam.domain.model.CategoryFilter;
import com.LatinasSexCam.domain.model.Women;
import com.LatinasSexCam.infrastructure.ServiceRepositoryAdapter;
import com.LatinasSexCam.infrastructure.UserRepositoryAdapter;
import com.LatinasSexCam.infrastructure.WomenRepositoryAdapter;
import com.LatinasSexCam.infrastructure.entity.CategoryFilterEntity;
import com.LatinasSexCam.infrastructure.entity.WomenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WomenCategoryFilterMapper {

    private final ServiceRepositoryAdapter serviceRepositoryAdapter;
    private final UserRepositoryAdapter userRepositoryAdapter;

    public Women toDomain(WomenEntity entity) {
        if (entity == null) {
            return null;
        }
        return Women.builder()
                .idWomen(entity.getIdWomen())
                .name(entity.getName())
                .age(entity.getAge())
                .height(entity.getHeight())
                .weight(entity.getWeight())
                .hips(entity.getHips())
                .shoeSize(entity.getShoeSize())
                .colorEyes(entity.getColorEyes())
                .colorHair(entity.getColorHair())
                .colorSkin(entity.getColorSkin())
                .cupSize(entity.getCupSize())
                .shaving(entity.getShaving())
                .smoker(entity.getSmoker())
                .piercings(entity.getPiercings())
                .tattoos(entity.getTattoos())
                .categoryFilters(toDomain(entity.getCategoryFilters()))
                .services(serviceRepositoryAdapter.toDomain(entity.getServices()))
                .user(userRepositoryAdapter.toDomain(entity.getUser()))
                .build();
    }

    public WomenEntity toEntity(Women women) {
        if (women == null) {
            return null;
        }
        return WomenEntity.builder()
                .idWomen(women.getIdWomen())
                .name(women.getName())
                .description(women.getDescription())
                .age(women.getAge())
                .height(women.getHeight())
                .weight(women.getWeight())
                .hips(women.getHips())
                .shoeSize(women.getShoeSize())
                .colorHair(women.getColorHair())
                .colorEyes(women.getColorEyes())
                .colorSkin(women.getColorSkin())
                .cupSize(women.getCupSize())
                .shaving(women.getShaving())
                .smoker(women.getSmoker())
                .piercings(women.getPiercings())
                .tattoos(women.getTattoos())
                .status(women.getStatus())
                .categoryFilters(toEntity(women.getCategoryFilters()))
                .services(serviceRepositoryAdapter.toEntity(women.getServices()))
                .user(userRepositoryAdapter.toEntity(women.getUser()))
                .build();
    }

    public Set<Women> toDomainWomen(Set<WomenEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Set.of();
        }
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toSet());
    }

    public Set<WomenEntity> toEntityWomen(Set<Women> women) {
        if (women == null || women.isEmpty()) {
            return Set.of();
        }
        return women.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    public CategoryFilter toDomain(CategoryFilterEntity entity) {
        if (entity == null) {
            return null;
        }
        return CategoryFilter.builder()
                .idCategoryFilter(entity.getIdCategoryFilter())
                .name(entity.getName())
                .women(toDomainWomen(entity.getWomen()))
                .build();
    }

    public Set<CategoryFilter> toDomain(Set<CategoryFilterEntity> entities){
        if (entities == null || entities.isEmpty()){
            return Set.of();
        }
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toSet());
    }

    public CategoryFilterEntity toEntity(CategoryFilter categoryFilter) {
        if (categoryFilter == null) {
            return null;
        }
        return CategoryFilterEntity.builder()
                .idCategoryFilter(categoryFilter.getIdCategoryFilter())
                .name(categoryFilter.getName())
                .women(toEntityWomen(categoryFilter.getWomen()))
                .build();
    }


    public Set<CategoryFilterEntity> toEntity(Set<CategoryFilter> categoryFilters){
        if (categoryFilters == null || categoryFilters.isEmpty()){
            return Set.of();
        }
        return categoryFilters.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }



}
