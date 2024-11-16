package com.test.proyectotelesai.infrastructure.entry_points.solicitudes;

import com.test.proyectotelesai.domain.usecase.ObservacionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/observacion")
public class ObservacionController {

    private final ObservacionUseCase observacionUseCase;

    @GetMapping
    public ResponseEntity<Object> getObservacionAll() {
        return ResponseEntity.ok().body(observacionUseCase.getAllObservaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getObservacionById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(observacionUseCase.getObservacionByIdSolictud(id));
    }
}
