package com.test.proyectotelesai.domain.model.rol.gateway;

import com.test.proyectotelesai.domain.model.rol.RolDTO;
import com.test.proyectotelesai.infrastructure.driven_adapters.repository.rol.UsuarioRolData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolGateway {

    Flux<RolDTO> getRolsByUsuario(Integer id);

    Mono<RolDTO> getRolById(Integer id);

    Mono<UsuarioRolData>updateUsuarioRol(Integer idUsuario, Integer idRol);

}
