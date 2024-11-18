package com.test.proyectotelesai.infrastructure.entry_points.solicitudes;

import com.test.proyectotelesai.domain.model.solicitud.SolicitudDTO;
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

    @GetMapping("/disponible")
    public ResponseEntity<Object> verificarDisponibilidad5Dias() {
        return ResponseEntity.ok().body(solicitudUseCase.verificarDisponibilidad5Dias());
    }

    @GetMapping
    public ResponseEntity<Object> getAllSolicitudes(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok().body(solicitudUseCase.getSolicitudInfoByIdCliente((Integer)
                jwtUtils.parseJwt(token.replace("Bearer ", ""))
                        .getPayload().get("id")));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSolicituById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(solicitudUseCase.getSolicitudInfoById(id));
    }

    @GetMapping("/acta/{id}")
    public ResponseEntity<Object> genararActa(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(actaUseCase.generarActa(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> crearSolicitud(@RequestHeader("Authorization") String token,@RequestBody SolicitudDTO solicitudDTO) {
        return ResponseEntity.ok().body(solicitudUseCase.procesarSolicitud(solicitudDTO,
                (Integer) jwtUtils
                        .parseJwt(token.replace("Bearer ", ""))
                                .getPayload().get("id")));
    }

    @PostMapping("/validar/{id}")
    public ResponseEntity<Object> validarSolicitud(@RequestHeader("Authorization") String token,@PathVariable("id") Integer idSolicitud) {
        return ResponseEntity.ok().body(solicitudUseCase.validarActa(idSolicitud,
                (Integer)
                        jwtUtils.parseJwt(token.replace("Bearer ", ""))
                                .getPayload().get("id")));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> eliminarSolicitud(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(solicitudUseCase.deleteSolicitud(id));
    }
}
