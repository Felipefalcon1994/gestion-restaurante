INSERT IGNORE INTO categoria (id, nombre, descripcion) VALUES (1, 'Platos Principales', 'Comida fuerte');
INSERT IGNORE INTO categoria (id, nombre, descripcion) VALUES (2, 'Bebidas', 'Bebidas frías y calientes');

INSERT IGNORE INTO producto (id, nombre, precio, disponible, id_categoria) VALUES (1, 'Hamburguesa Clásica', 5000, true, 1);
INSERT IGNORE INTO producto (id, nombre, precio, disponible, id_categoria) VALUES (2, 'Jugo Natural', 2000, true, 2);