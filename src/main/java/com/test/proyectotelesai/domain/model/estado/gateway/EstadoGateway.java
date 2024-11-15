package com.test.proyectotelesai.domain.model.estado.gateway;

import com.test.proyectotelesai.domain.model.estado.EstadoDTO;
import reactor.core.publisher.Mono;

public interface EstadoGateway {
    Mono<EstadoDTO> getEstadoById(Integer id);
}
