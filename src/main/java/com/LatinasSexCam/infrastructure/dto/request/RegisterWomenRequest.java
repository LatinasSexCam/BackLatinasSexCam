package com.LatinasSexCam.infrastructure.dto.request;

import com.LatinasSexCam.domain.model.WomenStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterWomenRequest {

    private String name;
    private String userName;

    @Email
    private String email;

    private String phoneNumber;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una letra mayúscula y un carácter especial")
    private String password;

    private String nationality;
    private Long idPackage;
}
