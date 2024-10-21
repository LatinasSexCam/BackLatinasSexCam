package com.LatinasSexCam.infrastructure.dto.response;

import com.LatinasSexCam.domain.model.Women;
import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class WomensResponseDTO {

    private Long id;
    private String profilePhoto;
    private String name;
    private String nationality;
    private int age;
    private String namePackage;

    public WomensResponseDTO(Women women) {
        this.id = women.getIdWomen();
        this.profilePhoto = (women.getUser() != null) ? women.getUser().getProfilePhoto(): "Unknown";
        this.name = women.getName();
        this.nationality = (women.getUser() != null) ? women.getUser().getNationality(): "Unknown";
        this.age = women.getAge();
        this.namePackage = (women.getPackageS() != null) ? women.getPackageS().getName(): "Package not found";
    }
}
