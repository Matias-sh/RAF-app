# Sistema de Autenticación - RAF App

## Resumen de Implementación

Se ha implementado un sistema completo de autenticación que incluye:

### ✅ Funcionalidades Implementadas

#### 1. **Autenticación Normal (Email/Contraseña)**
- **Login**: Usuario ingresa email y contraseña
- **Registro**: Usuario crea cuenta con nombre, apellido, email y contraseña
- **Validaciones**: Campos requeridos, formato de email, longitud mínima de contraseña
- **Integración con Backend**: Usa las rutas `/auth/login` y `/auth/register`

#### 2. **Autenticación con Google**
- **Login con Google**: Usuario se autentica usando su cuenta de Google
- **Registro con Google**: Si el usuario no existe, se crea automáticamente
- **Flujo Inteligente**: Intenta login primero, si falla, registra al usuario
- **Integración con Backend**: Usa las rutas `/auth/google` y `/auth/google/register`

#### 3. **Gestión de Sesiones**
- **AuthManager**: Maneja el almacenamiento seguro de tokens y datos de usuario
- **Persistencia**: Los datos se mantienen entre sesiones de la app
- **Expiración**: Tokens expiran después de 24 horas
- **Logout Completo**: Cierra sesión tanto local como en el servidor

### 🔧 Componentes Técnicos

#### **AuthManager**
```kotlin
// Funciones principales:
- saveUserSession(user, token)
- getCurrentUser()
- isUserLoggedIn()
- logout()
- getAuthToken()
```

#### **AuthService (Retrofit)**
```kotlin
// Endpoints implementados:
- POST /auth/login
- POST /auth/register  
- POST /auth/logout
- POST /auth/refresh
- POST /auth/google
- POST /auth/google/register
```

#### **Actividades**
- **LoginActivity**: Login normal + Google Sign-In
- **RegisterActivity**: Registro normal + Google Sign-In
- **MainActivity**: Verificación de sesión + logout

### 🎨 Interfaz de Usuario

#### **LoginActivity**
- Campo de usuario (email)
- Campo de contraseña
- Botón "Login"
- Botón de Google Sign-In
- Enlace "¿No tienes cuenta? Crear Cuenta"

#### **RegisterActivity**
- Campo de nombre
- Campo de apellido
- Campo de email
- Campo de contraseña
- Botón "Crear Cuenta"
- Botón de Google Sign-In
- Enlace "¿Ya tienes cuenta? Iniciar Sesión"

### 🔒 Seguridad

1. **Tokens JWT**: Autenticación basada en tokens
2. **SharedPreferences**: Almacenamiento local seguro
3. **Interceptores**: Tokens automáticamente incluidos en requests
4. **Validación de Expiración**: Verificación automática de tokens
5. **Logout del Servidor**: Invalidación de tokens en el backend

### 📱 Flujo de Usuario

#### **Primer Uso (Registro)**
1. Usuario abre la app
2. Va a "Crear Cuenta"
3. Completa formulario O usa Google Sign-In
4. Se crea cuenta en el backend
5. Se guarda sesión localmente
6. Redirige a MainActivity

#### **Uso Subsecuente (Login)**
1. Usuario abre la app
2. Completa credenciales O usa Google Sign-In
3. Se valida con el backend
4. Se actualiza sesión local
5. Redirige a MainActivity

#### **Logout**
1. Usuario presiona "Cerrar sesión" en el menú
2. Se invalida token en el servidor
3. Se limpian datos locales
4. Se cierra sesión de Google (si aplica)
5. Redirige a LoginActivity

### 🚀 Características Avanzadas

- **Auto-registro con Google**: Si el usuario no existe, se crea automáticamente
- **Manejo de Errores**: Mensajes específicos para diferentes tipos de errores
- **Logging Detallado**: Para debugging y monitoreo
- **Validación Robusta**: Campos requeridos y formatos correctos
- **UI Consistente**: Mantiene el diseño existente sin cambios

### 📋 Rutas de API Esperadas

El backend debe implementar estas rutas:

```
POST /auth/login
Body: { "email": "user@example.com", "password": "password123" }
Response: { "token": "jwt_token", "user": { ... } }

POST /auth/register  
Body: { "firstName": "John", "lastName": "Doe", "email": "user@example.com", "password": "password123" }
Response: { "token": "jwt_token", "user": { ... } }

POST /auth/google
Body: { "idToken": "google_id_token" }
Response: { "token": "jwt_token", "user": { ... } }

POST /auth/google/register
Body: { "idToken": "google_id_token" }
Response: { "token": "jwt_token", "user": { ... } }

POST /auth/logout
Headers: { "Authorization": "Bearer jwt_token" }
Response: 200 OK

POST /auth/refresh
Headers: { "Authorization": "Bearer jwt_token" }
Response: { "token": "new_jwt_token", "user": { ... } }
```

### ✅ Estado del Sistema

- ✅ Login normal implementado
- ✅ Registro normal implementado  
- ✅ Google Sign-In implementado
- ✅ Google Auto-registro implementado
- ✅ Gestión de sesiones implementada
- ✅ Logout completo implementado
- ✅ Validaciones implementadas
- ✅ Manejo de errores implementado
- ✅ UI consistente mantenida
- ✅ Integración con backend lista

El sistema está **COMPLETO** y listo para usar. Solo necesita que el backend implemente las rutas de autenticación correspondientes.

