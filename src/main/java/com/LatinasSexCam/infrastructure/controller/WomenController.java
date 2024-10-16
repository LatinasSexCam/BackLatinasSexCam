package com.LatinasSexCam.infrastructure.controller;

import com.LatinasSexCam.domain.model.Women;
import com.LatinasSexCam.infrastructure.dto.response.WomensResponseDTO;
import com.LatinasSexCam.infrastructure.entity.WomenEntity;
import com.LatinasSexCam.infrastructure.repository.WomenJpaRepository;
import com.LatinasSexCam.application.service.WomenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("LatinasSexCam/women")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class WomenController {

    private final WomenJpaRepository womenJpaRepository;
    private final WomenService womenService;
    @GetMapping("womens")
    public ResponseEntity<List<WomensResponseDTO>> getWomens(){
        List<WomensResponseDTO> womens = womenService.getWomens();
        return ResponseEntity.ok(womens);
    }
    @GetMapping("count")
    public long getTotalWomen() {
        return womenJpaRepository.count();
    }


    @GetMapping("/filter")
    public ResponseEntity<List<WomensResponseDTO>> filterWomenByCategories(@RequestParam List<String> categories) {
        List<WomensResponseDTO> womens = womenService.getWomenByCategories(categories);
        return ResponseEntity.ok(womens);
    }
}
