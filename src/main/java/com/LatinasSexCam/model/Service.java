package com.LatinasSexCam.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;

    private String title;
    private String description;

    @ManyToMany(mappedBy = "services")
    private Set<Women> women;

    @OneToMany(mappedBy = "service")
    private Set<SubService> subServices;
}

