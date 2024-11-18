create table telesai_services_db.ubicacion
(
    idubicacion     serial
        primary key,
    nombreubicacion varchar(100) not null,
    direccion       varchar(255) not null,
    idcliente       integer      not null
        references telesai_services_db.usuario
            on delete cascade
);

alter table telesai_services_db.ubicacion
    owner to postgres;

