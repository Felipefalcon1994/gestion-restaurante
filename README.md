# Microservicio de Usuarios y Roles - Sistema de Restaurante

Este proyecto es parte de una arquitectura basada en **6 microservicios** para la gestión integral de un restaurante. Este microservicio es responsable de la gestión de identidades del personal del restaurante y la asignación de sus niveles de acceso (Roles).

## 🛠️ Tecnologías y Herramientas

* **Java 17 / 21**
* **Spring Boot 3.x**
* **Spring Data JPA** (Hibernate)
* **MySQL 8.0** (Desplegado en Docker - `db_usuarios`)
* **Spring Boot Validation** (JSR 380)
* **Lombok**

## 🏗️ Arquitectura y Patrones

* Arquitectura **CSR** (Controller, Service, Repository).
* Manejo centralizado de excepciones con `@ControllerAdvice`.
* Uso de **DTOs** para la protección de campos sensibles (ej. ID de roles).
* Relación de base de datos **1:N** (Un Rol -> Muchos Usuarios).

## ⚙️ Configuración y Ejecución

1. El contenedor MySQL en Docker debe estar en ejecución en el puerto `3306`.
2. Ejecuta la clase principal `UsuariosApplication.java`.
3. El servidor se levantará en el puerto **`8083`**.

## 📡 Endpoints de la API

### Roles (`/api/roles`)

| Método | Endpoint | Descripción | Body (JSON) |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/roles` | Crea un nuevo rol | `{"nombre": "Mesero", "descripcion": "..."}` |
| **GET** | `/api/roles` | Lista todos los roles | - |
| **GET** | `/api/roles/{id}`| Obtiene un rol por ID | - |
| **PUT** | `/api/roles/{id}`| Actualiza un rol | (Mismo formato que POST) |
| **DELETE**| `/api/roles/{id}`| Elimina un rol | - |

### Usuarios (`/api/usuarios`)

| Método | Endpoint | Descripción | Body (JSON) |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/usuarios` | Registra un usuario | `{"nombre": "...", "correo": "...", "password": "...", "idRol": 1}` |
| **GET** | `/api/usuarios` | Lista todos los usuarios | - |
| **GET** | `/api/usuarios/{id}`| Obtiene un usuario por ID | - |
| **PUT** | `/api/usuarios/{id}`| Actualiza un usuario | (Mismo formato que POST) |
| **DELETE**| `/api/usuarios/{id}`| Elimina un usuario | - |
