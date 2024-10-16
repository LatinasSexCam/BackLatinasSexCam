package com.LatinasSexCam.infrastructure.entity;

import com.LatinasSexCam.domain.model.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;

    private String userName;

    private String email;

    private String password;

    private String phoneNumber;

    private String gender;

    private String nationality;

    private String profilePhoto;


    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @ManyToOne
    @JoinColumn(name = "fkid_role")
    private RoleEntity role;

    @OneToMany(mappedBy = "user")
    private Set<CommentEntity> comments;

    @OneToMany(mappedBy = "user")
    private  Set<WomenEntity> women;
}