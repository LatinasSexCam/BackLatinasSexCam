package com.LatinasSexCam.infrastructure.controller;


import com.LatinasSexCam.domain.model.Women;
import com.LatinasSexCam.infrastructure.entity.WomenEntity;
import com.LatinasSexCam.infrastructure.repository.WomenJpaRepository;
import com.LatinasSexCam.application.service.WomenService;
import lombok.RequiredArgsConstructor;
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
    public List<WomenEntity> getWomens(){
        return womenJpaRepository.findAll();
    }

    @GetMapping("count")
    public long getTotalWomen() {
        return womenJpaRepository.count();
    }


    @GetMapping("/filter")
    public List<WomenEntity> filterWomenByCategories(@RequestParam List<String> categories) {
        return womenService.getWomenByCategories(categories);
    }
}
