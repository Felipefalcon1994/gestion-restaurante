# Microservicio de Pagos - Sistema de Restaurante

Este proyecto es parte de una arquitectura basada en **6 microservicios** para la gestión integral de un restaurante.  
Este microservicio se encarga de gestionar los pagos asociados a los pedidos, consultando el total directamente desde el microservicio de pedidos.

---

# 🛠️ Tecnologías y Herramientas

- Java 17
- Spring Boot 3.3.5
- Spring Data JPA (Hibernate)
- Spring WebFlux (WebClient para comunicación entre microservicios)
- MySQL 8.0 (Desplegado en Docker - `db_facturacion`)
- Spring Boot Validation (JSR 380)
- Lombok

---

# 🏗️ Arquitectura y Patrones

- Arquitectura CSR (Controller, Service, Repository)
- Manejo centralizado de excepciones con `@ControllerAdvice`
- Uso de DTOs para transferencia de datos y validaciones
- Comunicación con `ms-pedidos` mediante `WebClient`
- Base de datos independiente (`db_facturacion`)

---

# ⚙️ Configuración y Ejecución

1. El contenedor MySQL en Docker debe estar en ejecución en el puerto `3306`
2. El microservicio `ms-pedidos` debe estar corriendo en el puerto `8084`
3. Ejecuta la clase principal:

```java
PagosApplication.java
