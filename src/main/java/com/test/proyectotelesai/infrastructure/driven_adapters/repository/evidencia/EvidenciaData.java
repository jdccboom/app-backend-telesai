package com.test.proyectotelesai.infrastructure.driven_adapters.repository.evidencia;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Builder
@Table(name = "evidencia", schema = "telesai_services_db")
@NoArgsConstructor
@AllArgsConstructor
public class EvidenciaData {

    @Id
    @Column( value = "idevidencia")
    private int idEvidencia;

    @Column( value = "idsolicitud")
    private int idSolicitud;

    @Column( value = "urlevidencia")
    private String urlEvidencia;

    @Column( value = "tipo")
    private String tipoEvidencia;
}
