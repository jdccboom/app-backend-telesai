package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.infrastructure.driven_adapters.service.cloudinary.CloudinaryGateway;
import com.test.proyectotelesai.infrastructure.driven_adapters.service.cloudinary.FileParams;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.Map;

@RequiredArgsConstructor
public class ClodinaryUseCase {

    private final CloudinaryGateway cloudinaryGateway;

    public Mono<Map> save(FilePart image){
        return cloudinaryGateway.save(image);
    }

    public Mono<Map> savePdf(File image){
        return cloudinaryGateway.savePdf(image);
    }

    public Mono<Map> delete(FileParams image) {
        return cloudinaryGateway.delete(image.getId());
    }
}
