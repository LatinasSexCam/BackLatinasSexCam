package com.LatinasSexCam.infrastructure;

import com.LatinasSexCam.domain.model.CategoryFilter;
import com.LatinasSexCam.domain.model.Women;
import com.LatinasSexCam.domain.ports.WomenRepositoryPort;
import com.LatinasSexCam.infrastructure.entity.CategoryFilterEntity;
import com.LatinasSexCam.infrastructure.entity.WomenEntity;
import com.LatinasSexCam.infrastructure.mapper.ServiceSubServiceMapper;
import com.LatinasSexCam.infrastructure.mapper.WomenCategoryFilterMapper;
import com.LatinasSexCam.infrastructure.repository.WomenJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WomenRepositoryAdapter implements WomenRepositoryPort {

    private final WomenJpaRepository womenJpaRepository;
    private final WomenCategoryFilterMapper womenCategoryFilterMapper;
    @Override
    public Women findById(long id) {
        return womenCategoryFilterMapper.toDomain(womenJpaRepository.findById(id));
    }

    @Override
    public List<Women> findByCategoryNames(List<String> categoryNames) {
        List<WomenEntity> entities = womenJpaRepository.findByCategoryFilters_NameIn(categoryNames);
        return entities.stream()
                .map(womenCategoryFilterMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long countByCategoryFilters(Set<CategoryFilter> categoryFilters) {
        // Convierte Set<CategoryFilter> a Set<CategoryFilterEntity>
        Set<CategoryFilterEntity> categoryFilterEntities = categoryFilters.stream()
                .map(womenCategoryFilterMapper::toEntity)  // Aquí debes tener un método toEntity
                .collect(Collectors.toSet());

        return womenJpaRepository.countByCategoryFilters(categoryFilterEntities);
    }

    public Set<Women> toDomain(Set<WomenEntity> womenEntities) {
        return womenEntities.stream()
                .map(womenCategoryFilterMapper::toDomain)
                .collect(Collectors.toSet());
    }

    public Set<WomenEntity> toEntity(Set<Women> women) {
        return women.stream()
                .map(womenCategoryFilterMapper::toEntity)
                .collect(Collectors.toSet());
    }


}
