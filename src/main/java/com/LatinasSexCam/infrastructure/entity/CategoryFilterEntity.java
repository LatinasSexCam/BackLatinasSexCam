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
@Table(name = "CategoryFilter")
public class CategoryFilterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoryFilter;

    private String name;

    @ManyToMany(mappedBy = "categoryFilters")
    private Set<WomenEntity> women;

}
