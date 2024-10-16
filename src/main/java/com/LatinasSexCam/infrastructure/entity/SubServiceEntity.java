package com.LatinasSexCam.infrastructure.entity;

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
public class SubServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubService;
    private String description;
    private double price;
    private String quantity;
    private String time;
    @ManyToOne
    @JoinColumn(name = "fkid_Service")
    private ServiceEntity service;

}
