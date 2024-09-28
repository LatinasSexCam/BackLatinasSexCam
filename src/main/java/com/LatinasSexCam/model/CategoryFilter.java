package com.LatinasSexCam.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class CategoryFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoryFilter;

    private String name;

    @ManyToMany(mappedBy = "categoryFilters")
    private Set<Women> women;

}

