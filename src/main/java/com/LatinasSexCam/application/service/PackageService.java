package com.LatinasSexCam.application.service;

import com.LatinasSexCam.domain.model.Package;
import com.LatinasSexCam.domain.ports.PackageRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageService {

    private final PackageRepositoryPort packageRepositoryPort;

    public List<Package> getAllPackages(){
        return packageRepositoryPort.findAll();
    }
}
