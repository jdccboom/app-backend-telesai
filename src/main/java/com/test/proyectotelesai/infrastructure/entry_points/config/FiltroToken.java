package com.test.proyectotelesai.infrastructure.entry_points.config;

import com.test.proyectotelesai.domain.model.rol.RolDTO;
import com.test.proyectotelesai.domain.usecase.RolUseCase;
import com.test.proyectotelesai.infrastructure.helpers.common.MensajeDTO;
import com.test.proyectotelesai.infrastructure.helpers.utils.JWTUtils;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FiltroToken implements WebFilter {

    private final JWTUtils jwtUtils;

    private ServerWebExchange exchange;

    private WebFilterChain chain;

    private String requestURI;

    private String token;

    private String tipoToken;

    private String mensajeError;

    private final RolUseCase rolUseCase;

    private final Environment env;

    private  String rol;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        this.tipoToken = "none";

        this.exchange = exchange;

        this.chain = chain;

        this.requestURI = exchange.getRequest().getURI().getPath();

        this.token = getToken(exchange.getRequest());

        agregarCabecerasCORS();

        if (HttpMethod.OPTIONS.equals(exchange.getRequest().getMethod())) {
            exchange.getResponse().setStatusCode(HttpStatus.OK);
            return Mono.empty();
        }

        if (tipoToken.equals("Bearer ")) {
            return validarToken();
        }

        if (Objects.equals(
                exchange.getRequest()
                        .getHeaders()
                        .getFirst("Authorization"),
                env.getProperty("user.encrip"))
                || requestURI.startsWith("/api/v1/auth")) {
            return chain.filter(exchange);
        }

        return crearRespuestaError(
                "No tiene permisos para acceder a este recurso",
                    HttpStatus.FORBIDDEN, exchange);
    }

    private Mono<Void> validarToken() {
        boolean error = true;
        boolean exception = false;

        try {
            // Validación de permisos según la URI y el rol del token
            if (requestURI.startsWith("/api/v1")) {
                error = validarRol("Cliente");
            } else if (requestURI.startsWith("/api/moderator")) {
                error = validarRol( "Admin");
            } else {
                error = false;
            }
        } catch (MalformedJwtException | SignatureException e) {
            mensajeError = "El token es incorrecto";
            exception = true;
        } catch (ExpiredJwtException e) {
            mensajeError = "El token está vencido";
            exception = true;
        } catch (Exception e) {
            mensajeError = e.getMessage();
            exception = true;
        }

        return validarExcepciones(exception, error);
    }

    private Mono<Void> validarExcepciones (Boolean exception, Boolean error){
        if(exception)
            return crearRespuestaError(mensajeError,
                    HttpStatus.INTERNAL_SERVER_ERROR, exchange);

        return validarError(error);
    }

    private Mono<Void> validarError (Boolean error){

        if(error)
            return crearRespuestaError("No tiene permisos para acceder a este recurso",
                    HttpStatus.FORBIDDEN, exchange);

        return validarRolUsuario();
    }

    private Mono <Void> validarRolUsuario (){

        Integer id= (Integer)jwtUtils.parseJwt(token).getPayload().get("id");
        return rolUseCase.getRolsByUsuario(id)
                .map(RolDTO::getNombreRol)
                .collectList()
                .flatMap(listRol -> {

                    if(!listRol.contains(rol))
                        return crearRespuestaError(
                                "No tiene permisos para acceder a este recurso",
                                        HttpStatus.FORBIDDEN, exchange
                        );

                    return chain.filter(exchange);
                });
    }

    private void agregarCabecerasCORS() {
        exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "Origin, Accept, Content-Type, Authorization");
    }

    private boolean validarRol(String rolEsperado) {
        Jws<Claims> jws = jwtUtils.parseJwt(token);
        @SuppressWarnings("unchecked")
        List<String> rolesActuales = (List<String>) jws.getPayload().get("rol");

        if (!rolesActuales.contains(rolEsperado)) {
            return true;
        }

        rol= rolEsperado;
        return false;
    }

    private String getToken(ServerHttpRequest request) {
        String header = request.getHeaders().getFirst("Authorization");

        if (header == null || !header.startsWith("Bearer "))
            return null;

        tipoToken = "Bearer ";
        return header.replace("Bearer ", "");
    }

    private Mono<Void> crearRespuestaError(String mensaje,
                                           HttpStatus codigoError, ServerWebExchange exchange) {

        MensajeDTO<String> dto = new MensajeDTO<>(true, mensaje);
        exchange.getResponse().setStatusCode(codigoError);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return exchange.getResponse().writeWith(Mono.just(exchange
                .getResponse().bufferFactory().wrap(toJson(dto).getBytes())));
    }

    private String toJson(MensajeDTO<String> dto) {
        try {
            return new ObjectMapper().writeValueAsString(dto);
        } catch (Exception e) {
            return "{\"error\":\"Error al convertir el mensaje\"}";
        }
    }
}
