package com.LatinasSexCam.infrastructure.controller;

import com.LatinasSexCam.application.service.ServicesService;
import com.LatinasSexCam.domain.model.Service;
import com.LatinasSexCam.domain.model.Women;
import com.LatinasSexCam.infrastructure.entity.ServiceEntity;
import com.LatinasSexCam.infrastructure.entity.WomenEntity;
import com.LatinasSexCam.infrastructure.repository.ServiceJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("LatinasSexCam/service")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ServiceController {

    private final ServiceJpaRepository serviceJpaRepository;
    private final ServicesService servicesService;

    @GetMapping("services")
    public ResponseEntity<List<ServiceEntity>> getServices() {
        List<ServiceEntity> services = serviceJpaRepository.findAll();
        if (services.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(services); // 200 OK
    }

    @GetMapping("/filterService")
    public List<Women> filterWomenByServices(@RequestParam List<String> services){
        System.out.println("servicio recibido: " + services);
        return servicesService.getServicesByWomen(services);
    }

}
