package com.LatinasSexCam.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "packages")
public class PackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPackage;

    private String name;

    private double price;
    private String description;
    @OneToMany(mappedBy = "packageS")
    private Set<WomenEntity> women;

}
