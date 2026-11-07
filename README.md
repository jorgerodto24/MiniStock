# MiniStock

Aplicación de gestión de inventario desarrollada en **Java 21** como proyecto personal para practicar programación orientada a objetos, colecciones, gestión de archivos, pruebas unitarias y buenas prácticas de desarrollo.

## Descripción

MiniStock permite gestionar el inventario de una pequeña tienda desde una aplicación de consola. El usuario puede registrar productos, consultar existencias, modificar precios y controlar el valor total del inventario.

## Funcionalidades

- Mostrar todos los productos.
- Buscar productos por código.
- Registrar nuevos productos.
- Evitar productos con códigos duplicados.
- Eliminar productos del inventario.
- Actualizar precios y cantidades.
- Consultar productos con poco stock.
- Calcular el valor total del inventario.
- Guardar los productos en un archivo de texto.
- Cargar automáticamente los productos al iniciar la aplicación.
- Validar los datos introducidos y gestionar errores de lectura y escritura.
- Realizar pruebas unitarias para comprobar el funcionamiento de las operaciones.

## Tecnologías utilizadas

- Java 21
- Maven
- JUnit 5
- IntelliJ IDEA
- Git y GitHub
- HashMap, Map y ArrayList
- Programación orientada a objetos
- Gestión de archivos (`FileReader`, `FileWriter`, `BufferedReader`)

## Estructura del proyecto

```text
MiniStock/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Main.java
│   │       ├── Inventario.java
│   │       └── Producto.java
│   └── test/
│       └── java/
│           ├── InventarioTest.java
│           └── ProductoTest.java
├── pom.xml
├── .gitignore
└── README.md
```

## Cómo ejecutar el proyecto

Es necesario tener instalado **JDK 21** y Maven.

1. Clonar el repositorio.
2. Abrir el proyecto desde IntelliJ IDEA.
3. Esperar a que Maven descargue las dependencias.
4. Ejecutar la clase `Main.java`.

### Ejecutar las pruebas

Desde la terminal, en la carpeta raíz del proyecto:

```bash
mvn test
```

Este comando ejecuta las pruebas unitarias de JUnit 5.

## Estado del proyecto

Proyecto en desarrollo.

La versión actual es una aplicación de consola con gestión de inventario, persistencia mediante archivos de texto y pruebas unitarias.

Como siguiente etapa, se plantea transformar MiniStock en una API REST utilizando Spring Boot y PostgreSQL.

## Autor

Proyecto personal desarrollado como parte de mi aprendizaje de Java.