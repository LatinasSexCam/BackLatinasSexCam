package com.LatinasSexCam.infrastructure.controller;

import com.LatinasSexCam.application.service.MultimediaService;
import com.LatinasSexCam.domain.model.Multimedia;
import com.LatinasSexCam.domain.model.Women;
import com.LatinasSexCam.infrastructure.dto.request.MultimediaRequest;
import com.LatinasSexCam.infrastructure.dto.request.RegisterRequest;
import com.LatinasSexCam.infrastructure.dto.request.RegisterWomenRequest;
import com.LatinasSexCam.infrastructure.dto.request.UpdateWomenRequest;
import com.LatinasSexCam.infrastructure.dto.response.MultimediaResponseDTO;
import com.LatinasSexCam.infrastructure.dto.response.WomenInfoResponseDTO;
import com.LatinasSexCam.infrastructure.dto.response.WomensResponseDTO;
import com.LatinasSexCam.infrastructure.entity.WomenEntity;
import com.LatinasSexCam.infrastructure.repository.WomenJpaRepository;
import com.LatinasSexCam.application.service.WomenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("LatinasSexCam/women")
@RequiredArgsConstructor
/*@CrossOrigin(origins = "*")*/
public class WomenController {

    private final WomenJpaRepository womenJpaRepository;
    private final WomenService womenService;
    private final MultimediaService multimediaService;

    @PostMapping("register")
    public ResponseEntity<String> registerWomen(@RequestBody RegisterWomenRequest request){
        return womenService.registerWomen(request);
    }

    @PostMapping("/update")
/*    @CrossOrigin(origins = "*")*/
    public ResponseEntity<String> updateWomenDetails(@Valid @RequestBody UpdateWomenRequest request) {
        return womenService.updateInfoWomen(request);
    }
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

    @PostMapping("/info")
/*    @CrossOrigin(origins = "*")*/
    public ResponseEntity<WomenInfoResponseDTO> getInfoWomen(@RequestBody RegisterRequest userName){
        WomenInfoResponseDTO womens = womenService.getWomesInfo(userName);
        if (womens == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(womens);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadMultimedia(@RequestBody MultimediaRequest request){
        return multimediaService.UploadImage(request);
    }

    @GetMapping("multimedia/{username}")
    public ResponseEntity<List<MultimediaResponseDTO>> getMultimedia(@PathVariable String username){
        List<MultimediaResponseDTO> multi = multimediaService.getMultimedia(username);
        if (multi == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(multi);
    }

}
