create table tbl_product(
    id int auto_increment,
    name varchar(45),
    price double,
    stock int,
    category varchar(45),
    created_at date,
    updated_at date,
    constraint pk_tbl_product_id primary key(id)
);

create table tbl_agent(
    agent_code varchar(45) not null unique,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(60),
    phone_number varchar(60),
    home_phone_number varchar(60),
    address_name varchar(100),
    date_of_birth date not null,
    created_at date not null,
    updated_at date not null,
    constraint pk_tbl_agen_id primary key(agent_code)
);

insert into tbl_agent(agent_code, first_name, last_name, email, phone_number, home_phone_number,address_name, date_of_birth, created_at, updated_at)
values ('GEN002', 'irma', 'khairunnisa', 'irmakhairunnisa@gmail.com', '087898929291', '7658782821', 'cimanggis depok', curdate(), curdate(), curdate());

--insert into tbl_product(name, price, stock, category, created_at, updated_at)
--values ('maestro optima care', 300000, 1, 'kesehatan', curdate(), curdate());