CREATE TABLE destinations
(
    id bigint not null auto_increment,
    name varchar(100) not null,
    photoPath varchar(255) not null,
    active tinyint not null,

    primary key(id)
);