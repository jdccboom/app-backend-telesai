package com.test.proyectotelesai.infrastructure.driven_adapters.service.cloudinary;

import jakarta.validation.constraints.NotBlank;

public record ImageParams(
        @NotBlank String id,
        String url
) {
}