create table telesai_services_db.observacion
(
    idobservacion    integer default nextval('telesai_services_db.observacion_idobservacion_seq'::regclass) not null
        primary key,
    idsolicitud      bigint                                                                                 not null
        references telesai_services_db.solicitud
            on delete cascade,
    descripcion      varchar(255)                                                                           not null,
    fechaobservacion timestamp                                                                              not null
);

alter table telesai_services_db.observacion
    owner to postgres;

