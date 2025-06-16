# 📚 Challenge Literalura

Aplicación de consola desarrollada que permite consultar, registrar y explorar información sobre libros y autores utilizando datos de una API externa.

---

## 🚀 Tecnologías y Herramientas Utilizadas

- ![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white) Java 17
- ![Maven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=apachemaven&logoColor=white) Maven
- ![JPA](https://img.shields.io/badge/JPA-59666C?style=flat&logo=hibernate&logoColor=white) Jakarta Persistence API
- ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=flat&logo=hibernate&logoColor=white) Hibernate ORM
- ![H2](https://img.shields.io/badge/H2%20Database-1C4A6A?style=flat&logo=data:image/svg+xml;base64,PHN2Zy...&logoColor=white) H2 Database (modo persistente)
- ![IntelliJ IDEA](https://img.shields.io/badge/IDE-IntelliJ%20IDEA-blue?style=flat&logo=intellijidea&logoColor=white)

---

## 🎯 Objetivos del Proyecto

- Consultar libros desde una API pública [Gutendex](https://gutendex.com/).
- Permitir al usuario almacenar libros seleccionados en una base de datos local.
- Explorar libros y autores registrados con diferentes filtros.
- Poner en práctica conceptos de:
    - Programación orientada a objetos (POO).
    - Persistencia con JPA.
    - Consumo de APIs externas (con `HttpClient` y `Gson`).

---

## ⚙️ Funcionalidades

1. 📖 **Buscar libro por título**  
   Busca un libro utilizando el título, consume los datos desde la API, y permite almacenarlo en la base de datos.

2. 🗂️ **Listar libros registrados**  
   Muestra todos los libros que han sido guardados en la base de datos.

3. 🧑‍💼 **Listar autores registrados**  
   Muestra los autores de los libros registrados.

4. 🌐 **Buscar autores vivos en un año específico**  
   Permite ingresar un año y listar todos los autores vivos en esa fecha.

5. 🌍 **Listar libros por idioma**  
   Filtra y muestra los libros según el idioma ingresado (por ejemplo: `en`, `es`, `fr`).

---

## 🧠 Conceptos Aplicados

- Encapsulamiento y uso de clases modelo (`Libro`, `Autor`, etc.).
- Repositorio con interfaz `Repository<T>` para desacoplar operaciones de persistencia.
- Uso de `EntityManager` para manejo de entidades.
- Mapas DTOs con Gson para deserialización de datos JSON.

---

## 📦 Estructura del Proyecto

```

challenge-literalura
│
├── src/main/java
│   ├── com.alura.literalura
│   │   ├── app                  # Clase principal
│   │   ├── principal            # Interfaz de usuario
│   │   ├── modelos              # Entidades JPA
│   │   ├── repository           # Interfaces y clases de persistencia
│   │   └── dto                  # Clases DTO para manejo de JSON
│
├── resources
│   └── application.properties   # Configuración de JPA
│
└── pom.xml                      # Dependencias Maven

````

---

## ▶️ Cómo Ejecutar el Proyecto

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/lucas29951/challenge-literalura.git
   cd challenge-literalura
   ```

2. **Compila y ejecuta desde tu IDE o usa Maven:**
   ```bash
   mvn compile
   mvn exec:java -Dexec.mainClass="com.alura.literalura.app.App"
   ```

3. **¡Listo! Interactúa desde la terminal.**

---

## 📝 Notas

* Los idiomas deben ingresarse con su código ISO (por ejemplo: `en` para inglés, `es` para español).

---

## 📄 Licencia

Este proyecto se encuentra bajo la licencia [MIT](LICENSE).

---
