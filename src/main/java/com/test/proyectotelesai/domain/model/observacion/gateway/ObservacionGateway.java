package com.test.proyectotelesai.domain.model.observacion.gateway;

import com.test.proyectotelesai.domain.model.observacion.ObservacionDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ObservacionGateway {
    Flux<ObservacionDTO> getAllObservaciones();

    Flux<ObservacionDTO> getObservacionByIdSolictud(Integer id, Integer idsolicitud);

    Mono<ObservacionDTO> saveObservacion(ObservacionDTO observacion);

    Mono<ObservacionDTO> getObservacionById(Integer id);

}
