package com.test.proyectotelesai.infrastructure.entry_points.solicitudes;

import com.test.proyectotelesai.domain.usecase.ServicioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/servicio")
public class ServicioController {

    private final ServicioUseCase servicioUseCase;

    @GetMapping
    public ResponseEntity<Object> getServicioAll() {
        return ResponseEntity.ok().body(servicioUseCase.getServicioAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getServicioById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(servicioUseCase.getServicioById(id));
    }
}
