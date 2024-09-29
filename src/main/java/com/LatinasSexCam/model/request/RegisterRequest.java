package com.LatinasSexCam.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterRequest {

    @NotNull
    private String user_name;

    @NotNull
    private String nacionality;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String gender;

    @Email
    private String email;

    @Pattern(regexp = ".*[A-Z].*", message = "La contraseña debe contener al menos una letra mayúscula")
    @Pattern(regexp = ".*[^a-zA-Z0-9].*", message = "La contraseña debe contener al menos un carácter especial")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;



}
