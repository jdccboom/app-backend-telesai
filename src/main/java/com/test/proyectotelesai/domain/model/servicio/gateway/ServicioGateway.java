package com.test.proyectotelesai.domain.model.servicio.gateway;

import com.test.proyectotelesai.domain.model.servicio.ServicioDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ServicioGateway {

    Mono<ServicioDTO> getServicio(Integer id);
    Flux<ServicioDTO> getServicioAll();
    Mono<ServicioDTO> saveServicio(ServicioDTO servicio);
}
