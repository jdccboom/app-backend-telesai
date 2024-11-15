package com.test.proyectotelesai.domain.model.solicitud.gateway;

import com.test.proyectotelesai.domain.model.solicitud.SolicitudDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SolicitudGateway {
    Mono<SolicitudDTO> getSolicitudById(Integer id);
    Flux<SolicitudDTO> getAllSolicituds();
    Mono<SolicitudDTO> saveSolicitud(SolicitudDTO dto);
    Mono<Boolean> existSolicitud(Integer id);
}
