package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.rol.RolDTO;
import com.test.proyectotelesai.domain.model.rol.gateway.RolGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RolUseCase {

    private final RolGateway rolGateway;

    public Flux<RolDTO> getRolsByUsuario(Integer id) {
        return rolGateway.getRolsByUsuario(id);
    }

    public Mono<RolDTO> getRol(Integer id) {
        return rolGateway.getRolById(id);
    }

}