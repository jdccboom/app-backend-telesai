package com.test.proyectotelesai.infrastructure.entry_points.solicitudes;

import com.test.proyectotelesai.domain.usecase.ActaUseCase;
import com.test.proyectotelesai.domain.usecase.SolicitudUseCase;
import com.test.proyectotelesai.infrastructure.helpers.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/solicitud")
public class SolicitudController {

    private final SolicitudUseCase solicitudUseCase;

    private final ActaUseCase actaUseCase;

    private final JWTUtils jwtUtils;

    @GetMapping
    public ResponseEntity<Object> getAllSolicitudes(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(solicitudUseCase.getSolicitudInfoByIdCliente((Integer)
                jwtUtils.parseJwt(token.replace("Bearer ", ""))
                        .getPayload().get("id")));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSolicituById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(solicitudUseCase.getSolicitudInfoById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> genararActa(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(actaUseCase.generarActa(id));
    }
}
