# Microservicio de Cocina - Sistema de Restaurante

Este proyecto es parte de una arquitectura basada en **6 microservicios** para la gestión integral de un restaurante. Este microservicio se encarga de recibir los pedidos confirmados, gestionar la cola de preparación (tickets) y actualizar el estado de los platos elaborados.

## 🛠️ Tecnologías y Herramientas

* **Java 17 / 21**
* **Spring Boot 3.x**
* **Spring Data JPA** (Hibernate)
* **MySQL 8.0** (Desplegado en Docker - `db_cocina`)
* **Spring WebFlux (WebClient)** (Para comunicación con pedidos)
* **Lombok**

## 🏗️ Arquitectura y Patrones

* Arquitectura **CSR** (Controller, Service, Repository).
* Comunicación síncrona mediante **WebClient** para notificar al microservicio de `pedidos` cuando un plato ha sido elaborado.
* Gestión de estados de preparación (ej. PENDIENTE, EN_PREPARACION, LISTO).

## ⚙️ Configuración y Ejecución

1. El contenedor MySQL en Docker debe estar en ejecución en el puerto `3306`.
2. El microservicio de `pedidos` (8084) debe estar activo para que la cocina pueda actualizar los estados.
3. Ejecuta la clase principal `CocinaApplication.java`.
4. El servidor se levantará en el puerto **`8085`**.

## 📡 Endpoints de la API

### Cocina / Tickets (`/api/cocina`)

| Método | Endpoint | Descripción | Body (JSON) |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/cocina/pendientes` | Lista los tickets pendientes de preparar | - |
| **POST** | `/api/cocina/recibir` | Recibe un nuevo ticket desde Pedidos | `{"pedidoIdExterno": 1, "observaciones": "Sin cebolla"}` |
| **PUT** | `/api/cocina/preparar/{id}`| Pasa un ticket a estado EN_PREPARACION | - |
| **PUT** | `/api/cocina/listo/{id}`| Marca un ticket como LISTO y avisa a Pedidos | - |
| **DELETE**| `/api/cocina/{id}`| Elimina un ticket (ej. pedido cancelado) | - |
