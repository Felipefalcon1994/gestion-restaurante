INSERT IGNORE INTO rol (id, nombre, descripcion) VALUES (1, 'Cliente', 'Cliente del restaurante');

INSERT IGNORE INTO usuario (id, nombre, correo, password, id_rol) VALUES (1, 'Jorge Cañas', 'jorge@correo.com', '123', 1);