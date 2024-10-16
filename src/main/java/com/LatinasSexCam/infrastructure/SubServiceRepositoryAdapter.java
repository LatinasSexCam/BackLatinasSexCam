package com.LatinasSexCam.infrastructure;

import com.LatinasSexCam.domain.model.SubService;
import com.LatinasSexCam.domain.ports.SubServiceRepositoryPort;
import com.LatinasSexCam.infrastructure.entity.SubServiceEntity;
import com.LatinasSexCam.infrastructure.mapper.ServiceSubServiceMapper;
import com.LatinasSexCam.infrastructure.repository.SubServiceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubServiceRepositoryAdapter implements SubServiceRepositoryPort {

    private final SubServiceJpaRepository subServiceJpaRepository;
    private final ServiceSubServiceMapper serviceSubServiceMapper;
    @Override
    public SubService findById(long id) {
        return serviceSubServiceMapper.toDomain(subServiceJpaRepository.findById(id));
    }

    @Override
    public List<SubService> findByService_IdService(long idService) {
        List<SubServiceEntity> entities = subServiceJpaRepository.findByService_IdService(idService);
        return entities.stream()
                .map(serviceSubServiceMapper::toDomain)
                .collect(Collectors.toList());
    }



}
