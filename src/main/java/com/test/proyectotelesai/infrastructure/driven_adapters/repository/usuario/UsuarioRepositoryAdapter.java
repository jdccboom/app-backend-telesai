package com.test.proyectotelesai.infrastructure.driven_adapters.repository.usuario;

import com.test.proyectotelesai.domain.model.usuario.UsuarioDTO;
import com.test.proyectotelesai.domain.model.usuario.gateway.UsuarioGateway;
import com.test.proyectotelesai.infrastructure.helpers.utils.AdapterOperationsReactive;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UsuarioRepositoryAdapter
        extends AdapterOperationsReactive<UsuarioDTO, UsuarioData, Integer, UsuarioRepository>
        implements UsuarioGateway {

    @Autowired
    protected UsuarioRepositoryAdapter(UsuarioRepository repository, ObjectMapper mapper) {
        super(repository, mapper,
                d -> mapper.mapBuilder(d, UsuarioDTO.UsuarioDTOBuilder.class).build());
    }

    @Override
    public Mono<UsuarioDTO> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(usuarioData ->mapper.map(usuarioData, UsuarioDTO.class));
    }

    @Override
    public Mono<UsuarioDTO> findByEmailOrUsername(String email, String username) {
        return null;
    }

}
