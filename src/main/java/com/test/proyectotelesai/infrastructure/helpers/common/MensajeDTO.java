package com.test.proyectotelesai.infrastructure.helpers.common;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
