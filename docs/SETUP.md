# Guía de entorno de desarrollo

Esta guía explica cómo preparar, compilar, probar y ejecutar el proyecto Java del repositorio `Construccion_Proyecto`.

> Stack: **Java 17 (JDK Temurin)** + **Maven**. CI: GitHub Actions (`.github/workflows/java-ci.yml`).

## 1. Requisitos previos

- **Git** (para clonar y contribuir).
- **JDK 17** (recomendado: Eclipse Temurin). Verifica con:
  ```bash
  java -version
  ```
  Debe mostrar `17.x`.
- **Maven** 3.8+. Verifica con:
  ```bash
  mvn -version
  ```
- (Opcional) **Docker** y Docker Compose, si vas a probar contenedores.
- (Opcional) IDE: IntelliJ IDEA, Eclipse o VS Code con extensiones Java.

### Instalación rápida del JDK 17 (Linux)

```bash
# Ejemplo con SDKMAN (opcional)
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 17.0.12-tem
sdk use java 17.0.12-tem
```

O descarga Temurin desde https://adoptium.net/.

## 2. Clonar y preparar el repositorio

```bash
git clone https://github.com/<tu-usuario>/Construccion_Proyecto.git
cd Construccion_Proyecto
```

Crea una rama de trabajo (ver `CONTRIBUTING.md`):

```bash
git checkout -b feature/mi-cambio
```

## 3. Compilar

Cuando exista `pom.xml` en la raíz:

```bash
mvn clean compile
```

Esto descarga dependencias y compila el código en `target/`.

## 4. Ejecutar pruebas

Local:

```bash
mvn clean test
```

Modo CI (sin colores/interactivo):

```bash
mvn -B test
```

Criterio de éxito: **build exitoso y todos los tests en verde**. Si un test falla, investiga la causa raíz; no deshabilites el test.

## 5. Ejecutar la aplicación

Depende del tipo de proyecto:

- **Java puro (con `main`):**
  ```bash
  mvn exec:java -Dexec.mainClass="com.ejemplo.Main"
  ```
  o ejecutar el JAR generado:
  ```bash
  java -jar target/*.jar
  ```

- **Spring Boot (si se adopta):**
  ```bash
  mvn spring-boot:run
  ```

Ajusta el comando según el `pom.xml` real cuando exista.

## 6. Docker (opcional)

Si el proyecto incluye `Dockerfile`:

```bash
docker build -t construccion-proyecto .
docker run --rm -p 8080:8080 construccion-proyecto
```

Si usas Compose:

```bash
docker compose up --build
```

## 7. Estructura del proyecto

```
Construccion_Proyecto/
├── .github/              # CI y plantillas
├── .agents/              # Prompts/skills para agentes de IA
├── docs/                 # Documentación técnica
├── src/                  # Código fuente (cuando exista)
│   ├── main/java/        # Código de producción
│   └── test/java/        # Pruebas
├── pom.xml               # Build Maven (cuando exista)
├── AGENTS.md             # Límites/responsabilidades de agentes IA
├── CONTRIBUTING.md       # Guía de contribución humana
└── README.md
```

Convenciones Java: paquetes en minúsculas, clases en PascalCase.

## 8. Variables de entorno y secretos

- Nunca commitees secretos, tokens, contraseñas ni `.env`.
- Usa variables de entorno o un `.env` local (debe estar en `.gitignore`).
- En CI, configura los secretos en GitHub Secrets, no en el código.

## 9. Solución de problemas

- **`java: invalid target release: 17`**: asegúrate de usar JDK 17 (`java -version` y `JAVA_HOME`).
- **Maven descarga lento/falla**: revisa proxy/red; limpia cache con `rm -rf ~/.m2/repository` (con cuidado).
- **Tests fallan por entorno**: verifica variables de entorno y puertos libres.
- **Puerto en uso**: cambia el puerto o detén el proceso que lo ocupa.

## 10. Referencias

- `README.md` — resumen del proyecto.
- `CONTRIBUTING.md` — flujo de contribución.
- `AGENTS.md` — límites y responsabilidades de agentes de IA.
- `.github/workflows/java-ci.yml` — pipeline de CI.
