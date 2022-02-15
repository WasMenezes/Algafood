create table tb_group (id bigint not null auto_increment, name varchar(255) not null, primary key (id)) engine=InnoDB;
create table group_users (user_id bigint not null, group_id bigint not null) engine=InnoDB;
create table payment_method (id bigint not null auto_increment, description varchar(255) not null, primary key (id)) engine=InnoDB;
create table permission (id bigint not null auto_increment, description varchar(255) not null, name varchar(255) not null, primary key (id)) engine=InnoDB;
create table permission_tb_group (group_id bigint not null, permission_id bigint not null) engine=InnoDB;
create table product (id bigint not null auto_increment, active bit not null, description varchar(255) not null, name varchar(255) not null, price decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB;
create table restaurant (id bigint not null auto_increment, address_complement varchar(255), address_neighborhood varchar(255), address_number varchar(255), address_public_space varchar(255), address_zip_code varchar(255), created_at datetime not null, last_update datetime not null, name varchar(255) not null, shipping_fee decimal(19,2) not null, city_id bigint, kitchen_id bigint, primary key (id)) engine=InnoDB;
create table restaurant_payment_method (restaurant_id bigint not null, payment_method_id bigint not null) engine=InnoDB;
create table users (id bigint not null auto_increment, created_at datetime not null, email varchar(255) not null, name varchar(255) not null, password varchar(255) not null, primary key (id)) engine=InnoDB;
alter table group_users add constraint FKl50oh6eoun7aprilyp8h3pc8h foreign key (group_id) references tb_group (id);
alter table group_users add constraint FK6syyopfepdpec1ihe2v5klehr foreign key (user_id) references users (id);
alter table permission_tb_group add constraint FKcn775l61gpimtyac31up3vbg5 foreign key (permission_id) references permission (id);
alter table permission_tb_group add constraint FK8ockwbxkpqt90l8tcytnkphbo foreign key (group_id) references tb_group (id);
alter table product add constraint FKp4b7e36gck7975p51raud8juk foreign key (restaurant_id) references restaurant (id);
alter table restaurant add constraint FKl968d8d7966yymvsxtdsni1vw foreign key (city_id) references city (id);
alter table restaurant add constraint FKrur1dqx79jim8s8dvdp16qc3d foreign key (kitchen_id) references kitchen (id);
alter table restaurant_payment_method add constraint FK5dxd5cjhjqf8eai6xugad3e1g foreign key (payment_method_id) references payment_method (id);
alter table restaurant_payment_method add constraint FKbjuwyavt07p2uihdqt6jtmkyj foreign key (restaurant_id) references restaurant (id);

