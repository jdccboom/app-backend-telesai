package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.rol.RolDTO;
import com.test.proyectotelesai.domain.model.usuario.UsuarioDTO;
import com.test.proyectotelesai.domain.model.usuario.response.InfoUsuarioResult;
import com.test.proyectotelesai.domain.model.usuario.resquet.LoginParams;
import com.test.proyectotelesai.domain.model.usuario.gateway.UsuarioGateway;
import com.test.proyectotelesai.infrastructure.helpers.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static com.test.proyectotelesai.infrastructure.helpers.utils.Constante.ERROR_EMAIL_NO_EXISTE;

@RequiredArgsConstructor
public class AutenticacionUseCase {

    private final UsuarioGateway usuarioGateway;

    private final RolUseCase rolUseCase;

    private final JWTUtils jwtUtils;

    public Mono<InfoUsuarioResult> login(LoginParams loginParams) {

        return usuarioGateway.findByEmail(loginParams.email())
                .switchIfEmpty(Mono.error(new Exception(ERROR_EMAIL_NO_EXISTE.getTexto())))
                .flatMap(usuarioDTO -> {
                    /*BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                    if( !passwordEncoder.matches(loginDTO.password(), usuarioDTO.getPassword()) ) {
                        return Mono.error(new Exception("La contraseña es incorrecta"));
                    }*/

                    return rolUseCase.getRolsByUsuario(usuarioDTO.getIdusuario())
                            .map(RolDTO::getNombreRol)
                            .collectList()
                            .flatMap(rolDTO -> {
                                       Map<String, Object> map = new HashMap<>();
                                       map.put("rol",rolDTO);

                                       map.put("name", usuarioDTO.getNombrecompleto());
                                       map.put("nickname", usuarioDTO.getUsername());
                                       map.put("id", usuarioDTO.getIdusuario());
                                       return Mono.just(InfoUsuarioResult.builder()
                                               .email(usuarioDTO.getEmail())
                                               .nombrecompleto(usuarioDTO.getNombrecompleto())
                                               .cedula(usuarioDTO.getCedula())
                                               .telefono(usuarioDTO.getTelefono())
                                               .idusuario(usuarioDTO.getIdusuario())
                                               .token(jwtUtils.generateToken(usuarioDTO.getEmail(), map))
                                               .build());
                            });
                });
    }


    public Mono<UsuarioDTO> register(LoginParams loginParams) {

        return usuarioGateway.findByEmail(loginParams.email())
                .switchIfEmpty(
                        usuarioGateway.findByEmail(loginParams.email())
                );
    }
}
