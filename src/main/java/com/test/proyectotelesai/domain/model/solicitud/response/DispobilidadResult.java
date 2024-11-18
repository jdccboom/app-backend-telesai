package com.test.proyectotelesai.domain.model.solicitud.response;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DispobilidadResult {

    @Column
    private LocalDate fecha;

    @Column
    private Boolean disponible;

}
