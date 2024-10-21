package com.LatinasSexCam.domain.ports;

import com.LatinasSexCam.domain.model.Package;

import java.util.List;
import java.util.Optional;

public interface PackageRepositoryPort {

    List<Package> findAll();
    Package findById(long id);

}
