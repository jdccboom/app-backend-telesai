package com.test.proyectotelesai.domain.model.ubicacion.gateway;

import com.test.proyectotelesai.domain.model.ubicacion.UbicacionDTO;
import reactor.core.publisher.Flux;

public interface UbicacionGateway {
    Flux<UbicacionDTO> getAllUbicacion();
}
