package com.LatinasSexCam.infrastructure.controller;

import com.LatinasSexCam.domain.model.Service;
import com.LatinasSexCam.infrastructure.entity.ServiceEntity;
import com.LatinasSexCam.infrastructure.repository.ServiceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("LatinasSexCam/service")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ServiceController {

    private final ServiceJpaRepository serviceJpaRepository;

    @GetMapping("services")
    public ResponseEntity<List<ServiceEntity>> getServices() {
        List<ServiceEntity> services = serviceJpaRepository.findAll();
        if (services.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(services); // 200 OK
    }
}
