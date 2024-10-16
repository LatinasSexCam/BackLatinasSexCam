package com.LatinasSexCam.domain.model;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryFilter {
    private Long idCategoryFilter;
    private String name;
    private Set<Women> women;

}

