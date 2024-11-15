package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.observacion.ObservacionDTO;
import com.test.proyectotelesai.domain.model.observacion.gateway.ObservacionGateway;
import com.test.proyectotelesai.domain.model.solicitud.SolicitudDTO;
import com.test.proyectotelesai.domain.model.solicitud.gateway.SolicitudGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ObservacionUseCase {

    private final ObservacionGateway observacionGateway;

    Flux<ObservacionDTO> getAllObservaciones() {
        return null;
    }

    Mono<ObservacionDTO> getObservacionByIdSolictud(Integer id){
        return observacionGateway.getObservacionByIdSolictud(id);
    }

    Mono<ObservacionDTO> createObservacion(ObservacionDTO observacion){
        return null;
    }

    Mono<ObservacionDTO> updateObservacion(ObservacionDTO observacion){
        return null;
    }
}
