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

    public  Flux<ObservacionDTO> getAllObservaciones() {
        return null;
    }

    public Flux<ObservacionDTO> getObservacionByIdSolictud(Integer id, Integer idsolicitud){
        return observacionGateway.getObservacionByIdSolictud(id,idsolicitud);
    }

    Mono<ObservacionDTO> createObservacion(ObservacionDTO observacion){
        return null;
    }

    Mono<ObservacionDTO> updateObservacion(ObservacionDTO observacion){
        return null;
    }

}
