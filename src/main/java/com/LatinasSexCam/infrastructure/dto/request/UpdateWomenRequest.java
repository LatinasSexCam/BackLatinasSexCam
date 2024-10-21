package com.LatinasSexCam.infrastructure.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class UpdateWomenRequest {

    private String email;
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
    private List<MultimediaRequest> multimedia = new ArrayList<>();
}
