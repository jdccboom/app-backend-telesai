package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.observacion.ObservacionDTO;
import com.test.proyectotelesai.domain.model.observacion.gateway.ObservacionGateway;
import com.test.proyectotelesai.domain.model.observacion.request.ObservacionSaveParams;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class ObservacionUseCase {

    private final ObservacionGateway observacionGateway;

    private final SolicitudUseCase solicitudUseCase;

    public  Flux<ObservacionDTO> getAllObservaciones() {
        return observacionGateway.getAllObservaciones();
    }

    public Flux<ObservacionDTO> getObservacionByIdSolictud(Integer id, Integer idsolicitud){
        return observacionGateway.getObservacionByIdSolictud(id,idsolicitud)
                .switchIfEmpty(Mono.error(new Exception("No se encontro una observacion con el id: " + id)));
    }

    public Mono<ObservacionDTO> getObservacionById(Integer id){
        return observacionGateway.getObservacionByIdSolictud(id,0)
                .switchIfEmpty(Mono.error(new Exception("No se encontro una observacion con el id: " + id)))
                .collectList()
                .flatMap(list -> Mono.just(list.get(0)));
    }

    public Mono<ObservacionDTO> createObservacion(ObservacionSaveParams observacion){
        return observacionGateway.saveObservacion(
                ObservacionDTO.builder()
                        .idSolicitud(observacion.idSolicitud())
                        .descripcion(observacion.descripcion())
                        .fechaObservacion(LocalDateTime.now())
                        .build()
                )
                .flatMap(observacionDTO -> solicitudUseCase.updateEstadoSolicitud(
                            observacionDTO.getIdSolicitud(), 4).thenReturn(observacionDTO)
                ).onErrorResume(error ->
                        Mono.error(new Exception("Error al guardar observacion: " + error.getMessage())));
    }

    public Mono<ObservacionDTO> updateObservacion(ObservacionDTO observacion){
        return observacionGateway.getObservacionById(observacion.getIdObservacion())
                .switchIfEmpty(Mono.error(new Exception("Observacion no existe")))
                .flatMap(observacionDTO -> {
                    observacionDTO.setDescripcion(observacion.getDescripcion());
                    return observacionGateway.saveObservacion(observacionDTO);
                }).onErrorResume(error ->
                        Mono.error(new Exception("Error al actualizar la observacion: " + error.getMessage())));
    }

}
