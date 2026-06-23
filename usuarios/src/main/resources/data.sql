INSERT INTO roles (id_rol, nombre, descripcion) VALUES (1, 'Cliente', 'Cliente del restaurante');
INSERT INTO roles (id_rol, nombre, descripcion) VALUES (2, 'ADMINISTRADOR', 'Control total del sistema');
INSERT INTO roles (id_rol, nombre, descripcion) VALUES (3, 'CAJERO', 'Gestión de pedidos y pagos en salón');
INSERT INTO roles (id_rol, nombre, descripcion) VALUES (4, 'COCINERO', 'Gestión de comandas y recetas');

INSERT INTO usuarios (id_usuario, nombre, correo, password, roles_id_rol) VALUES (1, 'Jorge Cañas', 'jorge@restaurante.cl', '12345', 2);

INSERT INTO usuarios (id_usuario, nombre, correo, password, roles_id_rol) VALUES (2, 'Felipe', 'felipe@restaurante.cl', '12345', 3);

INSERT INTO usuarios (id_usuario, nombre, correo, password, roles_id_rol) VALUES (3, 'Feña', 'cocina@restaurante.cl', '12345', 4);

INSERT INTO usuarios (id_usuario, nombre, correo, password, roles_id_rol) VALUES (4, 'María González', 'maria.cliente@correo.com', '12345', 1);
INSERT INTO usuarios (id_usuario, nombre, correo, password, roles_id_rol) VALUES (5, 'Pedro Pascal', 'pedro.cliente@correo.com', '12345', 1);