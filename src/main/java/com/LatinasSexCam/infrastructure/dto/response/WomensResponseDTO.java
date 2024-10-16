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

    private String profilePhoto;
    private String name;
    private String nationality;
    private int age;

    public WomensResponseDTO(Women women) {
        this.profilePhoto = women.getPhotoProfile();
        this.name = women.getName();
        this.nationality = women.getNationality();
        this.age = women.getAge();
    }
}
