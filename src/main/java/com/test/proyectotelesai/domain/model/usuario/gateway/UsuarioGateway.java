package com.test.proyectotelesai.domain.model.usuario.gateway;

import com.test.proyectotelesai.domain.model.usuario.UsuarioDTO;
import reactor.core.publisher.Mono;

public interface UsuarioGateway {
    Mono<UsuarioDTO> findByEmail(String email);
    Mono<UsuarioDTO> findByEmailOrUsername(String email, String username);
    Mono<UsuarioDTO> findById(Integer id);
}
