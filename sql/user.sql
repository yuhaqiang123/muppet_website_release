SET @database_name="user_slave1";

create database if not exists user character set UTF8;

create table if not exists user.tb_user(
id varchar(50) not null primary key,

nick_name varchar(20) not null,

email varchar(50) not null,

register_time timestamp DEFAULT CURRENT_TIMESTAMP,

update_time timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

last_login_time timestamp not null,

encrpt_password varchar(60) not null
);

alter table user.tb_user add column if not exists register_ip int(11);

alter table user.tb_user modify register_ip bigint;



