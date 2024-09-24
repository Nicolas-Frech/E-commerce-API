create table products(

    id bigint not null auto_increment,
    name varchar(100) not null,
    description varchar(300) not null,
    price varchar(100) not null,
    quantity varchar(100) not null,
    active tinyint,

    primary key(id)

);