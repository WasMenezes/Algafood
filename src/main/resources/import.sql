insert into kitchen(id, name) values(1, 'Tailandesa');
insert into kitchen(id, name) values(2, 'Indiana');

insert into restaurant(name, shipping_fee, kitchen_id) values('Thai Gourmet', 10, 1);
insert into restaurant(name, shipping_fee, kitchen_id) values('Thai Delivery', 9.5, 1);
insert into restaurant(name, shipping_fee, kitchen_id) values('Tuk Tuk Comida Indiana', 15, 2);

insert into state(id, name) values(1, 'Espirito Santo');
insert into state(id, name) values(2, 'Minas Gerais');

insert into city(name, state_id) values('Colatina', 1);
insert into city(name, state_id) values('Vitoria', 1);
insert into city(name, state_id) values('Belo Horizonte', 2);

insert into payment_method(id, description) values(1, 'Credit card');
insert into payment_method(id, description) values(2, 'Debit card');
insert into payment_method(id, description) values(3, 'Money');

insert into restaurant_payment_method (restaurant_id, payment_method_id) values(1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);