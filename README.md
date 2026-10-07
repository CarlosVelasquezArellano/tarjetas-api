# tarjetas-api — API REST de tarjetas

Proyecto de ejemplo para practicar Java 17 + Spring Boot con una API REST sencilla para gestionar tarjetas bancarias.

## Descripción del proyecto

- Java 17
- Spring Boot 3.5
- Maven
- Puerto: 8080
- Datos en memoria con `ArrayList`
- Sin base de datos
- Swagger UI: http://localhost:8080/swagger-ui.html

## Endpoints disponibles

### 1. Listar todas las tarjetas
- Método: `GET`
- Ruta: `/api/v1/tarjetas`
- Descripción: devuelve la lista completa de tarjetas guardadas en memoria.
- Respuesta exitosa: `200 OK`

### 2. Buscar tarjeta por número
- Método: `GET`
- Ruta: `/api/v1/tarjetas/{numero}`
- Descripción: busca una tarjeta por su número.
- Respuesta exitosa: `200 OK`
- Respuesta si no existe: `404 Not Found`

### 3. Buscar tarjetas por tipo
- Método: `GET`
- Ruta: `/api/v1/tarjetas/tipo/{tipo}`
- Descripción: devuelve todas las tarjetas del tipo indicado.
- Valores válidos: `CREDITO`, `DEBITO`
- Respuesta exitosa: `200 OK`
- Respuesta si el tipo no es válido: `400 Bad Request`

### 4. Registrar una tarjeta
- Método: `POST`
- Ruta: `/api/v1/tarjetas`
- Descripción: crea una nueva tarjeta.
- Respuesta exitosa: `201 Created`
- Validaciones:
  - número debe tener 16 caracteres
  - titular obligatorio
  - tipo permitido: `CREDITO` o `DEBITO`
  - si es `CREDITO`, el límite debe ser mayor a 0
  - no puede repetir un número ya registrado
- Respuestas de error:
  - `400 Bad Request` si los datos no son válidos
  - `409 Conflict` si el número ya existe

### 5. Bloquear una tarjeta
- Método: `PUT`
- Ruta: `/api/v1/tarjetas/{numero}/bloquear`
- Descripción: cambia el estado de la tarjeta a `BLOQUEADA`.
- Respuesta exitosa: `200 OK`
- Respuesta si no existe: `404 Not Found`

## Reglas de negocio

- Una tarjeta tiene estos datos:
  - `numero`
  - `titular`
  - `tipo`
  - `limite`
  - `estado`
- El estado inicial al guardar una tarjeta es `ACTIVA`.
- Si ya existe una tarjeta con el mismo número, la API responde con `409 Conflict`.
- Un número inválido o un titular vacío provoca `400 Bad Request`.

## Códigos HTTP esperados

- `200 OK`: consultas y actualizaciones correctas
- `201 Created`: tarjeta registrada correctamente
- `400 Bad Request`: datos inválidos
- `404 Not Found`: tarjeta no encontrada
- `409 Conflict`: número de tarjeta duplicado

## Ejemplo de JSON para crear una tarjeta

```json
{
  "numero": "4111000000000006",
  "titular": "Pedro Salas",
  "tipo": "CREDITO",
  "limite": 2500.00
}
```

## Cómo probar la API

1. Ejecuta la aplicación con el botón ▶ de `TarjetasApiApplication`.
2. Abre Swagger en: http://localhost:8080/swagger-ui.html
3. O usa el archivo `requests.http` para enviar peticiones rápidas.

## Estructura del proyecto

- `controller/TarjetaController.java`: endpoints REST
- `repository/TarjetaRepository.java`: almacenamiento en memoria
- `service/TarjetaServiceImpl.java`: validaciones y lógica
- `model/Tarjeta.java`: modelo de la tarjeta
- `exception/`: manejo de errores

## Qué queda en el ejercicio

El proyecto base ya tiene la estructura principal y algunos endpoints implementados. El objetivo del ejercicio es completar la lógica de acceso a datos y validaciones con la ayuda de GitHub Copilot.
