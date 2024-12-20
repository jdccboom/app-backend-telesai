package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.servicio.ServicioDTO;
import com.test.proyectotelesai.domain.model.servicio.gateway.ServicioGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ServicioUseCase {

    private final ServicioGateway servicioGateway;

    public Mono<ServicioDTO> getServicioById(Integer id) {
        return servicioGateway.getServicio(id)
                .switchIfEmpty(Mono.error(new Exception("No se encontro ningun servicio con id: " + id)));
    }

    public Flux<ServicioDTO> getServicioAll() {
        return servicioGateway.getServicioAll();
    }

    public Mono<ServicioDTO> saveServicio(ServicioDTO servicio){
        return servicioGateway.saveServicio(
                ServicioDTO.builder()
                        .nombreServicio(servicio.getNombreServicio())
                        .idEstado(2)
                        .build()
                )
                .onErrorResume( throwable -> Mono.error(
                        new Exception("Error al guardar servicio: " + throwable.getMessage())));
    }

    public Mono<ServicioDTO> updateServicio(ServicioDTO servicio){
        return servicioGateway.getServicio(servicio.getIdServicio())
                .switchIfEmpty(Mono.error(
                        new Exception("No existe el servicio con id: " + servicio.getIdServicio())))
                .flatMap(servicioDTO -> {
                        servicioDTO.setNombreServicio(servicio.getNombreServicio());
                        return servicioGateway.saveServicio(servicioDTO);
                })
                .onErrorResume(throwable -> Mono.error(new Exception("Error al actualizar el servicio")));
    }

    public Mono<String> deleteServicio(Integer idServicio){
        return servicioGateway.getServicio(idServicio)
                .switchIfEmpty(Mono.error(
                        new Exception("No existe el servicio con id: " + idServicio)))
                .flatMap(servicioDTO -> {
                    servicioDTO.setIdEstado(1);
                    return servicioGateway.saveServicio(servicioDTO)
                            .thenReturn("Servicio eliminado correctamente");
                })
                .onErrorResume(throwable -> Mono.error(new Exception("Error eliminar el servicio")));
    }
}
