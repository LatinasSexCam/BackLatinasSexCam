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
    public ResponseEntity<String> registerWomenAdmin(@RequestBody RegisterWomenAdminRequest request){
        return adminService.registerWomenByAdmin(request);
    }

    @PostMapping("updateInfo")
    public ResponseEntity<String> updateInfoWomenAdmin(@RequestBody RegisterWomenAdminRequest request){
        return adminService.updateInfoWomenAdmin(request);
    }
    @DeleteMapping("deleteWomen")
    public ResponseEntity<String> deleteWomenAdmin(@RequestBody RegisterWomenAdminRequest username){
        return adminService.deleteWomen(username);
    }
}
