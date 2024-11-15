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
        return servicioGateway.getServicio(id);
    }

    public Flux<ServicioDTO> getServicioAll() {
        return servicioGateway.getServicioAll();
    }

    public Mono<ServicioDTO> saveServicio(ServicioDTO servicio){
        return servicioGateway.saveServicio(servicio)
                .onErrorResume( throwable -> Mono.error(new Exception("Error al guardar servicio")));
    }

    public Mono<ServicioDTO> updateServicio(ServicioDTO servicio){
        return servicioGateway.getServicio(servicio.getIdServicio())
                .switchIfEmpty(Mono.error(
                        new Exception("No existe el servicio con id: " + servicio.getIdServicio())))
                .flatMap(servicioDTO -> {
                        servicioDTO.setNombreServicio(servicio.getNombreServicio());
                        servicioDTO.setIdEstado(servicio.getIdEstado());
                        return servicioGateway.saveServicio(servicioDTO);
                })
                .onErrorResume(throwable -> Mono.error(new Exception("Error al actualizar el servicio")));
    }
}
