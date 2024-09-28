package com.LatinasSexCam.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;

    private String comment;
    private int stars;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;

}

