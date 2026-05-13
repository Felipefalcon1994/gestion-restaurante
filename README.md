
<h1 , align="center">Gestión Restaurante.</h1>

## Descripción del Proyecto
Este proyecto es el backend para administrar un restaurante, construido usando una arquitectura de microservicios. Para esta entrega, el sistema cuenta con 6 microservicios  conectados a sus propias bases de datos mediante Docker.

## Profesor
  Hernan Saavedra

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

## Pasos para Ejecutar

**1. Levantar la base de datos (Docker)**
* Abre una terminal en esta carpeta principal.
* Ejecuta el comando: `docker-compose up -d`
* Esto creará automáticamente las bases de datos para los tres microservicios en el puerto 3306.

**2. Iniciar los microservicios**
Abre tu editor de código (como VS Code o IntelliJ) y ejecuta los proyectos en este orden:
1. `menu` (Iniciará en localhost:8081)
2. `inventario` (Iniciará en localhost:8082)
3. `usuarios` (Iniciará en localhost:8083)

**3. Probar la aplicación**
Usa Postman para hacer peticiones (GET, POST, PUT, DELETE) a los puertos mencionados para interactuar con los datos.
