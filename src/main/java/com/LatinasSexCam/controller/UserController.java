package com.LatinasSexCam.controller;

import com.LatinasSexCam.model.request.RegisterRequest;
import com.LatinasSexCam.service.UserService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("LatinasSexCam/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) throws MessagingException{
        return userService.registerUser(request);
    }
}
