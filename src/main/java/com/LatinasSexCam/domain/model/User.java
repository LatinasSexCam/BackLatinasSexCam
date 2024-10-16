package com.LatinasSexCam.domain.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private long id_user;
    private String user_name;
    private String email;
    private String password;
    private String phoneNumber;
    private String gender;
    private String nationality;
    private String profile_photo;
    private UserStatus status;
    private Role role;
}
