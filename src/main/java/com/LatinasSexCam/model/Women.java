package com.LatinasSexCam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Women")
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

    @Builder.Default
    @OneToMany(mappedBy = "women", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Multimedia> mediaList = new ArrayList<>();


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

