package com.proyectopulmonverde.userservice.Security;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
public class ArticleRequest {

    @NotEmpty(message = "Nombre Obligatorio")
    @NotBlank(message = "Nombre Obligatorio")
    private String content;

    @NotEmpty(message = "Nombre Obligatorio")
    @NotBlank(message = "Nombre Obligatorio")
    private String title;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date date;


}
