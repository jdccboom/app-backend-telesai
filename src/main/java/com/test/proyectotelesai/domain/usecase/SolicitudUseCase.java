package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.solicitud.SolicitudDTO;
import com.test.proyectotelesai.domain.model.solicitud.gateway.SolicitudGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SolicitudUseCase {

    private final SolicitudGateway solicitudGateway;

    public Mono<SolicitudDTO> getSolicitudById(Integer id) {
        return solicitudGateway.getSolicitudById(id);
    }

    public Flux<SolicitudDTO> getSolicitudAll() {
        return solicitudGateway.getAllSolicituds();
    }

    public Mono<SolicitudDTO> saveSolicitud(SolicitudDTO solicitudDTO){
        return solicitudGateway.saveSolicitud(
                SolicitudDTO.builder()
                        .idCliente(solicitudDTO.getIdCliente())
                        .idEstado(solicitudDTO.getIdEstado())
                        .idServicio(solicitudDTO.getIdServicio())
                        .build());
    }

    public Mono<SolicitudDTO> updateSolicitud(SolicitudDTO solicitud){
        return solicitudGateway.getSolicitudById(solicitud.getIdSolicitud())
                .switchIfEmpty(Mono.error(
                        new Exception("No existe el solicitud con id: " + solicitud.getIdSolicitud())))
                .flatMap(SolicitudDTO -> {
                        SolicitudDTO.setIdCliente(solicitud.getIdCliente());
                        SolicitudDTO.setIdServicio(solicitud.getIdServicio());
                        return solicitudGateway.saveSolicitud(SolicitudDTO);
                });
    }
}
