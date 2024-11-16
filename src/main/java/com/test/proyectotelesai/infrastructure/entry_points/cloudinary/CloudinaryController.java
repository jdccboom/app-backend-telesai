package com.test.proyectotelesai.infrastructure.entry_points.cloudinary;

import com.test.proyectotelesai.domain.usecase.ClodinaryUseCase;
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
@RequestMapping(value = "/file")
public class CloudinaryController {

    private final ClodinaryUseCase cloudinaryUseCase;

    @PostMapping("/update")
    public Mono<ResponseEntity<Object>> update(@RequestPart("file") Mono<FilePart> imagen) {
        return imagen.flatMap(cloudinaryUseCase::save) // Llama al servicio para guardar la imagen
                .map(response -> ResponseEntity.ok().body(new MensajeDTO<>(false, response)));
    }

    @DeleteMapping("/delete")
    public Mono<ResponseEntity<Object>> delete(@Valid @RequestBody Mono<FileParams> imageParams) {
        return imageParams
                .flatMap(cloudinaryUseCase::delete)
                .map(result -> ResponseEntity.ok().body(new MensajeDTO<>(false, result)));
    }
}
