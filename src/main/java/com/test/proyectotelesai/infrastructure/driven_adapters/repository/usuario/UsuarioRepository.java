package com.test.proyectotelesai.infrastructure.driven_adapters.repository.usuario;


import com.test.proyectotelesai.domain.model.usuario.UsuarioDTO;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UsuarioRepository extends
        ReactiveCrudRepository<UsuarioData, Integer>, ReactiveQueryByExampleExecutor<UsuarioData> {

    Mono<UsuarioData> findByEmail(String email);
    Mono<UsuarioDTO> findByEmailOrUsername(String email, String username);
}
