create table if not exists t_permissions (id bigint(9) auto_increment not null primary key, name varchar (100) not null );

create table if not exists t_roles (id bigint (9) auto_increment not null primary key, name varchar(20) not null);

create table if not exists t_role_permissions (role_id bigint(9)  not null primary key,
permission_id bigint(9) not null, foreign key(permission_id) references t_permissions(id),
foreign key (role_id) references t_roles(id));

create table if not exists t_users (id bigint (9) auto_increment not null primary key , login varchar(40) not null ,
surname varchar(40) not null , name varchar(40) not null, password varchar(40) not null, role_id bigint(8) not null,
foreign key(role_id) references t_roles(id));

create table if not exists t_news (id bigint(9) auto_increment not null primary key, title varchar (255) not null, content text not null,
created datetime not null default current_timestamp ,user_id bigint(9) not null, foreign key (user_id) references t_users(id));

create table if not exists t_comments (id bigint (9) auto_increment not null primary key, content text not null,
created datetime not null default current_timestamp , user_id bigint(9) not null, foreign key (user_id) references t_users(id));

create table if not exists t_audits (id bigint(9) auto_increment not null primary key, user_id bigint(9) not null, event_type text not null,
created datetime not null default current_timestamp, foreign key(user_id) references t_users(id));

create table if not exists t_profiles (user_id bigint(9) auto_increment not null primary key , telephone varchar (50) not null,
adress varchar (150) not null, foreign key(user_id) references t_users(id));

create table if not exists t_items (id bigint (9) auto_increment not null primary key, name varchar(70) not null,
description text, unique_num varchar (100), price float not null);

create table if not exists t_orders (id bigint(9) auto_increment not null primary key, user_id bigint(9) not null, ,
item_id bigint(9) not null, total_price float not null, created datetime not null default current_timestamp,quantity int (3)  not null,
foreign key (user_id) references t_users(id));



