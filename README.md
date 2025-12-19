# SafeBank 🏦

SafeBank es una aplicación bancaria web segura y moderna desarrollada como proyecto de portafolio. Permite a los usuarios gestionar sus finanzas, realizar transferencias en tiempo real y llevar un control de sus movimientos.

## 🚀 Características

*   **Gestión de Usuarios**: Registro seguro y autenticación (Login/Logout).
*   **Bono de Bienvenida**: ¡Todos los nuevos usuarios reciben **$1000.00** al registrarse!
*   **Operaciones Bancarias**:
    *   **Depósitos**: Ingresa dinero a tu cuenta.
    *   **Retiros**: Retira fondos cuando lo necesites.
    *   **Transferencias**: Envía dinero a otros usuarios de SafeBank al instante.
*   **Historial de Transacciones**: Visualiza todos tus movimientos con indicadores de color (Verde para ingresos, Rojo para egresos).
*   **Seguridad**: Contraseñas encriptadas con BCrypt y protección de rutas.
*   **API REST**: Backend robusto documentado con OpenAPI (Swagger).

## 🛠️ Tecnologías Utilizadas

*   **Java 17**: Lenguaje principal.
*   **Spring Boot 3**: Framework para el backend.
*   **Spring Security**: Gestión de autenticación y autorización.
*   **Spring Data JPA**: Persistencia de datos.
*   **H2 Database**: Base de datos en memoria (para desarrollo y pruebas rápidas).
*   **Thymeleaf**: Motor de plantillas para el frontend.
*   **HTML5 / CSS3 / JavaScript**: Interfaz de usuario moderna y responsiva.
*   **Maven**: Gestión de dependencias.

## 📋 Requisitos Previos

*   Java JDK 17 o superior.
*   Maven 3.6 o superior.

## ⚙️ Instalación y Ejecución

1.  **Clonar el repositorio** (o descargar el código):
    ```bash
    git clone (https://github.com/Niltra/SafeBank.git)
    cd Proyecto_Contratacion
    ```

2.  **Compilar y Ejecutar**:
    ```bash
    mvn spring-boot:run
    ```

3.  **Acceder a la aplicación**:
    *   Abre tu navegador en: `http://localhost:8080`

## 📖 Documentación de la API

Puedes explorar y probar la API directamente a través de Swagger UI:
*   URL: `http://localhost:8080/swagger-ui.html`

## 🧪 Usuarios de Prueba

Puedes registrar tus propios usuarios, o usar los siguientes si has ejecutado los scripts de prueba:
*   **Usuario A**: `alice@test.com` / `pass123`
*   **Usuario B**: `bob@test.com` / `pass123`

## 📂 Estructura del Proyecto

```
src/main/java/com/safebank/
├── config/       # Configuraciones (Seguridad, OpenAPI)
├── controller/   # Controladores Web y REST
├── dto/          # Objetos de Transferencia de Datos
├── entity/       # Entidades JPA (Usuario, Transaccion)
├── repository/   # Interfaces de Repositorio
└── service/      # Lógica de Negocio
```

---
Desarrollado con ❤️ para demostración técnica.
