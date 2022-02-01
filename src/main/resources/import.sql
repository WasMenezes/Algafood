insert into kitchen(id, name) values(1, 'Tailandesa');
insert into kitchen(id, name) values(2, 'Indiana');

insert into state(id, name) values(1, 'Espirito Santo');
insert into state(id, name) values(2, 'Minas Gerais');

insert into city(name, state_id) values('Colatina', 1);
insert into city(name, state_id) values('Vitoria', 1);
insert into city(name, state_id) values('Belo Horizonte', 2);

insert into restaurant(name, shipping_fee, kitchen_id, address_zip_code, address_public_space, address_number, address_complement, address_neighborhood, city_id, created_at, last_update) values('Thai Gourmet', 10, 1, '2999999', 'Rua Clorentino Bangladesh', '219', 'Terreo', 'Vila Leopoldina', 1, utc_timestamp, utc_timestamp);
insert into restaurant(name, shipping_fee, kitchen_id, address_zip_code, address_public_space, address_number, address_complement, address_neighborhood, city_id, created_at, last_update) values('Thai Delivery', 9.5, 1, '1343322', 'Rua Tomas Cavalcante', '23', '1º Andar', 'São Carlos', 1, utc_timestamp, utc_timestamp);
insert into restaurant(name, shipping_fee, kitchen_id, address_zip_code, address_public_space, address_number, address_complement, address_neighborhood, city_id, created_at, last_update) values('Tuk Tuk Comida Indiana', 15, 2, '14355200', 'Avenida Moacir Vago', '104', '17º Andar', 'Carlos Antoneli', 2, utc_timestamp, utc_timestamp);


insert into payment_method(id, description) values(1, 'Credit card');
insert into payment_method(id, description) values(2, 'Debit card');
insert into payment_method(id, description) values(3, 'Money');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values(1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);