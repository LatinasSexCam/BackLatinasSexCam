package com.LatinasSexCam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SubService")
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

