package com.test.proyectotelesai.infrastructure.driven_adapters.service.cloudinary;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FileParams{
    @NotBlank
    private String id;

    private String url;
}