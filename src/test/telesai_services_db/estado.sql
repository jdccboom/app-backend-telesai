create table telesai_services_db.estado
(
    idestado     serial
        primary key,
    nombreestado varchar(50)  not null,
    descripcion  varchar(250) not null
);

alter table telesai_services_db.estado
    owner to postgres;

