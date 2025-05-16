# API Spring WebFlux Project

Este proyecto explora el uso de programación reactiva con Spring WebFlux, implementando operaciones CRUD en una base de datos y mostrando datos dinámicamente en el front-end con Thymeleaf.

## Tabla de Contenidos

- [Descripción](#descripción)
- [Características](#características)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Ejecución](#ejecución)
- [Dockerización](#dockerización)
- [CI/CD con Jenkins](#ci-cd-con-jenkins)
- [Contribuciones](#contribuciones)

---

## Descripción

Este proyecto está diseñado para demostrar cómo realizar operaciones CRUD de manera reactiva con Spring WebFlux. Incluye un enfoque en el desarrollo de front-end dinámico con Thymeleaf, configuraciones personalizadas utilizando Configuration Processor y una integración continua simple con Jenkins.

## Características

- **Programación Reactiva**: Uso de Spring WebFlux para manejar solicitudes asíncronas y no bloqueantes.
- **Operaciones CRUD**: Interacción reactiva con la base de datos para crear, leer, actualizar y eliminar registros.
- **Thymeleaf**: Plantillas dinámicas para renderizar datos reactivos en tiempo real.
- **Configuraciones Personalizadas**: Uso de Configuration Processor para gestionar y validar configuraciones.
- **Dockerización**: Contenedores Docker para la aplicación, base de datos y Jenkins.
- **CI/CD**: Pipeline de Jenkins para probar y desplegar el código.

## Requisitos Previos

- **Java**: JDK 21 o superior.
- **Maven**: Para construir el proyecto.
- **Docker**: Para ejecutar los contenedores.

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/lisandroEkruger/api-spring-webflux-project.git
   cd reactive-programming
   ```

2. Construye el proyecto con Maven:
   ```bash
   mvn clean install
   ```

## Ejecución

1. Inicia la base de datos y la aplicación con Docker Compose:
   ```bash
   docker-compose up -d
   ```

2. Accede a la aplicación en tu navegador:
   ```
   http://localhost:8080
   ```

3. Navega por las operaciones CRUD y observa cómo los datos se actualizan dinámicamente.

## Dockerización

Este proyecto incluye un archivo `docker-compose.yml` para levantar los siguientes servicios:

- **Aplicación**: La aplicación Spring Boot.
- **Base de datos**: Configurada para operaciones CRUD.
- **Jenkins**: Servidor de integración continua para probar y desplegar el proyecto.

### Comandos útiles

- Para detener los servicios:
  ```bash
  docker-compose down
  ```
- Para reconstruir imágenes:
  ```bash
  docker-compose build
  ```

## CI/CD con Jenkins - Próximamente

El proyecto incluye un `Jenkinsfile` con los pasos para:

1. **Construcción**: Compila y empaqueta la aplicación con Maven.
2. **Pruebas**: Ejecuta pruebas unitarias y de integración.
3. **Despliegue**: Levanta los servicios en un entorno Docker.

### Configuración de Jenkins - Próximamente

1. Instala Jenkins en tu máquina o utiliza un contenedor Docker:
   ```bash
   docker run -p 8081:8080 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts
   ```

2. Crea un pipeline apuntando al repositorio de este proyecto.
3. Configura las credenciales necesarias para acceder al repositorio y Docker.

## Contribuciones

¡Contribuciones son bienvenidas! Si tienes ideas para mejorar este proyecto o quieres agregar nuevas características, abre un issue o envía un pull request.

## Créditos
Este proyecto está basado en el tutorial de [DavinchiCoder](https://www.youtube.com/@davinchicoder) que explica el proceso paso a paso.