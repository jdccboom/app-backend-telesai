package com.test.proyectotelesai.domain.model.observacion.gateway;

import com.test.proyectotelesai.domain.model.observacion.ObservacionDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ObservacionGateway {
    Flux<ObservacionDTO> getAllObservaciones();

    Flux<ObservacionDTO> getObservacionByIdSolictud(Integer id, String descripcion);

    Mono<ObservacionDTO> saveObservacion(ObservacionDTO observacion);
}
