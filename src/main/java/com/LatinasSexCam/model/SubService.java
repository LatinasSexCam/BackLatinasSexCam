package com.LatinasSexCam.model;

import jakarta.persistence.*;

@Entity
public class SubService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubService;

    private String name;
    private String description;
    private double price;
    private int quantity;
    private double time;

    @ManyToOne
    @JoinColumn(name = "fk_service")
    private Service service;

}

