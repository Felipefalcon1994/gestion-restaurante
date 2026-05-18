# Microservicio de Menú - Sistema de Restaurante

Este proyecto es parte de una arquitectura basada en **6 microservicios** para la gestión integral de un restaurante. Este microservicio en particular se encarga de gestionar el catálogo de productos y sus respectivas categorías, aplicando el patrón de diseño de Base de Datos por Servicio.

## 🛠️ Tecnologías y Herramientas

* **Java 17 / 21**
* **Spring Boot 3.x**
* **Spring Data JPA** (Hibernate)
* **MySQL 8.0** (Desplegado en Docker)
* **Spring Boot Validation** (JSR 380)
* **Lombok**

## 🏗️ Arquitectura y Patrones

* Arquitectura **CSR** (Controller, Service, Repository).
* Manejo centralizado de excepciones con `@ControllerAdvice`.
* Uso estricto de **DTOs** para transferencia de datos y validaciones.
* Persistencia independiente (`db_menu`).

## 🚀 Requisitos Previos

Para ejecutar este microservicio localmente, necesitas tener instalado:
1. Java Development Kit (JDK).
2. IDE (IntelliJ IDEA, VS Code, etc.).
3. **Docker Desktop** (Debe estar en ejecución).

## ⚙️ Configuración y Ejecución

1. Asegúrate de que tu contenedor de base de datos MySQL esté corriendo en Docker en el puerto `3306`.
2. Clona o abre el proyecto en tu IDE.
3. Ejecuta la clase principal `MenuApplication.java`.
4. El servidor se levantará en `http://localhost:8081`. La base de datos y las tablas se crearán automáticamente.

## 📡 Endpoints de la API (Documentación)

### Categorías (`/api/categorias`)

| Método | Endpoint | Descripción | Body (JSON) |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/categorias` | Crea una nueva categoría | `{"nombre": "...", "descripcion": "..."}` |
| **GET** | `/api/categorias` | Lista todas las categorías | - |
| **GET** | `/api/categorias/{id}`| Obtiene una categoría por ID | - |
| **PUT** | `/api/categorias/{id}`| Actualiza una categoría | `{"nombre": "...", "descripcion": "..."}` |
| **DELETE**| `/api/categorias/{id}`| Elimina una categoría | - |

### Productos (`/api/productos`)

| Método | Endpoint | Descripción | Body (JSON) |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/productos` | Crea un nuevo producto | `{"nombre": "...", "precio": 1000, "idCategoria": 1}` |
| **GET** | `/api/productos` | Lista todos los productos | - |
| **GET** | `/api/productos/{id}` | Obtiene un producto por ID | - |
| **PUT** | `/api/productos/{id}` | Actualiza un producto | `{"nombre": "...", "precio": 1500, "idCategoria": 1}` |
| **DELETE**| `/api/productos/{id}` | Elimina un producto | - |