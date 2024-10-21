package com.LatinasSexCam.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Package {
    private long idPackage;
    private String name;
    private double price;
    private String description;
}
