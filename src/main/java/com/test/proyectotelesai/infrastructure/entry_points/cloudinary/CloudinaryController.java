package com.test.proyectotelesai.infrastructure.entry_points.cloudinary;

import com.test.proyectotelesai.infrastructure.driven_adapters.service.cloudinary.ImageParams;
import com.test.proyectotelesai.infrastructure.driven_adapters.service.cloudinary.CloudinaryService;
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

    private final CloudinaryService cloudinaryService;

    @PostMapping("/update")
    public Mono<ResponseEntity<Object>> update(@RequestPart("file") Mono<FilePart> imagen) {
        return imagen.flatMap(cloudinaryService::save) // Llama al servicio para guardar la imagen
                .map(response -> ResponseEntity.ok().body(new MensajeDTO<>(false, response)));
    }

    @DeleteMapping("/delete")
    public Mono<ResponseEntity<Object>> delete(@Valid @RequestBody Mono<ImageParams> imageParams) {
        return imageParams
                .flatMap(params -> cloudinaryService.delete(params.id()))
                .map(result -> ResponseEntity.ok().body(new MensajeDTO<>(false, result)));
    }
}
