package com.test.proyectotelesai.domain.model.solicitud.gateway;

import com.test.proyectotelesai.domain.model.solicitud.SolicitudDTO;
import com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud.InfoActaData;
import com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud.SolicitudResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SolicitudGateway {

    Mono<SolicitudDTO> getSolicitudById(Integer id);

    Flux<SolicitudDTO> getAllSolicituds();

    Mono<SolicitudDTO> saveSolicitud(SolicitudDTO dto);

    Mono<SolicitudResult> getSolicitudInfoById(Integer id);

    Mono<InfoActaData> getInfoActaData(Integer id);

    Flux<SolicitudResult> getSolicitudInfoByIdCliente(Integer id);
}
