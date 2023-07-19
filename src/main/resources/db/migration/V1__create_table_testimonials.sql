CREATE TABLE testimonials
(
    id bigint not null auto_increment,
    personName varchar(150) not null,
    testimonialText varchar(1000) not null,
    imagePath varchar(255) not null unique,
    active tinyint not null,

    primary key(id)
);