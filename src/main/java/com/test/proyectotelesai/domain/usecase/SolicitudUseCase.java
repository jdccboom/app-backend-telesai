package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.solicitud.SolicitudDTO;
import com.test.proyectotelesai.domain.model.solicitud.gateway.SolicitudGateway;
import com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud.InfoActaData;
import com.test.proyectotelesai.infrastructure.driven_adapters.repository.solicitud.SolicitudResult;
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

    public Mono<SolicitudResult> getSolicitudInfoById(Integer id) {
        return solicitudGateway.getSolicitudInfoById(id);
    }

    public Mono<InfoActaData> getInfoActaData(Integer id) {
        return solicitudGateway.getInfoActaData(id);
    }

    public Flux<SolicitudResult> getSolicitudInfoByIdCliente(Integer id) {
        return solicitudGateway.getSolicitudInfoByIdCliente(id);
    }

    public Mono<SolicitudDTO> saveSolicitud(SolicitudDTO solicitudDTO){
        return solicitudGateway.saveSolicitud(solicitudDTO);
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

    public Mono<SolicitudDTO> updateEstadoSolicitud(Integer id, Integer estado){
        return solicitudGateway.getSolicitudById(id)
                .switchIfEmpty(Mono.error(
                        new Exception("No existe el solicitud con id: " + id)))
                .flatMap(SolicitudDTO -> {
                    SolicitudDTO.setIdEstado(estado);
                    return solicitudGateway.saveSolicitud(SolicitudDTO);
                });
    }

    public Mono<SolicitudDTO> deleteSolicitud(Integer id){
        return solicitudGateway.getSolicitudById(id)
                .switchIfEmpty(Mono.error(
                        new Exception("No existe el solicitud con id: " + id)))
                .flatMap(SolicitudDTO -> {
                    SolicitudDTO.setIdEstado(1);
                    return solicitudGateway.saveSolicitud(SolicitudDTO);
                });
    }
}
