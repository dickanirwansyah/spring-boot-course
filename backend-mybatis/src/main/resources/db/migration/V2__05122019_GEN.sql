create table tbl_agent(
    agent_code varchar(45) not null unique,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(60),
    phone_number varchar(60),
    home_phone_number varchar(60),
    address_name varchar(100),
    company_name varchar(45),
    date_of_birth date not null,
    status boolean not null,
    created_at date not null,
    updated_at date not null,
    constraint pk_tbl_agen_id primary key(agent_code)
);

create table tbl_status_leads(
    status_leads varchar(45) primary key,
    description varchar(45)
);

insert into tbl_status_leads(status_leads, description) values
('l001', 'waiting aproval'),('l002', 'aproval denied');


create table tbl_leads(
    id int auto_increment,
    first_name varchar(45),
    last_name varchar(45),
    dob date,
    phone varchar(14),
    status_leads_id varchar(45),
    constraint pk_tbl_leads primary key (id)
);

create table tbl_upload_file(
    id varchar(255) not null unique,
    file_name varchar(255),
    file_type varchar(255),
    data longblob,
    constraint pk_tbl_data_file primary key(id)
);
