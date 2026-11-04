# MiniStock

Aplicación de gestión de inventario desarrollada en **Java 21** como proyecto personal para practicar programación orientada a objetos, gestión de archivos y buenas prácticas de desarrollo.

## Descripción

MiniStock permite gestionar el inventario de una pequeña tienda desde una aplicación de consola. El usuario puede registrar productos, consultar existencias, modificar precios y controlar el valor total del inventario.

## Funcionalidades

- Mostrar todos los productos.
- Buscar productos por nombre.
- Registrar nuevos productos.
- Eliminar productos del inventario.
- Actualizar precios y cantidades.
- Consultar productos con poco stock.
- Calcular el valor total del inventario.
- Guardar los productos en un archivo de texto.
- Cargar automáticamente los productos al iniciar la aplicación.
- Validar los datos introducidos por el usuario y gestionar errores de lectura.

## Tecnologías utilizadas

- Java 21
- IntelliJ IDEA
- Git y GitHub
- ArrayList
- Programación orientada a objetos
- Lectura y escritura de archivos (`FileReader`, `FileWriter`, `BufferedReader`)

## Estructura del proyecto

```text
MiniStock/
├── src/
│   ├── Main.java
│   ├── Inventario.java
│   └── Producto.java
├── .gitignore
└── README.md
```

## Cómo ejecutar el proyecto

Es necesario tener instalado **JDK 21**.

Desde la carpeta raíz del proyecto, ejecuta:

```bash
javac -d out src/*.java
java -cp out Main
```

## Estado del proyecto

Proyecto en desarrollo.

Como siguientes pasos, se plantea ampliar la aplicación incorporando una base de datos PostgreSQL y desarrollando una API REST con Spring Boot.

## Autor

Proyecto personal desarrollado como parte de mi aprendizaje de Java.