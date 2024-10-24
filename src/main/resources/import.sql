INSERT INTO usuario (id, username, password, role) VALUES (1, 'admin', '$2a$10$4q6tB4IF1nU7E62zgP7lX.m/3vfmIjNDbv/Q/e4OBMC7.1vxUATfm', 'ADMIN');
INSERT INTO usuario (id, username, password, role) VALUES (2, 'user', '$2a$10$cttXHBEqTVRw9HTjmYzrGeHvMwpWYB.xuaYxih9wp4eb/8mPJiSiW', 'USER');

INSERT INTO pacote (id, nome, preco_por_dia) VALUES (1, 'basico', 100);
INSERT INTO pacote (id, nome, preco_por_dia) VALUES (2, 'premiun', 300);
INSERT INTO pacote (id, nome, preco_por_dia) VALUES (3, 'luxo', 500);

INSERT INTO carro (id, modelo, marca, ano, renavam, pacote_id) VALUES (1, 'Civic', 'Honda', 2024, '123456789', 2);
INSERT INTO carro (id, modelo, marca, ano, renavam, pacote_id) VALUES (2, 'Corolla', 'Toyota', 2024, '987654321', 2);
INSERT INTO carro (id, modelo, marca, ano, renavam, pacote_id) VALUES (3, 'Mustang', 'Ford', 2024, '112233445', 3);
INSERT INTO carro (id, modelo, marca, ano, renavam, pacote_id) VALUES (4, 'Golf', 'Volkswagen', 2024, '556677889', 2);
INSERT INTO carro (id, modelo, marca, ano, renavam, pacote_id) VALUES (5, 'Model S', 'Tesla', 2024, '778899001', 3);
INSERT INTO carro (id, modelo, marca, ano, renavam, pacote_id) VALUES (6, 'Accord', 'Honda', 2024, '667788990', 2);
INSERT INTO carro (id, modelo, marca, ano, renavam, pacote_id) VALUES (7, 'A4', 'Audi', 2024, '998877665', 3);
INSERT INTO carro (id, modelo, marca, ano, renavam, pacote_id) VALUES (8, 'X5', 'BMW', 2024, '554433221', 3);
INSERT INTO carro (id, modelo, marca, ano, renavam, pacote_id) VALUES (9, 'Cherokee', 'Jeep', 2024, '776655443', 2);
INSERT INTO carro (id, modelo, marca, ano, renavam, pacote_id) VALUES (10, 'Polo', 'Volkswagen', 2024, '332211554', 1);
INSERT INTO carro (id, modelo, marca, ano, renavam, pacote_id) VALUES (11, 'Argo', 'Fiat', 2024, '547654321', 1);
INSERT INTO carro (id, modelo, marca, ano, renavam, pacote_id) VALUES (12, 'Onix', 'Chevrolet', 2024, '767654321', 1);
INSERT INTO carro (id, modelo, marca, ano, renavam, pacote_id) VALUES (13, 'Hilux', 'Toyta', 2024, '237654321', 2);
INSERT INTO carro (id, modelo, marca, ano, renavam, pacote_id) VALUES (14, '320i', 'Bmw', 2024, '557653896', 2);
