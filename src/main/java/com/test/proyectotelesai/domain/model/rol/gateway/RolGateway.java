package com.test.proyectotelesai.domain.model.rol.gateway;

import com.test.proyectotelesai.domain.model.rol.RolDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolGateway {

    Flux<RolDTO> getRolsByUsuario(Integer id);

    Mono<RolDTO> getRolById(Integer id);
}
