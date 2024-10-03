package com.LatinasSexCam.model;

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
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;

    private String title;
    private String description;

    @ManyToMany(mappedBy = "services")
    private Set<Women> women;

    @OneToMany(mappedBy = "service")
    private Set<SubService> subServices;
}

