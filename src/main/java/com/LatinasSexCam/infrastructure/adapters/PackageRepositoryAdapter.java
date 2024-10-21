package com.LatinasSexCam.infrastructure.adapters;

import com.LatinasSexCam.domain.model.Package;
import com.LatinasSexCam.domain.ports.PackageRepositoryPort;
import com.LatinasSexCam.infrastructure.entity.PackageEntity;
import com.LatinasSexCam.infrastructure.repository.PackageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PackageRepositoryAdapter implements PackageRepositoryPort {

    private final PackageJpaRepository packageJpaRepository;
    @Override
    public List<Package> findAll() {
        return packageJpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Package findById(long id) {
        return packageJpaRepository.findById(id)
                .map(this::toDomain)
                .orElse(null);
    }

    public Package toDomain(PackageEntity entity){
        return Package.builder()
                .idPackage(entity.getIdPackage())
                .name(entity.getName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .build();
    }

    public PackageEntity toEntity(Package packages){
        return PackageEntity.builder()
                .idPackage(packages.getIdPackage())
                .name(packages.getName())
                .price(packages.getPrice())
                .description(packages.getDescription())
                .build();
    }

}
