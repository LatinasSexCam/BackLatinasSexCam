package com.LatinasSexCam.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Women {
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
    private WomenStatus status;
    private List<Multimedia> mediaList;
    private User user;
    private Set<Service> services;
    private Set<CategoryFilter> categoryFilters;

}
