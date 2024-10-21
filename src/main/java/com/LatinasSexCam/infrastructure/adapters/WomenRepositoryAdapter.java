package com.LatinasSexCam.infrastructure.adapters;

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
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WomenRepositoryAdapter implements WomenRepositoryPort {

    private final WomenJpaRepository womenJpaRepository;
    private final WomenCategoryFilterMapper womenCategoryFilterMapper;

    @Override
    public Women findByUser_IdUser(Long idUser) {
        return womenCategoryFilterMapper.toDomain(womenJpaRepository.findByUser_IdUser(idUser));
    }

    @Override
    public Optional<Women> findByUser_UserName(String userName) {
        return womenJpaRepository.findByUser_UserName(userName)
                .map(womenCategoryFilterMapper::toDomain);
    }

    @Override
    public List<Women> findByCategoryNames(List<String> categoryNames) {
        List<WomenEntity> entities = womenJpaRepository.findByCategoryFilters_NameIn(categoryNames);
        return entities.stream()
                .map(womenCategoryFilterMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Women> findByServices_TitleIn(List<String> serviceTitle) {
        List<WomenEntity> entities = womenJpaRepository.findByServices_TitleIn(serviceTitle);
        return entities.stream()
                .map(womenCategoryFilterMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void save(Women women) {
        womenJpaRepository.save(womenCategoryFilterMapper.toEntity(women));
    }


    @Override
    public long countByCategoryFilters(Set<CategoryFilter> categoryFilters) {
        Set<CategoryFilterEntity> categoryFilterEntities = categoryFilters.stream()
                .map(womenCategoryFilterMapper::toEntity)
                .collect(Collectors.toSet());

        return womenJpaRepository.countByCategoryFilters(categoryFilterEntities);
    }

    @Override
    public List<Women> findAll() {
        List<WomenEntity> womenEntities = womenJpaRepository.findAll();
        return womenEntities.stream()
                .map(womenCategoryFilterMapper::toDomain)
                .collect(Collectors.toList());
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
