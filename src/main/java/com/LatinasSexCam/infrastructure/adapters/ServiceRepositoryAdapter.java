package com.LatinasSexCam.infrastructure.adapters;

import com.LatinasSexCam.domain.model.Services;
import com.LatinasSexCam.domain.ports.ServiceRepositoryPort;
import com.LatinasSexCam.infrastructure.entity.ServiceEntity;
import com.LatinasSexCam.infrastructure.mapper.ServiceSubServiceMapper;
import com.LatinasSexCam.infrastructure.repository.ServiceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ServiceRepositoryAdapter implements ServiceRepositoryPort {

    private final ServiceJpaRepository serviceJpaRepository;
    private final ServiceSubServiceMapper serviceSubServiceMapper;
    @Override
    public Services findById(long id) {
        return serviceSubServiceMapper.toDomain(serviceJpaRepository.findById(id));
    }

    @Override
    public List<Services> findAllServices() {
        return  serviceJpaRepository.findAll().stream()
                .map(serviceSubServiceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Services> findByIdServiceIn(List<Long> ids) {
        return serviceJpaRepository.findByIdServiceIn(ids).stream()
                .map(serviceSubServiceMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Set<Services> toDomain(Set<ServiceEntity> entities) {
        return entities.stream()
                .map(serviceSubServiceMapper::toDomain)
                .collect(Collectors.toSet());
    }

    public Set<ServiceEntity> toEntity(Set<Services> services) {
        if (services == null || services.isEmpty()) {
            return Set.of();
        }
        return services.stream()
                .map(this::toEntity) // Usa el método existente que convierte un Service a ServiceEntity
                .collect(Collectors.toSet());
    }


    public Services toDomain(ServiceEntity entity){
        if (entity == null){
            return null;
        }
        return Services.builder()
                .idService(entity.getIdService())
                .title(entity.getTitle())
                .subServices(serviceSubServiceMapper.toDomain(entity.getSubServices()))
                .build();
    }

    public ServiceEntity toEntity(Services service){
        if (service == null) return null;
        return ServiceEntity.builder()
                .idService(service.getIdService())
                .title(service.getTitle())
                .subServices(serviceSubServiceMapper.toEntity(service.getSubServices()))
                .build();
    }

}
