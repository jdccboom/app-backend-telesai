create table telesai_services_db.solicitud
(
    idsolicitud     serial
        primary key,
    idcliente       integer not null
        references telesai_services_db.usuario
            on delete cascade,
    idubicacion     integer not null
        references telesai_services_db.ubicacion
            on delete cascade,
    idservicio      integer not null
        references telesai_services_db.servicio
            on delete cascade,
    idoperario      integer not null
        references telesai_services_db.usuario
            on delete cascade,
    idsupervisor    integer not null
        references telesai_services_db.usuario
            on delete cascade,
    fechasolicitud  date    not null,
    fecharevision   date,
    fechavalidacion timestamp,
    descripcion     varchar(255),
    idestado        integer not null
        references telesai_services_db.estado
            on delete cascade
);

alter table telesai_services_db.solicitud
    owner to postgres;

create index idx_solicitud_cliente
    on telesai_services_db.solicitud (idcliente);

create index idx_solicitud_ubicacion
    on telesai_services_db.solicitud (idubicacion);

