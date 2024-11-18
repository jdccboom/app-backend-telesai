package com.test.proyectotelesai.domain.model.usuario.gateway;

import com.test.proyectotelesai.domain.model.usuario.UsuarioDTO;
import com.test.proyectotelesai.domain.model.usuario.response.InfoUsuarioCrud;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioGateway {
    Mono<UsuarioDTO> findByEmail(String email);
    Mono<InfoUsuarioCrud> findUsuarioById(Integer id);
    Mono<UsuarioDTO> findById(Integer id);
    Flux<UsuarioDTO> findByRol(Integer rol);
    Flux<InfoUsuarioCrud> findAll();
    Mono<UsuarioDTO> save(UsuarioDTO usuario);
}
