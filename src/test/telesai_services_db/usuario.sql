create table telesai_services_db.usuario
(
    idusuario      serial
        primary key,
    username       varchar(50)  not null
        unique,
    password       varchar(100) not null,
    nombrecompleto varchar(100) not null,
    cedula         varchar(20)  not null
        unique,
    telefono       varchar(10)  not null,
    email          varchar(30)  not null,
    idestado       integer
        constraint usuario_estado_fk
            references telesai_services_db.estado
);

alter table telesai_services_db.usuario
    owner to postgres;

