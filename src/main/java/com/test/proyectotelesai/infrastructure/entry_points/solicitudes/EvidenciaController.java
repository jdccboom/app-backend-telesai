package com.test.proyectotelesai.infrastructure.entry_points.solicitudes;

import com.test.proyectotelesai.domain.usecase.ClodinaryUseCase;
import com.test.proyectotelesai.domain.usecase.EvidenciaUseCase;
import com.test.proyectotelesai.infrastructure.driven_adapters.service.cloudinary.FileParams;
import com.test.proyectotelesai.infrastructure.helpers.common.MensajeDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/evidencia")
public class EvidenciaController {

    private final EvidenciaUseCase evidenciaUseCase;

    @PostMapping("/add/{id}")
    public Mono<ResponseEntity<Object>> update(@RequestPart("file") Mono<FilePart> imagen, @PathVariable("id") int id) {
        return imagen.flatMap(filePart -> evidenciaUseCase.save(filePart,id)) // Llama al servicio para guardar la imagen
                .map(response -> ResponseEntity.ok().body(new MensajeDTO<>(false, response)));
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Object>> delete(@PathVariable("id") int id) {
        return evidenciaUseCase.delete(id)
                .map(result -> ResponseEntity.ok().body(new MensajeDTO<>(false, result)));
    }

}
