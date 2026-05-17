# Microservicio de Pagos - Sistema de Restaurante

Este proyecto es parte de una arquitectura basada en **6 microservicios** para la gestión integral de un restaurante.  
Este microservicio se encarga de gestionar los pagos asociados a los pedidos, consultando el total directamente desde el microservicio de pedidos.

---

## 🛠️ Tecnologías y Herramientas

- Java 17
- Spring Boot 3.3.5
- Spring Data JPA (Hibernate)
- Spring WebFlux (WebClient para comunicación entre microservicios)
- MySQL 8.0 (Desplegado en Docker - `db_facturacion`)
- Spring Boot Validation (JSR 380)
- Lombok

---

## 🏗️ Arquitectura y Patrones

- Arquitectura CSR (Controller, Service, Repository)
- Manejo centralizado de excepciones con `@ControllerAdvice`
- Uso de DTOs para transferencia de datos y validaciones
- Comunicación con `ms-pedidos` mediante `WebClient`
- Base de datos independiente (`db_facturacion`)

---

## ⚙️ Configuración y Ejecución

1. El contenedor MySQL en Docker debe estar en ejecución en el puerto `3306`
2. El microservicio `ms-pedidos` debe estar corriendo en el puerto `8084`
3. Ejecuta la clase principal:

```java
PagosApplication.java
```

4. El servidor se levantará en el puerto `8086`

---

## 📡 Endpoints de la API

### Pagos (`/api/pagos`)

| Método | Endpoint | Descripción | Body (JSON) |
|---|---|---|---|
| POST | `/api/pagos` | Procesa un pago para un pedido | `{"pedidoIdExterno": 1, "metodoPago": "EFECTIVO"}` |
| GET | `/api/pagos` | Lista todos los pagos | - |
| GET | `/api/pagos/{id}` | Obtiene un pago por ID | - |
| GET | `/api/pagos/pedido/{pedidoId}` | Obtiene el pago de un pedido específico | - |
| GET | `/api/pagos/estado/{estado}` | Lista pagos por estado | - |
| DELETE | `/api/pagos/{id}` | Elimina un pago | - |

---

## 💳 Métodos de Pago Disponibles

```text
EFECTIVO
DEBITO
CREDITO
TRANSFERENCIA
```

---

## 📊 Estados de Pago

```text
PENDIENTE
COMPLETADO
RECHAZADO
```

---

## 🔗 Comunicación con otros Microservicios

Este microservicio consulta a `ms-pedidos` para:

- Verificar que el pedido existe antes de procesar el pago
- Obtener el monto total del pedido automáticamente
- Actualizar el estado del pedido a `ENTREGADO` una vez completado el pago

---

## 🗄️ Base de Datos

| Propiedad | Valor |
|---|---|
| Nombre | `db_facturacion` |
| Puerto | `3306` |
| Motor | MySQL 8.0 (Docker) |
| Tabla | `pagos` |

---

## 📋 Estructura de la Tabla `pagos`

| Campo | Tipo | Descripción |
|---|---|---|
| `id_pago` | BIGINT | ID autogenerado |
| `pedido_id_externo` | BIGINT | ID del pedido asociado |
| `monto` | DOUBLE | Monto total del pago |
| `metodo_pago` | VARCHAR | Método de pago utilizado |
| `estado` | VARCHAR | Estado del pago |
| `fecha_pago` | DATETIME | Fecha y hora del pago |

---
