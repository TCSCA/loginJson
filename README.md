# API de Autenticación con JSON

Un servicio seguro de autenticación construido con Spring Boot que proporciona autenticación basada en JWT.

## Servicios

### Servicio de Autenticación
- **Endpoint:** `POST /api/auth/login`
  - Autentica usuarios con nombre de usuario y contraseña
  - Devuelve un token JWT para usuarios autenticados
  - Valida credenciales contra un repositorio de usuarios JSON

### Endpoint Seguro
- **Endpoint:** `GET /api/auth/secure`
  - Un endpoint protegido que requiere un token JWT válido
  - Demuestra la validación de tokens JWT

## Requisitos Previos

- Java 17 o superior
- Maven 3.6.3 o superior

## Cómo Ejecutar

1. **Clonar el repositorio:**
   ```bash
   git clone <url-del-repositorio>
   cd loginJson
   ```

2. **Compilar la aplicación:**
   ```bash
   mvn clean install
   ```

3. **Ejecutar la aplicación:**
   ```bash
   mvn spring-boot:run
   ```

4. **La aplicación iniciará en:** `http://localhost:8080`

## Configuración

Crea un archivo `application.properties` o `application.yml` con las siguientes propiedades:

```properties
# Configuración JWT
app.jwt.secret=tu-clave-secreta-cambiar-en-produccion
app.jwt.expiration=3600000 # 1 hora en milisegundos

# Configuración del Servidor
server.port=8080
```

## Uso de la API

### 1. Inicio de Sesión
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "tu-usuario",
  "password": "tu-contraseña"
}
```

### 2. Acceso a Endpoint Protegido
```http
GET /api/auth/secure
Authorization: Bearer tu-token-jwt
```

## Seguridad

- Utiliza JWT (JSON Web Tokens) para autenticación sin estado
- Las contraseñas se almacenan de forma segura con BCrypt
- Todos los endpoints sensibles requieren un token JWT válido

## Dependencias

- Spring Boot 3.x
- Spring Security
- JSON Web Token (JWT)
- Maven

## Licencia

[Su Licencia Aquí]