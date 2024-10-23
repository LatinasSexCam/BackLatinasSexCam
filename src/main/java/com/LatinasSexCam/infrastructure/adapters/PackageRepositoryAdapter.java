package com.LatinasSexCam.infrastructure.adapters;

import com.LatinasSexCam.domain.model.Package;
import com.LatinasSexCam.domain.ports.PackageRepositoryPort;
import com.LatinasSexCam.infrastructure.entity.PackageEntity;
import com.LatinasSexCam.infrastructure.repository.PackageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PackageRepositoryAdapter implements PackageRepositoryPort {

    private final PackageJpaRepository packageJpaRepository;
    @Override
    public List<Package> findAll() {
        return packageJpaRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Package findById(long id) {
        return packageJpaRepository.findById(id)
                .map(this::toDomain)
                .orElseThrow(() -> new RuntimeException("Package with Id " +id + "not found"));
    }

    public Package toDomain(PackageEntity entity){
        if (entity == null){
            return null;
        }
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
