# cursosBDD - Documentación

## Descripción
Este proyecto es una aplicación de ejemplo que implementa un CRUD (Crear, Leer, Actualizar, Eliminar) para gestionar cursos. La aplicación permite realizar las siguientes operaciones:

- Obtener la lista de todos los cursos.
- Obtener un curso por su código.
- Obtener una lista de cursos cuyo título empiece por lo que le ingresemos en la petición.
- Obetner una lista de cursos por un rango de precios.
- Crear un nuevo curso.
- Crear varios cursos mandando una lista de cursos.
- Actualizar la información de un curso.
- Eliminar un curso por su código.


La aplicación está construida en Java y utiliza Spring Boot para crear un servicio REST que expone las operaciones mencionadas.

## Estructura del Proyecto
El proyecto está organizado de la siguiente manera:

- `src/main/java/com/viewnext/kidaprojects/cursosbdd/model`: Contiene la clase `Curso`, que representa el modelo de datos de un libro.
- `src/main/java/com/viewnext/kidaprojects/cursosbdd/service`: Contiene la interfaz `CursoService` y su implementación `CursoServiceImpl`, que define y realiza las operaciones de gestión de libros.

- `src/main/java/com/viewnext/kidaprojects/cursosbdd/restcontrollers`: Contiene el controlador REST `CursoRestController`, que define los puntos de entrada de la API y gestiona las solicitudes HTTP.
- `src/main/java/com/viewnext/kidaprojects/cursosbdd/biblioteca1Application.java`: La clase principal que inicia la aplicación Spring Boot.


## Instrucciones de Uso
Puedes utilizar esta aplicación para realizar operaciones CRUD en libros. Aquí hay algunos ejemplos de cómo usarla:

### Obtener la lista de todos los cursos
- Método: GET
- URL: `http://localhost:8080/cursos`

### Obtener un curso por su código
- Método: GET
- URL: `http://localhost:8080/curso/{codigo}`

### Obtener una lista de cursos por el inicio del nombre del curso
- Método: GET
- URL: `http://localhost:8080/cursos/nombre/{nombre}`

### Obtener una lista de cursos por rango de precio
- Método: GET
- URL: `http://localhost:8080/cursos/rango?precioMinimo={int precioMinimo}&precioMaximo{int precioMaximo}`

### Dar de alta un nuevo curso
- Método: POST
- URL: `http://localhost:8080/curso`
- Cuerpo de la solicitud (JSON):
```json
{
  "codigo": "1234",
  "nombre": "Informática aplicada",
  "numeroHoras": 200,
  "precio": 400
}
```

### Dar de alta varios cursos
- Método: POST
- URL: `http://localhost:8080/cursos`
- Cuerpo de la solicitud (JSON):
```json
[
  {
    "codigo": "1234",
    "nombre": "Curso 1",
    "numeroHoras": 40,
    "precio": 200
  },
  {
    "codigo": "5678",
    "nombre": "Curso 2",
    "numeroHoras": 30,
    "precio": 150
  }
]
```

### Actualizar la información de un curso
- Método: PUT
- URL: `http://localhost:8080/curso`
- Cuerpo de la solicitud (JSON):
```json
{
  "codigo": "1234",
  "nombre": "Informática aplicada",
  "numeroHoras": 200,
  "precio": 400
}
```

### Eliminar un curso por su codigo
- Método: DELETE
- URL: `http://localhost:8080/curso/{codigo}`

### Uso de Postman
Puedes utilizar herramientas como [Postman](https://www.postman.com/) para probar y consumir la API de manera más conveniente. Simplemente crea solicitudes HTTP en Postman con las URL y los datos de solicitud correspondientes.

## Requisitos
Asegúrate de tener instalado lo siguiente antes de ejecutar la aplicación:

- Java 8 o superior.
- Eclipse o cualquier otro IDE de tu elección.
- Maven para gestionar las dependencias del proyecto.

## Ejecución
Para ejecutar la aplicación, sigue estos pasos:

Abre el proyecto en tu IDE (por ejemplo, Eclipse).

1. Busca la clase `CursosBddApplication` en el paquete `com.viewnext.kidaprojects.cursosbdd`.

2. Haz clic derecho en la clase y selecciona "Run as" (Ejecutar como) > "Java Application" (Aplicación Java).

3. La aplicación se iniciará en `http://localhost:8080`. Puedes acceder a la API utilizando las URL mencionadas anteriormente.

## Contribución
Si deseas contribuir a este proyecto, ¡te damos la bienvenida! Puedes fork el repositorio y enviar tus contribuciones a través de pull requests.

## Licencia
Este proyecto está bajo la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para obtener más detalles.

¡Gracias por usar `cursosBDD`!
