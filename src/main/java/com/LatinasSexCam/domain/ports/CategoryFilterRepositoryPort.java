package com.LatinasSexCam.domain.ports;

import com.LatinasSexCam.domain.model.CategoryFilter;

import java.util.List;

public interface CategoryFilterRepositoryPort {

    List<CategoryFilter> findAll();

    List<CategoryFilter> findByNameIn(List<String> names);
    Void save(CategoryFilter categoryFilter);
}
