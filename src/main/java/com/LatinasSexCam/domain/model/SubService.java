package com.LatinasSexCam.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubService {
    private Long idSubService;
    private String description;
    private double price;
    private String quantity;
    private String time;
    private Service service;

}

