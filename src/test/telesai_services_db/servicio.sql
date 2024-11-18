create table telesai_services_db.servicio
(
    idservicio     serial
        primary key,
    nombreservicio varchar(100) not null,
    idestado       integer
        constraint servicio_estado_idestado_fk
            references telesai_services_db.estado
);

alter table telesai_services_db.servicio
    owner to postgres;

