INSERT IGNORE INTO insumos (id_insumo, nombre, unidad_medida, stock_actual, stock_minimo) VALUES (1, 'Carne de Hamburguesa', 'Unidad', 100, 20);

INSERT IGNORE INTO recetas_detalle (id_receta, cantidad_usada, producto_id_externo, insumo_id_externo) VALUES (1, 1, 1, 1);