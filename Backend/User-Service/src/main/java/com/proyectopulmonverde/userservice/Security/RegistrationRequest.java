package com.proyectopulmonverde.userservice.Security;


import com.proyectopulmonverde.userservice.Entities.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "Nombre Obligatorio")
    @NotBlank(message = "Nombre Obligatorio")
    private String firstname;
    @NotEmpty(message = "Apellido Obligatorio")
    @NotBlank(message = "Apellido Obligatorio")
    private String lastname;
    @Email(message = "Error en formato de Email")
    @NotEmpty(message = "Email Obligatorio")
    @NotBlank(message = "Email Obligatorio")
    private String email;
    @NotEmpty(message = "Contraseña Obligatoria")
    @NotBlank(message = "Contraseña Obligatoria")
    @Size(min = 8, message = "Contraseña demasiado corta")
    private String password;
    private String phone;

    private Role role;


}
