package com.LatinasSexCam.controller;

import com.LatinasSexCam.controller.response.TokenResponse;
import com.LatinasSexCam.model.User;
import com.LatinasSexCam.model.request.LoginRequest;
import com.LatinasSexCam.model.request.RegisterRequest;
import com.LatinasSexCam.repository.UserRepository;
import com.LatinasSexCam.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("LatinasSexCam/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping(value = "register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) throws MessagingException{
        return userService.registerUser(request);
    }

    @PostMapping(value = "login")
    public ResponseEntity<TokenResponse> loginUser(@RequestBody LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(userService.loginUser(request, user));
    }
}
