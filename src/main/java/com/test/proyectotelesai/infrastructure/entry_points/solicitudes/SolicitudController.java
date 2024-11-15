package com.test.proyectotelesai.infrastructure.entry_points.solicitudes;

import com.test.proyectotelesai.domain.usecase.SolicitudUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/solicitud")
public class SolicitudController {

    private final SolicitudUseCase solicitudUseCase;

    @GetMapping
    public ResponseEntity<Object> getAllSolicitudes() {
        return ResponseEntity.ok().body(solicitudUseCase.getSolicitudAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSolicituById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(solicitudUseCase.getSolicitudById(id));
    }
}
