package com.test.proyectotelesai.domain.model.observacion.request;

import jakarta.validation.constraints.NotNull;

public record ObservacionSaveParams(
        @NotNull
        Integer idSolicitud,
        String descripcion
){}
