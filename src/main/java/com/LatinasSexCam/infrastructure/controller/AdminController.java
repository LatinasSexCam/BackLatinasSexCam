package com.LatinasSexCam.infrastructure.controller;

import com.LatinasSexCam.application.service.AdminService;
import com.LatinasSexCam.infrastructure.dto.request.RegisterWomenAdminRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("LatinasSexCam/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    @PostMapping("register")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> registerWomenAdmin(@RequestBody RegisterWomenAdminRequest request){
        return adminService.registerWomenByAdmin(request);
    }

    @PostMapping("updateInfo")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> updateInfoWomenAdmin(@RequestBody RegisterWomenAdminRequest request){
        return adminService.updateInfoWomenAdmin(request);
    }
    @DeleteMapping("deleteWomen/{username}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteWomenAdmin(@PathVariable String username){
        System.out.println(username);
        return adminService.deleteWomen(username);
    }
}
