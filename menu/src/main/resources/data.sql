-- 1. POBLAR TABLA DE CATEGORÍAS

INSERT IGNORE INTO categorias (id_categoria, nombre, descripcion) VALUES (1, 'Hamburguesas', 'Hamburguesas de la casa con ingredientes frescos y carne premium');
INSERT IGNORE INTO categorias (id_categoria, nombre, descripcion) VALUES (2, 'Pizzas', 'Pizzas artesanales de masa a la piedra, ideales para compartir');
INSERT IGNORE INTO categorias (id_categoria, nombre, descripcion) VALUES (3, 'Acompañamientos', 'Las mejores opciones para acompañar tu plato principal');
INSERT IGNORE INTO categorias (id_categoria, nombre, descripcion) VALUES (4, 'Bebidas', 'Bebidas frias, jugos naturales y gaseosas');
INSERT IGNORE INTO categorias (id_categoria, nombre, descripcion) VALUES (5, 'Postres', 'El toque dulce perfecto para terminar tu comida');


-- Categoría 1: Hamburguesas
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (1, 'Hamburguesa Clasica', 5500, 1, 1);
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (2, 'Hamburguesa Doble Queso', 7500, 1, 1);
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (3, 'Bacon Crispy Burger', 8500, 1, 1);

-- Categoría 2: Pizzas
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (4, 'Pizza Pepperoni Familiar', 12000, 1, 2);
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (5, 'Pizza Mechada BBQ', 14500, 1, 2);
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (6, 'Pizza Margarita', 10000, 1, 2);

-- Categoría 3: Acompañamientos
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (7, 'Nuggets de Pollo (10 un.)', 4500, 1, 3);
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (8, 'Papas Fritas Grandes', 3500, 1, 3);
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (9, 'Aros de Cebolla', 4000, 1, 3);

-- Categoría 4: Bebidas
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (10, 'Bebida Cola 500cc', 1500, 1, 4);
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (11, 'Jugo Natural de Frambuesa', 2500, 1, 4);
-- PRODUCTO AGOTADO (Para probar la validación de error en la presentación)
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (12, 'Bebida Sprite 500cc', 1500, 0, 4);

-- Categoría 5: Postres
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (13, 'Brownie con Helado', 4500, 1, 5);
INSERT IGNORE INTO productos (id_producto, nombre, precio, disponible, id_categoria) VALUES (14, 'Helado de Vainilla', 2000, 1, 5);