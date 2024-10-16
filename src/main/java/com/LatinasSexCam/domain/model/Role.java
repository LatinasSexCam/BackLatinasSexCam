package com.LatinasSexCam.domain.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    private long id_role;
    private String name;
    private Set<User> users;
}
