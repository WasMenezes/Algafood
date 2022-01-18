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