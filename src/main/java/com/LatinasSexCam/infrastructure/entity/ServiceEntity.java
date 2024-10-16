package com.LatinasSexCam.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Service")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;

    private String title;

    @ManyToMany(mappedBy = "services")
    private Set<WomenEntity> women;

    @OneToMany(mappedBy = "service")
    private Set<SubServiceEntity> subServices;
}
