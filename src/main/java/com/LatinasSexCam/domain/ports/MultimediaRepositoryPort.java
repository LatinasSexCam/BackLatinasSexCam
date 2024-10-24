package com.LatinasSexCam.domain.ports;

import com.LatinasSexCam.domain.model.Multimedia;
import com.LatinasSexCam.domain.model.Women;

import java.util.List;
import java.util.Optional;

public interface MultimediaRepositoryPort {

    void save(Multimedia multimedia);
    List<Multimedia> findByWomen_User_UserName(String userName);

    void deleteAll(List<Multimedia> multimedia);

}
