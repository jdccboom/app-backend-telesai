package com.test.proyectotelesai.infrastructure.entry_points.solicitudes;

import com.test.proyectotelesai.domain.model.servicio.ServicioDTO;
import com.test.proyectotelesai.domain.model.solicitud.SolicitudDTO;
import com.test.proyectotelesai.domain.usecase.ServicioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/save")
    public ResponseEntity<Object> saveServicio(@RequestBody ServicioDTO servicio) {
        return ResponseEntity.ok().body(servicioUseCase.saveServicio(servicio));
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateServicio(@RequestBody ServicioDTO servicio) {
        return ResponseEntity.ok().body(servicioUseCase.updateServicio(servicio));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteServicio(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(servicioUseCase.deleteServicio(id));
    }
}
