package com.LatinasSexCam.domain.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Services {
    private Long idService;
    private String title;
    private Set<Women> women;
    private Set<SubService> subServices;
}

