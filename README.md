<h1 align="center">Gestión Restaurante - Arquitectura de Microservicios 🍽️</h1>

## Descripción del Proyecto
Este proyecto es el backend transaccional para administrar las operaciones de un restaurante. Para esta entrega final (Examen Transversal), el ecosistema está compuesto por 6 microservicios de negocio independientes, conectados a sus propias bases de datos MySQL aisladas mediante Docker, y orquestados en su totalidad a través de un **API Gateway** centralizado.

## Datos Académicos
* **Institución:** Duoc UC
* **Profesor:** Hernan Saavedra
* **Estudiantes:** Felipe Quiroga - Jorge Cañas - Fernanda Peña

---

## Arquitectura y Patrones Implementados (Nivel Empresarial)

Para garantizar un código robusto, escalable y tolerante a fallos, se implementaron los siguientes estándares en la arquitectura:

1. **API Gateway (Punto de Entrada Único):** Enrutamiento centralizado de peticiones. Oculta la complejidad de los puertos internos de los microservicios al cliente final (Front-end), gestionando el tráfico y balanceo a través de un solo puerto de acceso (`8080`).
2. **Documentación Viva e Integrada (OpenAPI 3 / Swagger):** Interfaces interactivas autogeneradas (`@Operation`, `@Parameter`). Gracias al API Gateway, es posible centralizar la documentación para explorar los endpoints de todo el ecosistema desde un solo lugar.
3. **Patrón DTO y Bean Validation (JSR 380):** Sanitización estricta de datos de entrada en la capa de Controladores (`@Valid`, `@NotBlank`, `@Email`). Los DTO protegen las entidades de la base de datos contra inyecciones o datos corruptos.
4. **Manejo Centralizado de Excepciones (`@RestControllerAdvice`):** Implementación de un escudo global (`GlobalExceptionHandler`) que captura errores (ej. `IllegalArgumentException`). Evita colapsos críticos (Error 500) y devuelve respuestas HTTP en JSON estructurado (Errores 404/400).
5. **Comunicación Síncrona REST (`WebClient`):** Integración inter-servicios. El microservicio de **Usuarios** actúa como consumidor conectándose mediante peticiones HTTP al microservicio de **Pedidos** para consultar el historial de compras de un cliente.
6. **Trazabilidad y Auditoría (Logs SLF4J):** Logs profesionales a nivel de Servicio (`log.info`, `log.warn`, `log.error`) para auditoría de transacciones y diagnóstico rápido en producción.
7. **Cobertura de Pruebas (JUnit 5 & Mockito):** Pruebas unitarias bajo el patrón *Given-When-Then*, utilizando simulaciones (`@Mock`) para aislar la capa de Servicios de la base de datos real.

---

## Estructura de Microservicios y Puertos

El ecosistema opera detrás del API Gateway, dirigiéndose a 6 módulos de negocio:

** API Gateway (Puerto 8080) - PUNTO DE ACCESO PRINCIPAL**
* Recibe todas las peticiones externas y las enruta al microservicio correspondiente.

**1. Microservicio de Menú (Puerto interno 8081)**
* Gestiona el CRUD de los productos y categorías.

**2. Microservicio de Inventario (Puerto interno 8082)**
* Controla el stock de insumos en bodega y gestiona las recetas vinculadas a los platos.

**3. Microservicio de Usuarios (Puerto interno 8083)**
* Administra los roles y credenciales. Consume datos de Pedidos vía `WebClient`.

**4. Microservicio de Pedidos (Puerto interno 8084)**
* Gestiona las órdenes de los clientes vinculando usuarios y productos.

**5. Microservicio de Cocina (Puerto interno 8085)**
* Recibe pedidos confirmados en una cola de tickets y actualiza los estados de preparación.

**6. Microservicio de Pagos (Puerto interno 8086)**
* Procesa los cobros de las órdenes finalizadas (Efectivo, Débito, Crédito).

---

## Pasos para Despliegue y Ejecución

**1. Levantar la Infraestructura de Datos (Docker)**
* Asegúrese de tener instalado [Docker Desktop](https://www.docker.com/products/docker-desktop/).
* Abra una terminal en la carpeta `Docker restaurant`.
* Ejecute: `docker-compose up -d` (Esto desplegará las bases de datos MySQL en el puerto `3306`).

**2. Iniciar el Ecosistema (Spring Boot)**
Abra su entorno de desarrollo y ejecute los proyectos en este orden:
1. `api-gateway` (localhost:8080)
2. `menu` (localhost:8081)
3. `inventario` (localhost:8082)
4. `usuarios` (localhost:8083)
5. `pedidos` (localhost:8084)
6. `cocina` (localhost:8085)
7. `pagos` (localhost:8086)

---

## Flujo de Pruebas Transaccional a través del API Gateway

*Nota: Todas las peticiones ahora apuntan al puerto `8080` (Gateway). El Gateway se encarga de redirigirlas internamente según la ruta.*

```http
### 1. Crear Categoría (Enrutado a Menú)
POST http://localhost:8080/api/categorias
Content-Type: application/json

{ 
  "nombre": "Platos Principales", 
  "descripcion": "Comida fuerte" 
}

### 2. Crear Producto (Enrutado a Menú)
POST http://localhost:8080/api/productos
Content-Type: application/json

{ 
  "nombre": "Hamburguesa Clásica", 
  "precio": 5000, 
  "idCategoria": 1 
}

### 3. Crear Usuario (Enrutado a Usuarios)
POST http://localhost:8080/api/usuarios
Content-Type: application/json

{ 
  "nombre": "Jorge Cañas", 
  "correo": "jorge@correo.com", 
  "password": "123", 
  "idRol": 1 
}

### 4. Crear un Pedido (Enrutado a Pedidos)
POST http://localhost:8080/api/pedidos
Content-Type: application/json

{ 
  "usuarioIdExterno": 1, 
  "productoIdExterno": 1, 
  "cantidad": 2 
}

### 5. Recibir Ticket (Enrutado a Cocina)
POST http://localhost:8080/api/cocina/recibir
Content-Type: application/json

{ 
  "pedidoIdExterno": 1, 
  "observaciones": "Sin cebolla" 
}

### 6. Procesar el Cobro (Enrutado a Pagos)
POST http://localhost:8080/api/pagos
Content-Type: application/json

{ 
  "pedidoIdExterno": 1, 
  "metodoPago": "DEBITO" 
}
