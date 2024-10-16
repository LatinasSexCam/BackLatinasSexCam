package com.LatinasSexCam.domain.ports;

import com.LatinasSexCam.domain.model.CategoryFilter;
import com.LatinasSexCam.domain.model.Women;

import java.util.List;
import java.util.Set;

public interface WomenRepositoryPort {

    Women findById(long id);

    List<Women> findByCategoryNames(List<String> categoryNames);

    long countByCategoryFilters(Set<CategoryFilter> categoryFilters);

    List<Women>findAll();

    List<Women>findByServices_TitleIn(List<String> serviceTitle);
}
