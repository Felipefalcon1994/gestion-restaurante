
<h1 , align="center">Gestión Restaurante.</h1>

## Descripción del Proyecto
Este proyecto es el backend para administrar un restaurante, construido usando una arquitectura de microservicios. Para esta entrega, el sistema cuenta con 6 microservicios  conectados a sus propias bases de datos mediante Docker.

## Profesor
* Hernan Saavedra

## Estudiantes
* Felipe Quiroga
* Jorge Cañas


## Funcionalidades Implementadas
En esta etapa del proyecto, se desarrollaron los siguientes 6 microservicios:

**1. Microservicio de Menú (Puerto 8081)**
* Permite crear, leer, actualizar y eliminar (CRUD) los productos que se venden en el restaurante y sus categorías.

**2. Microservicio de Inventario (Puerto 8082)**
* Controla el stock de los ingredientes en la bodega (Insumos).
* Gestiona las Recetas, vinculando los ingredientes de la bodega con los platos del menú.

**3. Microservicio de Usuarios (Puerto 8083)**
* Administra los Roles de los empleados (Administrador, Mesero, Cocinero, etc.).
* Registra los Usuarios que tendrán acceso al sistema.

**4. Microservicio de Pedidos (Puerto 8084)**
* Gestiona las órdenes de los clientes vinculando usuarios y productos del menú.
* Calcula totales y mantiene el estado general del pedido.

**5. Microservicio de Cocina (Puerto 8085)**
* Recibe los pedidos confirmados para su elaboración en cola de tickets.
* Actualiza los estados de preparación (Ej: EN_PREPARACION, LISTO).

**6. Microservicio de Pagos (Puerto 8086)**
* Procesa los pagos de las órdenes finalizadas.
* Soporta distintos métodos de pago (EFECTIVO, DEBITO, CREDITO, TRANSFERENCIA).

## Pasos para Ejecutar

**1. Levantar la base de datos (Docker)**
* Descargar Docker Desktop de la pagina oficial [Enlace Docker oficial](https://www.docker.com/products/docker-desktop/)
* Abrir Docker
* Descargar todo el contenido, los microservicios y Docker Restaurant
* Abre una terminal en esta carpeta Docker restaurant "Tip: Dar segundo clic en la carpeta y poner abrir terminal".
* Ejecuta el comando: `docker-compose up -d`
* Esto creará automáticamente las bases de datos para los seis microservicios en el puerto 3306.

**2. Iniciar los microservicios**
Abre tu editor de código (como VS Code o IntelliJ) y ejecuta los proyectos en este orden:
1. `menu` (Iniciará en localhost:8081)
2. `inventario` (Iniciará en localhost:8082)
3. `usuarios` (Iniciará en localhost:8083)
4. `pedidos` (Iniciará en localhost:8084)
5. `cocina` (Iniciará en localhost:8085)
6. `pagos` (Iniciará en localhost:8086)

**3. Probar la aplicación**
Usa Postman para hacer peticiones (GET, POST, PUT, DELETE) a los puertos mencionados para interactuar con los datos.
