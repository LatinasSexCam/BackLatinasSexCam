package com.LatinasSexCam.infrastructure.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterWomenAdminRequest {

    @NotNull
    private String name;
    @NotNull
    private String user_name;
    @NotNull
    private String nationality;
    private String photoProfile;
    private int age;
    private String colorEyes;
    private String colorHair;
    private String colorSkin;
    private String cupSize;
    private String description;
    private double height;
    private double hips;
    private int piercings;
    private String shaving;
    private int shoeSize;
    private String smoker;
    private int tattoos;
    private double weight;
    private List<Long> selectedServiceIds;
    private List<String> selectedFilterNames;
}
