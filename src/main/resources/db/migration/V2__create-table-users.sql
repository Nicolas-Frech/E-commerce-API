create table users(

    id bigint not null auto_increment,
    username varchar(100) not null,
    password varchar(300) not null,
    products_id bigint,
    active tinyint,

    primary key(id),
    constraint fk_users_products_id foreign key(products_id) references products(id)

);