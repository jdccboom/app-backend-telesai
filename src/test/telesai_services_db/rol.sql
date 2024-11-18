create table telesai_services_db.rol
(
    idrol     serial
        primary key,
    nombrerol varchar(50) not null
);

alter table telesai_services_db.rol
    owner to postgres;

