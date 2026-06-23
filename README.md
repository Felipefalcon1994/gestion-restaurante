<h1 align="center">Gestión Restaurante - Arquitectura de Microservicios 🍽️</h1>

## Descripción del Proyecto
Este proyecto es el backend transaccional para administrar las operaciones de un restaurante, construido bajo una arquitectura de microservicios. Para esta entrega final (Examen Transversal), el ecosistema cuenta con 6 microservicios independientes, conectados a sus propias bases de datos MySQL aisladas mediante Docker y comunicados de forma síncrona cumpliendo con los estándares de la industria.

## Datos Académicos
* **Institución:** Duoc UC
* **Profesor:** Hernan Saavedra
* **Estudiantes:** Felipe Quiroga - Jorge Cañas - Fernanda Peña

---

## Arquitectura y Patrones Implementados

Para garantizar un código robusto, escalable y tolerante a fallos, se implementaron los siguientes estándares en los microservicios:

1. **Patrón DTO y Bean Validation (JSR 380):** Sanitización estricta de datos de entrada en la capa de Controladores (`@Valid`, `@NotBlank`, `@Email`). Los objetos de transferencia (DTO) aíslan y protegen las entidades reales de la base de datos contra inyecciones o datos corruptos.
2. **Manejo Centralizado de Excepciones (`@RestControllerAdvice`):** Implementación de un escudo global (`GlobalExceptionHandler`) que captura errores en tiempo de ejecución (ej. `IllegalArgumentException`). Evita colapsos críticos del servidor (Error 500) y devuelve en su lugar respuestas HTTP en formato JSON limpio y estructurado (Errores 404/400).
3. **Comunicación Síncrona REST (`WebClient`):** Integración inter-servicios. Por ejemplo, el microservicio de **Usuarios** actúa como consumidor al conectarse directamente mediante peticiones HTTP al microservicio de **Pedidos** para consultar el historial de compras de un cliente específico.
4. **Trazabilidad y Auditoría (Logs SLF4J):** Reemplazo de impresiones de consola por logs profesionales a nivel de Servicio (`log.info`, `log.warn`, `log.error`), permitiendo la auditoría de transacciones y un diagnóstico rápido de fallos en producción.
5. **Documentación Viva (OpenAPI 3 / Swagger):** Interfaces interactivas y personalizadas autogeneradas en cada microservicio, permitiendo probar los endpoints y visualizar los esquemas JSON requeridos sin depender exclusivamente de Postman.
6. **Cobertura de Pruebas (JUnit 5 & Mockito):** Implementación de pruebas unitarias con el patrón *Given-When-Then*, utilizando inyección de dependencias simuladas (`@Mock`) aislando la capa de Servicios de la base de datos real.

---

## Estructura de Microservicios

El sistema se compone de los siguientes 6 módulos interconectados:

**1. Microservicio de Menú (Puerto 8081)**
* Permite gestionar el CRUD de los productos y categorías del restaurante.
* **Swagger:** `http://localhost:8081/swagger-ui.html`

**2. Microservicio de Inventario (Puerto 8082)**
* Controla el stock de insumos en bodega y gestiona las recetas vinculadas a los platos.

**3. Microservicio de Usuarios (Puerto 8083)**
* Administra los roles y credenciales de los empleados y clientes.
* **Integración:** Consume datos del microservicio de Pedidos vía `WebClient`.
* **Swagger:** `http://localhost:8083/swagger-ui.html`

**4. Microservicio de Pedidos (Puerto 8084)**
* Gestiona las órdenes de los clientes vinculando usuarios y productos, calculando los totales a pagar.

**5. Microservicio de Cocina (Puerto 8085)**
* Recibe pedidos confirmados en una cola de tickets y actualiza los estados de preparación (ej: *EN_PREPARACION*, *LISTO*).

**6. Microservicio de Pagos (Puerto 8086)**
* Procesa los cobros de las órdenes finalizadas soportando múltiples métodos (Efectivo, Débito, Crédito, Transferencia).

---

## Pasos para Despliegue y Ejecución

**1. Levantar la Infraestructura de Datos (Docker)**
* Asegúrese de tener instalado [Docker Desktop](https://www.docker.com/products/docker-desktop/).
* Abra una terminal en la carpeta `Docker restaurant`.
* Ejecute el comando: `docker-compose up -d`
* Esto desplegará automáticamente las 6 bases de datos en el puerto `3306`.

**2. Iniciar los Microservicios (Spring Boot)**
Abra su entorno de desarrollo y ejecute los proyectos **estrictamente en este orden** para asegurar la correcta inyección de dependencias y ejecución de los scripts `data.sql`:
1. `menu` (localhost:8081)
2. `inventario` (localhost:8082)
3. `usuarios` (localhost:8083)
4. `pedidos` (localhost:8084)
5. `cocina` (localhost:8085)
6. `pagos` (localhost:8086)

---

## Flujo de Pruebas Transaccional (Postman)

Para comprobar la interoperabilidad de todo el ecosistema, puede importar o copiar el siguiente bloque de peticiones secuenciales:

```http
### 1. Crear Categoría (Menú)
POST http://localhost:8081/api/categorias
Content-Type: application/json

{ 
  "nombre": "Platos Principales", 
  "descripcion": "Comida fuerte" 
}

### 2. Crear Producto (Menú)
POST http://localhost:8081/api/productos
Content-Type: application/json

{ 
  "nombre": "Hamburguesa Clásica", 
  "precio": 5000, 
  "idCategoria": 1 
}

### 3. Crear Usuario (Usuarios)
POST http://localhost:8083/api/usuarios
Content-Type: application/json

{ 
  "nombre": "Jorge Cañas", 
  "correo": "jorge@correo.com", 
  "password": "123", 
  "idRol": 1 
}

### 4. Crear un Pedido (Pedidos)
POST http://localhost:8084/api/pedidos
Content-Type: application/json

{ 
  "usuarioIdExterno": 1, 
  "productoIdExterno": 1, 
  "cantidad": 2 
}

### 5. Recibir Ticket en Cocina (Cocina)
POST http://localhost:8085/api/cocina/recibir
Content-Type: application/json

{ 
  "pedidoIdExterno": 1, 
  "observaciones": "Sin cebolla" 
}

### 6. Procesar el Cobro (Pagos)
POST http://localhost:8086/api/pagos
Content-Type: application/json

{ 
  "pedidoIdExterno": 1, 
  "metodoPago": "DEBITO" 
}
