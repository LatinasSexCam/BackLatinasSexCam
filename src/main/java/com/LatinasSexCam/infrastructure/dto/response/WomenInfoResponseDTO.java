package com.LatinasSexCam.infrastructure.dto.response;

import com.LatinasSexCam.domain.model.CategoryFilter;
import com.LatinasSexCam.domain.model.Services;
import com.LatinasSexCam.domain.model.Women;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WomenInfoResponseDTO {
    private String name;
    private String packageSelect;
    private String photoProfile;
    private String nationality;
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
    private List<ServicesResponseDTO> services;
    private List<CategoryFilter> categoryFilters;


    public WomenInfoResponseDTO(Women women){
        this.name = women.getName();
        this.packageSelect = (women.getPackageS() != null) ? women.getPackageS().getName(): "Package not found";
        this.photoProfile = (women.getUser() != null) ? women.getUser().getProfilePhoto(): "Photo not found";
        this.nationality = (women.getUser() != null) ? women.getUser().getNationality(): "Nationality not found";
        this.description = women.getDescription();
        this.age = women.getAge();
        this.height = women.getHeight();
        this.weight = women.getWeight();
        this.hips = women.getHips();
        this.shoeSize = women.getShoeSize();
        this.colorHair = women.getColorHair();
        this.colorEyes = women.getColorEyes();
        this.colorSkin = women.getColorSkin();
        this.cupSize = women.getCupSize();
        this.shaving = women.getShaving();
        this.smoker = women.getSmoker();
        this.piercings = women.getPiercings();
        this.tattoos = women.getTattoos();
        this.services = women.getServices().stream().map(ServicesResponseDTO::new).collect(Collectors.toList());
        this.categoryFilters = new ArrayList<>(women.getCategoryFilters());
    }
}
