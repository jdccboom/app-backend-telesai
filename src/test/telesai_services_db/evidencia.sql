create table telesai_services_db.evidencia
(
    idevidencia  serial
        primary key,
    idsolicitud  integer      not null
        references telesai_services_db.solicitud
            on delete cascade,
    urlevidencia varchar(255) not null,
    tipo         varchar(20),
    idestado     integer
        constraint evidencia_estado_idestado_fk
            references telesai_services_db.estado
);

alter table telesai_services_db.evidencia
    owner to postgres;

