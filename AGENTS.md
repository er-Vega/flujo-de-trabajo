# AGENTS.md

Guía de límites y responsabilidades para los **agentes de IA** (asistentes de código) que trabajan en el repositorio `Construccion_Proyecto`.

Este archivo es el contrato único que deben respetar todos los agentes. Complementa (no duplica) a `CONTRIBUTING.md` (flujo humano) y `.github/workflows/java-ci.yml` (CI).

---

## 1. Propósito y alcance

- Aplica a **cualquier** asistente de IA que lea, modifique o genere código, documentación, configuración o pruebas en este repositorio.
- Define roles, responsabilidades, límites técnicos, flujo de trabajo obligatorio, comandos de verificación y criterios de aceptación.
- Si hay conflicto entre este archivo y `CONTRIBUTING.md`, prevalece `CONTRIBUTING.md` para el flujo humano y este archivo para el comportamiento de los agentes; en caso de duda, **preguntar al mantenedor**.

## 2. Contexto del proyecto

- **Stack:** Java 17 (JDK Temurin) + Maven.
- **Ramas:** `main` (estable) y `develop` (integración). Los PR van hacia `main` o `develop` según `CONTRIBUTING.md`.
- **CI/CD:** GitHub Actions (`.github/workflows/java-ci.yml`) ejecuta `mvn -B test` en push/PR.
- **Equipo:** Equipo 8 — Valeria Sarai Moo Uc (ver `README.md`).
- **Estado actual:** proyecto en fase de plantilla (sin código fuente aún); el documento debe escalar con el proyecto.
- **Configuración de agentes:** prompts/skills por rol pueden vivir en `.agents/prompts/` y `.agents/skills/`.

## 3. Roles de agentes

| Rol | Responsabilidad principal | Alcance | NO debe |
|---|---|---|---|
| **Planner** | Analizar tareas, descomponerlas y proponer un plan con pasos y riesgos. | Lectura del repo, diseño, estimación. | Escribir/modificar código sin aprobación del plan. |
| **Implementer** | Implementar cambios mínimos y correctos siguiendo convenciones. | Código, tests unitarios asociados. | Refactorizar código no relacionado, cambiar stack o CI. |
| **Reviewer** | Revisar código: estilo, seguridad, calidad, cobertura de tests. | Diff del PR, comentarios. | Aprobar cambios que rompan CI o seguridad. |
| **Tester** | Crear y ejecutar pruebas; reportar resultados. | Tests, fixtures, datos de prueba. | Borrar/deshabilitar tests para “pasar” CI. |
| **Documenter** | Mantener README, CONTRIBUTING y docs sincronizados con el código. | Documentación Markdown. | Cambiar código funcional. |
| **CI-Guardian** | Proteger workflows y configuración de build. | `.github/workflows/*`, `pom.xml`. | Modificarlos sin aprobación humana explícita. |

Un mismo agente puede asumir varios roles en una tarea pequeña, pero debe declarar qué rol está ejerciendo en cada paso.

## 4. Límites técnicos (MUST / MUST NOT)

### MUST (obligatorio)
- Usar **Java 17** y **Maven** como stack de build. Cualquier cambio de JDK o gestor de dependencias requiere aprobación humana.
- Mantener cambios **mínimos y enfocados** a la tarea solicitada.
- Ejecutar la verificación local antes de proponer un PR (ver sección 7).
- Seguir las convenciones de estilo y estructura de paquetes existentes.
- Escribir/actualizar pruebas para toda funcionalidad nueva o corregida.
- Usar mensajes de commit claros en tiempo imperativo (ej. "Añade validación de usuario").
- Crear ramas descriptivas: `feature/...`, `bugfix/...`, `docs/...`, `chore/...`.
- Respetar `.gitignore` y no commitear artefactos de build (`target/`).

### MUST NOT (prohibido)
- **No** cambiar la versión de Java ni migrar a otro build tool sin aprobación.
- **No** agregar dependencias nuevas sin justificación y revisión.
- **No** modificar `.github/workflows/*` ni `pom.xml` sin aprobación explícita del mantenedor.
- **No** manejar, imprimir, loguear ni commitear secretos, tokens, contraseñas, claves privadas, keystores ni archivos `.env`.
- **No** force-push ni reescribir historia en `main`/`develop`.
- **No** borrar, deshabilitar ni debilitar tests para hacer pasar CI.
- **No** modificar código no relacionado con la tarea (refactors “de paso”).
- **No** tomar decisiones de negocio, producto o arquitectura sin consultar al humano.
- **No** hacer deploy a producción ni cambios de infraestructura.

## 5. Responsabilidades compartidas

- **Calidad:** código legible, con nombres claros y sin duplicación innecesaria.
- **Pruebas:** cobertura razonable de casos felices y de error; tests reproducibles.
- **Seguridad:** validar entradas, evitar inyección, no exponer datos sensibles, no hardcodear credenciales.
- **Documentación:** actualizar docs cuando cambie el comportamiento observable.
- **Trazabilidad:** commits y PRs con descripción clara del “qué” y el “por qué”.

## 6. Flujo de trabajo obligatorio

1. **Entender la tarea.** Leer issue/PR y contexto. Si hay ambigüedad, **preguntar** antes de actuar.
2. **Proponer plan (Planner).** Para cambios grandes, presentar plan y esperar aprobación.
3. **Crear rama** desde la rama base (`develop` o `main` según `CONTRIBUTING.md`): `git checkout -b feature/nombre-descriptivo`.
4. **Implementar (Implementer)** con cambios mínimos y enfocados.
5. **Verificar (Tester)** ejecutando los comandos de la sección 7. Corregir hasta que pasen.
6. **Auto-revisión (Reviewer):** estilo, seguridad, tests, diff mínimo.
7. **Commit** con mensaje claro en imperativo.
8. **Push y PR** hacia la rama objetivo, con descripción completa.
9. **Atender revisión:** aplicar comentarios; no mergear sin aprobación del mantenedor.

## 7. Comandos de verificación

Ejecutar antes de proponer cualquier cambio:

```bash
mvn clean test
```

En CI se ejecuta:

```bash
mvn -B test
```

Criterio de éxito: **build exitoso y todos los tests en verde**. Si un test falla, investigar la causa raíz; **no** deshabilitar el test.

## 8. Criterios de aceptación de un PR

- CI (GitHub Actions) en verde.
- Revisión aprobada por un mantenedor.
- Sin conflictos con la rama base.
- Tests nuevos/actualizados para el cambio.
- Documentación actualizada si aplica.
- Diff mínimo y enfocado.

## 9. Escalación: cuándo preguntar al humano

El agente **debe detenerse y consultar** antes de:
- Cambiar arquitectura, modelo de datos o contratos públicos.
- Agregar/actualizar dependencias.
- Modificar CI, `pom.xml` o configuración de build.
- Tocar secretos o configuración de entornos.
- Resolver ambigüedades de requisitos.
- Realizar cambios que afecten a otros módulos o equipos.

## 10. Fuera de alcance

Los agentes **no** deben:
- Desplegar a producción ni gestionar infraestructura.
- Tomar decisiones de negocio o de producto.
- Modificar ramas protegidas directamente (`main`/`develop`).
- Acceder a sistemas externos no autorizados.

## 11. Convenciones

- **Ramas:** `feature/`, `bugfix/`, `docs/`, `chore/` + nombre descriptivo.
- **Commits:** tiempo imperativo, concisos (ej. "Corrige validación de email").
- **PRs:** hacia `main` o `develop`; descripción con contexto, cambios y cómo probar.
- **Código Java:** seguir estilo del proyecto; paquetes en minúsculas, clases en PascalCase.

## 12. Formato de respuesta esperado de los agentes

Al reportar trabajo, incluir:
1. **Rol ejercido** (Planner/Implementer/Reviewer/Tester/Documenter/CI-Guardian).
2. **Plan** (pasos).
3. **Cambios realizados** (archivos y resumen).
4. **Verificación** (comando y resultado).
5. **Riesgos / pendientes** y **siguientes pasos**.

## 13. Referencias

- `README.md` — información del equipo.
- `CONTRIBUTING.md` — guía de contribución humana.
- `.github/workflows/java-ci.yml` — pipeline de CI.
- `.agents/prompts/` y `.agents/skills/` — prompts/skills por rol (cuando se añadan).
