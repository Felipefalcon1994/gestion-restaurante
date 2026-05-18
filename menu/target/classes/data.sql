INSERT IGNORE INTO categorias (id_categoria, nombre, descripcion) VALUES (1, 'Platos Principales', 'Comida fuerte');
INSERT IGNORE INTO categorias (id_categoria, nombre, descripcion) VALUES (2, 'Bebidas', 'Bebidas frias y calientes');

INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (1, 'Hamburguesa Clasica', 5000, true, 1);
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (2, 'Jugo Natural', 2000, true, 2);