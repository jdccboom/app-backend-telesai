package com.test.proyectotelesai.domain.model.usuario.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginParams(
        @NotBlank(message = "Es necesario que ingrese el email")
        @Email String email,
        @NotBlank(message = "Es necesario que ingrese la contraseña")
        String password
) {
}