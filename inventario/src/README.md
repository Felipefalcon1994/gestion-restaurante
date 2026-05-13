# Microservicio de Inventario - Sistema de Restaurante

Este proyecto es parte de una arquitectura basada en **6 microservicios** para la gestión integral de un restaurante. Este microservicio se encarga de controlar el stock de la bodega (Insumos) y relacionar las proporciones necesarias para cada plato (Recetas) mediante referencias externas.

## 🛠️ Tecnologías y Herramientas

* **Java 17 / 21**
* **Spring Boot 3.x**
* **Spring Data JPA** (Hibernate)
* **MySQL 8.0** (Desplegado en Docker - `db_inventario`)
* **Spring Boot Validation** (JSR 380)
* **Lombok**

## 🏗️ Arquitectura y Patrones

* Arquitectura **CSR** (Controller, Service, Repository).
* Manejo centralizado de excepciones con `@ControllerAdvice`.
* Uso de **DTOs** para transferencia de datos y validaciones.
* Relación de microservicios mediante **ID Externo** (`productoIdExterno`) para mantener el bajo acoplamiento con el servicio de Menú.

## ⚙️ Configuración y Ejecución

1. El contenedor MySQL en Docker debe estar en ejecución en el puerto `3306`.
2. Ejecuta la clase principal `InventarioApplication.java`.
3. El servidor se levantará en el puerto **`8082`**.

## 📡 Endpoints de la API

### Insumos (`/api/insumos`)

| Método | Endpoint | Descripción | Body (JSON) |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/insumos` | Crea un nuevo insumo de bodega | `{"nombre": "...", "unidadMedida": "...", "stockActual": 100, "stockMinimo": 20}` |
| **GET** | `/api/insumos` | Lista todos los insumos | - |
| **GET** | `/api/insumos/{id}`| Obtiene un insumo por ID | - |
| **PUT** | `/api/insumos/{id}`| Actualiza un insumo | (Mismo formato que POST) |
| **DELETE**| `/api/insumos/{id}`| Elimina un insumo | - |

### Recetas Detalle (`/api/recetas`)

| Método | Endpoint | Descripción | Body (JSON) |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/recetas` | Crea una relación insumo-producto | `{"cantidadUsada": 2, "productoIdExterno": 1, "idInsumo": 1}` |
| **GET** | `/api/recetas` | Lista todas las recetas | - |
| **GET** | `/api/recetas/{id}`| Obtiene una receta por ID | - |
| **PUT** | `/api/recetas/{id}`| Actualiza una receta | (Mismo formato que POST) |
| **DELETE**| `/api/recetas/{id}`| Elimina una receta | - |