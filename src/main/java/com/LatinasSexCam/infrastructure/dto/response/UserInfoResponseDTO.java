package com.LatinasSexCam.infrastructure.dto.response;

import com.LatinasSexCam.domain.model.User;
import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserInfoResponseDTO {

    private String userName;
    private String gender;
    private String numberPhone;
    private String nationality;
    private String profilePhoto;

    public UserInfoResponseDTO(User user){
        this.userName = user.getUserName();
        this.gender = user.getGender();
        this.numberPhone = user.getPhoneNumber();
        this.nationality = user.getNationality();
        this.profilePhoto = user.getProfilePhoto();
    }
}
