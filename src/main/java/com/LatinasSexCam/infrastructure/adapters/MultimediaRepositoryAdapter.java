/*
package com.LatinasSexCam.infrastructure.adapters;

import com.LatinasSexCam.domain.model.Multimedia;
import com.LatinasSexCam.domain.ports.MultimediaRepositoryPort;
import com.LatinasSexCam.infrastructure.entity.MultimediaEntity;
import com.LatinasSexCam.infrastructure.mapper.WomenCategoryFilterMapper;
import com.LatinasSexCam.infrastructure.repository.MultimediaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class MultimediaRepositoryAdapter implements MultimediaRepositoryPort {
    private final MultimediaJpaRepository multimediaJpaRepository;
    private final WomenCategoryFilterMapper womenCategoryFilterMapper;

    @Override
    public void save(Multimedia multimedia) {
        multimediaJpaRepository.save(toEntity(multimedia));
    }
    @Override
    public List<Multimedia> findByWomenId(Long womenId) {
        List<MultimediaEntity> entities = multimediaJpaRepository.findByWomenId(womenId);
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private Multimedia toDomain(MultimediaEntity entity){
        return Multimedia.builder()
                .idMedia(entity.getIdMedia())
                .mediaType(entity.getMediaType())
                .url(entity.getUrl())
                .women(womenCategoryFilterMapper.toDomain(entity.getWomen()))
                .build();
    }
    private MultimediaEntity toEntity(Multimedia multimedia){
        return MultimediaEntity.builder()
                .idMedia(multimedia.getIdMedia())
                .mediaType(multimedia.getMediaType())
                .url(multimedia.getUrl())
                .women(womenCategoryFilterMapper.toEntity(multimedia.getWomen()))
                .build();
    }


}
*/
