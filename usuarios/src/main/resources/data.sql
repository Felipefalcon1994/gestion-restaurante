INSERT IGNORE INTO roles (id_rol, nombre, descripcion) VALUES (1, 'Cliente', 'Cliente del restaurante');

INSERT IGNORE INTO usuarios (id_usuario, nombre, correo, password, id_rol) VALUES (1, 'Jorge Cañas', 'jorge@correo.com', '123', 1);