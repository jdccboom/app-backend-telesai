package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.usuario.UsuarioDTO;
import com.test.proyectotelesai.domain.model.usuario.gateway.UsuarioGateway;
import com.test.proyectotelesai.domain.model.usuario.response.InfoUsuarioCrud;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    private final RolUseCase rolUseCase;

    public Flux<InfoUsuarioCrud> getUsuarioAll() {
        return usuarioGateway.findAll();
    }

    public Mono<String> deleteUsuario(Integer id) {
        return usuarioGateway.findById(id)
                .switchIfEmpty(Mono.error(new Exception("No existe el servicio con id: " + id)))
                .flatMap(usuarioDTO -> {
                    usuarioDTO.setIdEstado(1);
                    return usuarioGateway.save(usuarioDTO)
                            .thenReturn("Usuario eliminado correctamente");
                })
                .onErrorResume(throwable -> Mono.error(new Exception("Error eliminar el usuario")));
    }

    public Mono<UsuarioDTO> saveUsuario(UsuarioDTO usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioGateway.save(usuario)
                .flatMap( user ->
                rolUseCase.asignarRol(user.getIdusuario(),1)
                .thenReturn(user)
                .onErrorResume( throwable -> Mono.error(new Exception("Error al guardar servicio: "+throwable.getMessage()))));
    }

    public Mono<InfoUsuarioCrud> getUsuarioById(Integer id) {
        return usuarioGateway.findUsuarioById(id)
                .switchIfEmpty(Mono.error(new Exception("No existe el usuario con id: " + id)));
    }

    public Flux<UsuarioDTO> getUsuarioByRol(Integer id) {
        return usuarioGateway.findByRol(id)
                .switchIfEmpty(Mono.error(new Exception("No existe el usuario con id: " + id)));
    }

    public Mono<UsuarioDTO> updateUsuario(UsuarioDTO usuario){
        return usuarioGateway.findById(usuario.getIdusuario())
                .switchIfEmpty(Mono.error(
                        new Exception("No existe el servicio con id: " + usuario.getIdusuario())))
                .flatMap(usuarioDTO -> {

                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                    if( !passwordEncoder.matches(usuario.getPassword(),usuarioDTO.getPassword()) ) {
                        usuarioDTO.setPassword(passwordEncoder.encode(usuario.getPassword()));
                    }
                    usuarioDTO.setNombrecompleto(usuario.getNombrecompleto());
                    usuarioDTO.setTelefono(usuario.getTelefono());
                    usuarioDTO.setCedula(usuario.getCedula());
                    return usuarioGateway.save(usuarioDTO);
                })
                .onErrorResume(throwable -> Mono.error(new Exception("Error al actualizar el servicio: " + throwable.getMessage())));
    }
}
