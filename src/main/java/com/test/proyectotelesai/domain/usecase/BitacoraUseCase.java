package com.test.proyectotelesai.domain.usecase;

import com.test.proyectotelesai.domain.model.bitacora.BitacoraDTO;
import com.test.proyectotelesai.domain.model.bitacora.gateway.BitacoraGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BitacoraUseCase {

    private final BitacoraGateway bitacoraGateway;

    public Flux<BitacoraDTO> getBitacoraByIdSolicitud(Integer id) {
        return bitacoraGateway.getBitacoraByIdSolicitud(id);
    }
}
