package com.LatinasSexCam.infrastructure.controller;

import com.LatinasSexCam.infrastructure.entity.PackageEntity;
import com.LatinasSexCam.infrastructure.repository.PackageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("LatinasSexCam")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PackageController {
    private final PackageJpaRepository packageJpaRepository;

    @GetMapping("packages")
    public ResponseEntity<List<PackageEntity>>getPackages(){
        List<PackageEntity> packageEntities = packageJpaRepository.findAll();
        if (packageEntities.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(packageEntities);
    }
}
