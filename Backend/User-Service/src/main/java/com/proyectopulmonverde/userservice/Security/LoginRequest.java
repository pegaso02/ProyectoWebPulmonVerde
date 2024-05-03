package com.proyectopulmonverde.userservice.Security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {

    @Email(message = "Error en formato de Email")
    @NotBlank(message = "Email Obligatorio")
    private String email;
    @Size(min = 8, message = "Contraseña demasiado corta")
    private String password;

}
