# ComposeTienda (Geek TCG)
Proyecto Android (Jetpack Compose) migrado desde un sitio HTML + Bootstrap.

## Integración según rúbrica DSY1105
- **Interfaz + navegación:** `Scaffold`, `TopAppBar`, `NavigationBar`, `NavHost` con pantallas (Inicio, Productos, Carrito, Blogs, Nosotros, Contacto, Login, Registro, Usuarios, Admin, Detalle).
- **Formularios + validaciones:** `LoginScreen`, `RegistroScreen`, `ContactoScreen` con `ViewModel`, `isError`, `supportingText`, íconos y mensajes.
- **Gestión de estado + lógica desacoplada:** ViewModels en `ui/vm` con state hoisting.
- **Animaciones:** `AnimatedVisibility` y feedback visual en Detalle/Contacto.
- **Persistencia local:** `Room` (carrito) + `DataStore` (contador carrito).
- **Recursos nativos:** 
  - **Galería** (selector de imágenes).
  - **Ubicación** (FusedLocationProviderClient).
- **Estructura modular/MVVM:** `data/` (Room, DataStore), `ui/` (pantallas), `ui/vm/` (viewmodels).

## Requisitos
- Android Studio Ladybug+
- JDK 17

## Permisos
Declarados en `AndroidManifest.xml`: ubicación fina/gruesa, lectura de imágenes y vibración.

## Ejecutar
1. Abrir con Android Studio y sincronizar Gradle.
2. Ejecutar en un emulador/dispositivo con Google Play Services para la demo de ubicación.

## Sugerencias de commits (GitHub)
- `feat: add room database and repository`
- `feat: login/registro validations with viewmodels`
- `feat: add gallery and location demos`
- `feat: cart view and total with room`
- `chore: theme and icons`

## Equipo
- Nombres de estudiantes aquí.