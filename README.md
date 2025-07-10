


# README - Guess-What Quiz Game

## Descripción del Proyecto

**Guess-What** es una aplicación de juego de preguntas y respuestas desarrollada en JavaFX que utiliza árboles binarios para implementar la lógica de decisión del juego. La aplicación permite a los usuarios jugar juegos de adivinanzas a través de diferentes temas donde el sistema hace preguntas de sí/no para identificar en qué está pensando el usuario. [1](#0-0) 

## Características Principales

- **Interfaz gráfica intuitiva**: Desarrollada con JavaFX y FXML para una experiencia de usuario fluida
- **Múltiples temas**: Soporte para tres temas principales:
  - Animales
  - Tecnología  
  - Personas Famosas
- **Lógica de árbol binario**: Implementa un sistema de decisión basado en árboles binarios para el juego de adivinanzas
- **Gestión de temas**: Permite cargar y cambiar entre diferentes temas de juego
- **Persistencia de datos**: Guarda la configuración y datos de temas usando serialización de Java [2](#0-1) 

## Tecnologías Utilizadas

- **JavaFX 13**: Framework para la interfaz de usuario de aplicaciones de escritorio
- **Java 11**: Lenguaje de programación y entorno de ejecución
- **Maven**: Automatización de construcción y gestión de dependencias  
- **FXML**: Lenguaje de marcado declarativo para interfaces JavaFX
- **Serialización Java**: Persistencia de objetos para datos de temas
- **File I/O**: Almacenamiento basado en texto para configuración y datos [3](#0-2) 

## Estructura del Proyecto

```
ProyectoEDP2/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── Bases/           # Estructuras de datos básicas
│   │   │   │   ├── Archivos.java
│   │   │   │   ├── BinaryTree.java
│   │   │   │   ├── NodeBinaryTree.java
│   │   │   │   └── Tema.java
│   │   │   ├── Clases/          # Controladores y lógica de aplicación
│   │   │   │   ├── App.java
│   │   │   │   ├── CargarController.java
│   │   │   │   ├── InicioController.java
│   │   │   │   ├── PantallaTemporalController.java
│   │   │   │   └── PartidaController.java
│   │   │   └── module-info.java
│   │   └── resources/
│   │       └── Clases/          # Archivos FXML y estilos
│   │           ├── cargar.fxml
│   │           ├── estilos.css
│   │           ├── inicio.fxml
│   │           ├── pantallaTemporal.fxml
│   │           └── partida.fxml
├── pom.xml
└── nbactions.xml
``` [4](#0-3) 

## Requisitos del Sistema

### Prerequisitos

- **Java JDK 11 o superior**
- **JavaFX 13**
- **Maven 3.6+**
- **Sistema operativo**: Windows, macOS o Linux [5](#0-4) 

## Instalación

### 1. Clonar el repositorio
```bash
git clone https://github.com/DERS0214/ED-Proyecto-2P.git
cd ED-Proyecto-2P/ProyectoEDP2
```

### 2. Compilar el proyecto
```bash
mvn clean compile
```

### 3. Ejecutar la aplicación
```bash
mvn javafx:run
```

### Opciones de ejecución adicionales
- **Modo debug**: `mvn javafx:run@debug`
- **Modo IDE**: `mvn javafx:run@ide-debug`
- **Modo profiling**: `mvn javafx:run@ide-profile` [6](#0-5) 

## Cómo Usar la Aplicación

### Flujo Principal del Juego

1. **Pantalla de Inicio**: Al ejecutar la aplicación, se muestra el menú principal con opciones para:
   - Iniciar Partida
   - Cargar Tema
   - Salir

2. **Cargar Tema**: Permite seleccionar entre los temas disponibles (Animales, Tecnología, Personas Famosas) o cargar un tema personalizado

3. **Pantalla Temporal**: Muestra una pantalla de carga antes de comenzar el juego

4. **Partida**: El juego principal donde el usuario responde preguntas de sí/no mientras el sistema navega por el árbol binario de decisiones [7](#0-6) 

### Funcionalidades Principales

- **Selección de Temas**: Cambiar entre diferentes categorías de preguntas
- **Navegación por Árbol**: Sistema de preguntas basado en estructura de árbol binario
- **Interfaz Intuitiva**: Botones claros para respuestas Sí/No
- **Gestión de Archivos**: Carga y guardado automático de configuraciones [8](#0-7) 

## Arquitectura Técnica

### Patrón MVC (Modelo-Vista-Controlador)

La aplicación sigue una arquitectura MVC con cuatro capas principales:

1. **Capa de Presentación**: Archivos FXML para la interfaz de usuario
2. **Capa de Controlador**: Lógica de negocio y manejo de interacciones
3. **Capa de Modelo de Datos**: Estructuras de datos centrales (Tema, BinaryTree, NodeBinaryTree)
4. **Capa de Persistencia**: Gestión de archivos y serialización [9](#0-8) 

### Implementación del Árbol Binario

El núcleo del juego utiliza un árbol binario donde:
- Los nodos internos contienen preguntas
- Las ramas izquierdas representan respuestas "Sí"
- Las ramas derechas representan respuestas "No"
- Los nodos hoja contienen las respuestas finales [10](#0-9) 

## Configuración de Desarrollo

### Dependencias Maven

El proyecto utiliza las siguientes dependencias principales:
- JavaFX Controls (13.0.2)
- JavaFX FXML (13.0.2)
- Maven Compiler Plugin (3.8.0)
- JavaFX Maven Plugin (0.0.4) [11](#0-10) 

## Contribución

Para contribuir al proyecto:

1. Fork el repositorio
2. Crea una rama para tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`)
3. Realiza tus cambios y commit (`git commit -am 'Añadir nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crea un Pull Request

## Licencia

Este proyecto fue desarrollado como parte del curso de Estructuras de Datos, Proyecto del Segundo Parcial.

## Notas

- La aplicación utiliza archivos `.dat` para almacenar datos de temas serializados
- Los archivos de configuración (`seleccionado.txt`, `cantidadPreguntas.txt`) se generan automáticamente
- El proyecto incluye temas predefinidos pero permite la carga de temas personalizados
- La interfaz está diseñada para ser intuitiva y fácil de navegar [12](#0-11) 
