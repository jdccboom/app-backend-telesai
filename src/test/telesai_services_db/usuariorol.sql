create table telesai_services_db.usuariorol
(
    idusuario integer not null
        references telesai_services_db.usuario
            on delete cascade,
    idrol     integer not null
        references telesai_services_db.rol
            on delete cascade,
    primary key (idusuario, idrol)
);

alter table telesai_services_db.usuariorol
    owner to postgres;

