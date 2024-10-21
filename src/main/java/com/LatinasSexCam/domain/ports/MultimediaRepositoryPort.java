package com.LatinasSexCam.domain.ports;

import com.LatinasSexCam.domain.model.Multimedia;

import java.util.List;

public interface MultimediaRepositoryPort {

    void save(Multimedia multimedia);
    List<Multimedia> findByWomenId(Long womenId);

}
