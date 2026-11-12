# Guía de Contribución

¡Gracias por tu interés en contribuir a este proyecto! Todas las contribuciones, desde reportes de errores hasta nuevas características, son bienvenidas y apreciadas.

Para asegurar un proceso fluido y colaborativo, te pedimos que leas y sigas estas pautas.

> **Agentes de IA:** además de esta guía, deben seguir `AGENTS.md` (límites, roles y flujo de trabajo). Las reglas técnicas viven ahí; aquí se detalla el flujo humano.

## 🐛 Reportar un Problema (Issues)

Si encuentras un error o tienes una sugerencia para una nueva funcionalidad:

1. **Revisa los Issues existentes:** Antes de crear un nuevo ticket, busca en la pestaña de [Issues](../../issues) para asegurarte de que nadie más lo haya reportado ya.
2. **Usa las plantillas:** Al crear un Issue se te ofrecerán plantillas (bug report / feature request). Úsalas para dar contexto completo.
3. **Sé descriptivo:** Proporciona la mayor cantidad de detalles posible. Si es un error, incluye pasos para reproducirlo, el comportamiento esperado y el comportamiento actual.

## 💻 Entorno de Desarrollo local

Para trabajar en este proyecto en tu máquina local, necesitarás tener instalados:

* **Java JDK 17**
* **Maven**
* (Opcional) Docker, si deseas probar los contenedores localmente.

> Nota: el proyecto puede estar en fase de plantilla (sin `pom.xml` aún). Cuando exista, compila y prueba con el comando siguiente.

Para compilar el proyecto y ejecutar las pruebas localmente antes de enviar tu código, utiliza el siguiente comando:

```bash
mvn clean test
```

En CI se ejecuta:

```bash
mvn -B test
```

## 🌿 Convenciones de ramas

Usa nombres descriptivos en kebab-case con un prefijo de tipo:

| Prefijo | Uso | Ejemplo |
|---|---|---|
| `feature/` | Nueva funcionalidad | `feature/validacion-usuario` |
| `bugfix/` | Corrección de error | `bugfix/corrige-login` |
| `docs/` | Documentación | `docs/actualiza-readme` |
| `chore/` | Tareas de mantenimiento | `chore/actualiza-dependencias` |
| `test/` | Pruebas | `test/cobertura-servicio` |

Reglas:
- Una rama = un cambio coherente (evita mezclar features).
- Crea la rama desde `develop` (o `main` si no existe `develop`).
- Borra la rama tras mergear el PR.

## ✍️ Convenciones de commits

Seguimos **Conventional Commits** en español, en tiempo imperativo:

```
<tipo>(<alcance>): <descripción corta>

[cuerpo opcional]

[pie opcional: refs, breaking changes]
```

Tipos permitidos: `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`, `ci`.

Ejemplos:

```bash
feat(auth): añade validación de usuario
fix(api): corrige respuesta 500 en login
docs: actualiza guía de contribución
test(service): añade casos de error
```

Reglas:
- Mensaje en imperativo ("Añade", no "Añadido").
- Primera línea ≤ 72 caracteres.
- Un commit = un cambio lógico.

> (Opcional) Configura la plantilla de commit: `git config commit.template .gitmessage` si existe `.gitmessage` en la raíz.

Para configurarlo automáticamente al clonar, puedes ejecutar:

```bash
# Configurar la plantilla de commit para este repositorio
git config commit.template .gitmessage

# (Opcional) Verificar la configuración
git config --get commit.template
```

Si quieres que se aplique a todos tus repositorios, usa `--global`:

```bash
git config --global commit.template ~/.gitmessage
```

## 🛠️ Proceso para enviar código (Pull Requests)

Si deseas contribuir con código, sigue este flujo de trabajo:

1. **Haz un Fork del repositorio** a tu propia cuenta de GitHub.
2. **Clona tu fork** en tu máquina local:
   ```bash
   git clone https://github.com/TU-USUARIO/nombre-del-proyecto.git
   ```
3. **Crea una nueva rama** para tu característica o corrección de error. Usa un nombre descriptivo (ver convenciones de ramas):
   ```bash
   git checkout -b feature/nombre-de-tu-rama
   ```
4. **Haz tus cambios** en el código. Asegúrate de seguir las convenciones de estilo de código del proyecto y de escribir pruebas (tests) para tus nuevas funcionalidades si aplica.
5. **Haz Commit de tus cambios.** Escribe mensajes de commit claros y concisos en tiempo imperativo (ver convenciones de commits):
   ```bash
   git commit -m "Descripción clara de lo que hace este commit"
   ```
6. **Haz Push de tu rama** a tu fork en GitHub:
   ```bash
   git push origin feature/nombre-de-tu-rama
   ```
7. **Abre un Pull Request (PR)** hacia la rama `main` (o `develop`) del repositorio original. Usa la plantilla de PR y completa el checklist.

## ✅ Requisitos para que tu Pull Request sea aceptado

* **CI/CD:** Tu código debe pasar todas las pruebas automáticas (GitHub Actions) que se ejecutan al crear el PR.
* **Revisión:** Un mantenedor del proyecto revisará tu código. Es posible que te dejen comentarios solicitando algunos ajustes antes de aprobarlo.
* **Sin conflictos:** Asegúrate de que tu rama esté actualizada con la rama principal para evitar conflictos de fusión.
* **Tests:** Incluye pruebas para la funcionalidad nueva o corregida.
* **Documentación:** Actualiza docs si cambia el comportamiento observable.

## 📎 Plantillas

- Issues: `.github/ISSUE_TEMPLATE/` (bug report, feature request).
- Pull Request: `.github/pull_request_template.md`.

¡Gracias de nuevo por ayudar a mejorar este proyecto!
