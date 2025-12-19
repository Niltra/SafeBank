# 🏦 SafeBank - Sistema Bancario Seguro


**SafeBank** es una aplicación web de banca online robusta y moderna, diseñada para demostrar la implementación de un sistema financiero seguro utilizando el ecosistema de Spring Boot. Este proyecto simula operaciones bancarias reales con un enfoque en la seguridad, la integridad de los datos y una experiencia de usuario fluida.



## 🚀 Características Principales

*   **🔐 Autenticación Robusta**: Sistema de registro y login seguro utilizando Spring Security y BCrypt para el hashing de contraseñas.
*   **💸 Operaciones en Tiempo Real**:
    *   **Transferencias**: Envío de dinero instantáneo entre usuarios.
    *   **Depósitos y Retiros**: Gestión de fondos con validaciones de saldo.
*   **🎁 Bono de Bienvenida**: Sistema automático que otorga **$1,000.00** a cada nuevo usuario registrado.
*   **📊 Dashboard Interactivo**: Visualización clara del saldo, número de cuenta (IBAN ficticio) e historial de transacciones.
*   **🎨 UI Intuitiva**: Interfaz limpia y responsiva con indicadores visuales para ingresos (verde) y egresos (rojo).
*   **📚 Documentación API**: Endpoints REST documentados automáticamente con OpenAPI (Swagger).

## 🛠️ Stack Tecnológico

*   **Backend**: Java 17, Spring Boot 3 (Web, Security, Data JPA, Validation).
*   **Base de Datos**: H2 Database (In-Memory) para despliegue rápido y pruebas.
*   **Frontend**: Thymeleaf, HTML5, CSS3, JavaScript (Vanilla).
*   **Testing**: JUnit 5, Mockito.
*   **Build Tool**: Maven.

## 📋 Requisitos Previos

Para ejecutar este proyecto localmente necesitas:
*   [Java JDK 17](https://www.oracle.com/java/technologies/downloads/) o superior.
*   [Maven](https://maven.apache.org/) (opcional si usas el wrapper incluido).

## ⚙️ Instalación y Despliegue

1.  **Clonar el repositorio**:
    ```bash
    git clone (https://github.com/Niltra/SafeBank.git))
    cd safebank
    ```

2.  **Compilar y Ejecutar**:
    ```bash
    mvn spring-boot:run
    ```

3.  **Acceder a la Aplicación**:
    *   Abre tu navegador en: `http://localhost:8080`
    *   Swagger UI: `http://localhost:8080/swagger-ui.html`

## 🧪 Credenciales de Prueba

Puedes registrar tus propios usuarios o usar estos si has cargado datos de prueba:

| Rol | Email | Contraseña |
| :--- | :--- | :--- |
| **Usuario A** | `alice@test.com` | `pass123` |
| **Usuario B** | `bob@test.com` | `pass123` |

## 📂 Estructura del Proyecto

El código sigue una arquitectura en capas limpia y modular:

```
com.safebank
├── config/       # Configuración de Seguridad y Beans
├── controller/   # Controladores MVC y REST
├── dto/          # Data Transfer Objects (Request/Response)
├── entity/       # Entidades JPA (Modelo de Datos)
├── repository/   # Interfaces de Acceso a Datos (DAO)
└── service/      # Lógica de Negocio y Transaccionalidad
```

## 🤝 Contribución

Las contribuciones son bienvenidas. Si tienes ideas para mejorar SafeBank, siéntete libre de abrir un issue o enviar un pull request.

---
*Desarrollado con ❤️ como proyecto de portafolio profesional.*
    
