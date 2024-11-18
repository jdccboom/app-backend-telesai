create table telesai_services_db.bitacora
(
    idbitacora  serial
        primary key,
    idsolicitud integer      not null
        references telesai_services_db.solicitud
            on delete cascade,
    idpersona   integer      not null
        references telesai_services_db.usuario
            on delete cascade,
    fechaaccion timestamp    not null,
    accion      varchar(100) not null,
    detalle     varchar(255)
);

alter table telesai_services_db.bitacora
    owner to postgres;

create index idx_bitacora_solicitud
    on telesai_services_db.bitacora (idsolicitud);

create index idx_bitacora_fecha
    on telesai_services_db.bitacora (fechaaccion);

