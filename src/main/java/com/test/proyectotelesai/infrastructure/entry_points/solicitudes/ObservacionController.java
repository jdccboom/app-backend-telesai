package com.test.proyectotelesai.infrastructure.entry_points.solicitudes;

import com.test.proyectotelesai.domain.model.observacion.ObservacionDTO;
import com.test.proyectotelesai.domain.model.observacion.request.ObservacionSaveParams;
import com.test.proyectotelesai.domain.usecase.ObservacionUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> getObservacionById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(observacionUseCase.getObservacionById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Object> saveObservacion(@Valid @RequestBody ObservacionSaveParams observacion) {
        return ResponseEntity.ok().body(observacionUseCase.createObservacion(observacion));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateObservacion(@RequestBody ObservacionDTO observacionDTO) {
        return ResponseEntity.ok().body(observacionUseCase.updateObservacion(observacionDTO));
    }

}
