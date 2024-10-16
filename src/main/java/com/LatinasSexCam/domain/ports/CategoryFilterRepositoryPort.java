package com.LatinasSexCam.domain.ports;

import com.LatinasSexCam.domain.model.CategoryFilter;

import java.util.List;

public interface CategoryFilterRepositoryPort {

    List<CategoryFilter> findAll();
    Void save(CategoryFilter categoryFilter);
}
