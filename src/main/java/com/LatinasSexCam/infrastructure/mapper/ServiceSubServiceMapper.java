package com.LatinasSexCam.infrastructure.mapper;

import com.LatinasSexCam.domain.model.Service;
import com.LatinasSexCam.domain.model.SubService;
import com.LatinasSexCam.infrastructure.entity.ServiceEntity;
import com.LatinasSexCam.infrastructure.entity.SubServiceEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component

public class ServiceSubServiceMapper {

    public Service toDomain(ServiceEntity entity) {
        if (entity == null) {
            return null;
        }
        return Service.builder()
                .idService(entity.getIdService())
                .title(entity.getTitle())
                .subServices(toDomain(entity.getSubServices()))
                .build();
    }

    public ServiceEntity toEntity(Service service) {
        if (service == null) {
            return null;
        }
        return ServiceEntity.builder()
                .idService(service.getIdService())
                .title(service.getTitle())
                .subServices(toEntity(service.getSubServices()))
                .build();
    }


    public SubService toDomain(SubServiceEntity entity) {
        if (entity == null) {
            return null;
        }
        return SubService.builder()
                .idSubService(entity.getIdSubService())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .time(entity.getTime())
                .quantity(entity.getQuantity())
                .service(toDomain(entity.getService()))
                .build();
    }

    public SubServiceEntity toEntity(SubService subService) {
        if (subService == null) {
            return null;
        }
        return SubServiceEntity.builder()
                .idSubService(subService.getIdSubService())
                .description(subService.getDescription())
                .price(subService.getPrice())
                .time(subService.getTime())
                .quantity(subService.getQuantity())
                .service(toEntity(subService.getService())) // Si necesitas incluir el Service aquí.
                .build();
    }

    public Set<SubService> toDomain(Set<SubServiceEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Set.of();
        }
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toSet());
    }

    public Set<SubServiceEntity> toEntity(Set<SubService> subServices) {
        if (subServices == null || subServices.isEmpty()) {
            return Set.of();
        }
        return subServices.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}
