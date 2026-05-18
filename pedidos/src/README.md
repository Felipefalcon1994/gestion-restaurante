# Microservicio de Pedidos - Sistema de Restaurante

Este proyecto es parte de una arquitectura basada en **6 microservicios** para la gestión integral de un restaurante. Este microservicio se encarga de gestionar las órdenes de los clientes, unificando la información de usuarios y productos del menú.

## 🛠️ Tecnologías y Herramientas

* **Java 17 / 21**
* **Spring Boot 3.x**
* **Spring Data JPA** (Hibernate)
* **MySQL 8.0** (Desplegado en Docker - `db_pedidos`)
* **Spring WebFlux (WebClient)** (Para comunicación entre microservicios)
* **Lombok**

## 🏗️ Arquitectura y Patrones

* Arquitectura **CSR** (Controller, Service, Repository).
* Comunicación síncrona mediante **WebClient** con los microservicios de `menu` y `usuarios` para validar existencias e información.
* Uso de **DTOs** para transferencia de datos y validaciones.
* Persistencia independiente (`db_pedidos`).

## ⚙️ Configuración y Ejecución

1. El contenedor MySQL en Docker debe estar en ejecución en el puerto `3306`.
2. Los microservicios de `menu` (8081) y `usuarios` (8083) deben estar activos para poder crear un pedido exitosamente.
3. Ejecuta la clase principal `PedidosApplication.java`.
4. El servidor se levantará en el puerto **`8084`**.

## 📡 Endpoints de la API

### Pedidos (`/api/pedidos`)

| Método | Endpoint | Descripción | Body (JSON) |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/pedidos` | Crea un nuevo pedido | `{"usuarioIdExterno": 1, "productoIdExterno": 1, "cantidad": 2}` |
| **GET** | `/api/pedidos` | Lista todos los pedidos | - |
| **GET** | `/api/pedidos/{id}`| Obtiene un pedido por ID | - |
| **PUT** | `/api/pedidos/{id}`| Actualiza la información de un pedido | `{"cantidad": 3}` |
| **PATCH**| `/api/pedidos/{id}/estado`| Actualiza el estado de un pedido | `{"estado": "EN_PREPARACION"}` |
| **DELETE**| `/api/pedidos/{id}`| Elimina o cancela un pedido | - |