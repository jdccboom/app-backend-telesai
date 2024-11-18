package com.test.proyectotelesai.infrastructure.driven_adapters.repository.rol;

import com.test.proyectotelesai.domain.model.rol.RolDTO;
import com.test.proyectotelesai.domain.model.rol.gateway.RolGateway;
import com.test.proyectotelesai.infrastructure.helpers.utils.AdapterOperationsReactive;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class RolRepositoryAdapter
        extends AdapterOperationsReactive<RolDTO, RolData, Integer, RolRepository>
        implements RolGateway {

    @Autowired
    protected RolRepositoryAdapter(RolRepository repository, ObjectMapper mapper) {
        super(repository,
                mapper,
                d -> mapper.mapBuilder(d, RolDTO.RolDTOBuilder.class).build());
    }

    @Override
    public Flux<RolDTO> getRolsByUsuario(Integer id) {
        return repository.getRolByIdUsuario(id)
                .map(rolData -> mapper.map(rolData, RolDTO.class));
    }

    @Override
    public Mono<RolDTO> getRolById(Integer id) {
        return repository.findByIdRol(id)
                .map(rolData -> mapper.map(rolData, RolDTO.class));
    }

    @Override
    public Mono<UsuarioRolData> updateUsuarioRol(Integer idUsuario, Integer idRol) {
        return repository.updateUsuarioRol(idUsuario,idRol);
    }
}
