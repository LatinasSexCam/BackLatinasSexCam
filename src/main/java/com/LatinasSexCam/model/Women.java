package com.LatinasSexCam.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Women {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idWomen;

    private String name;
    private String description;
    private int age;
    private double height;
    private double weight;
    private double hips;
    private int shoeSize;
    private String colorHair;
    private String colorEyes;
    private String colorSkin;
    private String cupSize;
    private String shaving;
    private String smoker;
    private int piercings;
    private int tattoos;
    @Enumerated(EnumType.STRING)
    private WomenStatus status;
    private String photos;

    @ManyToOne
    @JoinColumn(name = "fkid_user")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "women_services",
            joinColumns = @JoinColumn(name = "fk_women"),
            inverseJoinColumns = @JoinColumn(name = "fk_service"))
    private Set<Service> services;

    @ManyToMany
    @JoinTable(
            name = "categoryfilter_women",
            joinColumns = @JoinColumn(name = "fk_women"),
            inverseJoinColumns = @JoinColumn(name = "fk_categoryfilter"))
    private Set<CategoryFilter> categoryFilters;

}

