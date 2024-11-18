package com.test.proyectotelesai.domain.model.solicitud.gateway;

import com.test.proyectotelesai.domain.model.solicitud.SolicitudDTO;
import com.test.proyectotelesai.domain.model.solicitud.response.DispobilidadResult;
import com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud.InfoActaData;
import com.test.proyectotelesai.domain.model.solicitud.response.SolicitudResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface SolicitudGateway {

    Mono<SolicitudDTO> getSolicitudById(Integer id);

    Flux<SolicitudDTO> getAllSolicituds();

    Mono<SolicitudDTO> saveSolicitud(SolicitudDTO dto);

    Mono<Boolean> verificarSolicitudesPorFecha(LocalDate fecha);

    Mono<SolicitudResult> getSolicitudInfoById(Integer id);

    Mono<InfoActaData> getInfoActaData(Integer id);

    Flux<SolicitudResult> getSolicitudInfoByIdCliente(Integer id);

    Flux<DispobilidadResult> verificarDisponibilidad5Dias();

}

