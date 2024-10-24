package com.LatinasSexCam.infrastructure.controller;

import com.LatinasSexCam.application.service.SubServiceService;
import com.LatinasSexCam.infrastructure.dto.response.SubServiceResponseDTO;
import com.LatinasSexCam.infrastructure.dto.response.TokenResponse;
import com.LatinasSexCam.infrastructure.dto.request.LoginRequest;
import com.LatinasSexCam.infrastructure.dto.request.RegisterRequest;
import com.LatinasSexCam.infrastructure.dto.response.UserInfoResponseDTO;
import com.LatinasSexCam.infrastructure.entity.SubServiceEntity;
import com.LatinasSexCam.infrastructure.repository.SubServiceJpaRepository;
import com.LatinasSexCam.application.service.CategoryFilterService;
import com.LatinasSexCam.application.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("LatinasSexCam/user")
@RequiredArgsConstructor
/*@CrossOrigin(origins = "*")*/
public class UserController {

    private final UserService userService;
    private final SubServiceService subServiceService;
    private final CategoryFilterService categoryFilterService;

    @PostMapping(value = "register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request){
        return userService.registerUser(request);
    }

    @PostMapping(value = "login")
    public ResponseEntity<TokenResponse> loginUser(@RequestBody LoginRequest request) {
        TokenResponse tokenResponse = userService.loginUser(request);

        if (tokenResponse == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(tokenResponse);
    }

    @GetMapping(value = "subServices/{serviceId}")
    public ResponseEntity<List<SubServiceResponseDTO>> getSubServicesByServiceId(@PathVariable Long serviceId) {
        List<SubServiceResponseDTO> subServices = subServiceService.getSubServicesByServiceId(serviceId);
        if (subServices.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(subServices);
    }

    @GetMapping("/filters")
    public Map<String, Long> getAllFiltersWithCount() {
        return categoryFilterService.getAllFiltersWithCount();
    }

    @PostMapping("/info")
 /*   @CrossOrigin(origins = "*")*/
    public ResponseEntity<UserInfoResponseDTO> getInfoUser(@RequestBody LoginRequest email){
        UserInfoResponseDTO users = userService.getUsers(email);
        if (users == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);

    }
}
