package com.LatinasSexCam.domain.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private long idUser;
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private String gender;
    private String nationality;
    private String profilePhoto;
    private UserStatus status;
    private Role role;
}
