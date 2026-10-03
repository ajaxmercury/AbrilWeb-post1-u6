# Gestor de Tareas MVC (Unidad 6)

## Descripción
Proyecto web en Java que implementa un gestor de tareas utilizando el patrón Front Controller, patrón Comando, y Servlet 6.0 con JSTL 3.0.

## Partes del Proyecto

### Parte 1
- CRUD completo (Listar, Agregar, Completar y Eliminar tareas) sin sesión.
- Implementación de modelo Tarea y TareaDAO (datos en memoria).
- Uso del patrón Comando (`ListarComando`, `FormularioComando`, `GuardarComando`, `EliminarComando`, `CompletarComando`).
- Vistas JSP con JSTL y EL, sin scriptlets.

### Parte 2
- Autenticación y control de acceso con Sesiones.
- Roles de usuario (Administrador y Usuario General) limitando las acciones posibles (ej: solo ADMIN elimina).
- Validación por campo en el formulario y retención de valores.
- Internacionalización (i18n) a través de ResourceBundles y retención de idioma por Cookie.

## Decisiones de Diseño
1. **Comando devuelve la vista:** La interfaz `Comando` devuelve un `String` con la ruta de la vista a la cual hacer *forward*, no hace el *forward* directamente. Esto delega la responsabilidad centralizada al controlador.
2. **Front Controller centralizado:** Al tener un solo Servlet como punto de entrada, pudimos agregar la verificación de la sesión y control de acceso (`autenticado`) en un solo lugar (Parte 2), sin tener que repetirlo en múltiples servlets.
3. **Usuario en Sesión, Idioma en Cookie:** El `Usuario` (incluyendo su rol) reside en la `HttpSession` porque debe expirar si el usuario se desconecta o cierra el navegador. Sin embargo, el idioma se guarda en una `Cookie` para que sobreviva cierres de sesión y se pueda mostrar la pantalla de *Login* pre-traducida en visitas futuras.
4. **Longitud Máxima de Título en Contexto:** La variable para la máxima longitud se configuró como un `context-param` en `web.xml` y se lee en el controlador. Esto permite ajustar las políticas de negocio en tiempo de ejecución (o con un simple reinicio/re-despliegue del contenedor) sin requerir recompilar las clases Java.

## Notas Técnicas y Correcciones Obligatorias
- **JSTL 3.0:** Se migró de los prefijos antiguos a las nuevas URIs de Jakarta EE 10 (`jakarta.tags.core` y `jakarta.tags.fmt`).
- **Verificación de Fecha de Hoy:** Para la validación de la fecha en el alta de una tarea, se compara estrictamente la fecha ingresada contra el inicio del día local mediante la manipulación de `Calendar` a medianoche, evitando así que tareas para "hoy" se consideren inválidas debido al tiempo en horas, minutos y segundos de la validación contra un `new Date()` limpio.
- **Seguridad en Completar/Eliminar:** Las rutas por `GET` utilizadas son un compromiso de simplicidad para este laboratorio (ver manual). En escenarios de producción rigurosos deberían manejarse vía `POST`.
- Se corrigió cualquier ambigüedad de redirecciones manejando los `sendRedirect` con retornos `null` en el Front Controller para que no intenten ejecutar doble forward y eviten el error 500 o fallos de renderizado.

## Cómo compilar y ejecutar
1. Compilar con Maven: `mvn clean package`.
2. Desplegar el archivo resultante (`target/gestor-tareas-mvc.war`) en el directorio `webapps` de un servidor Tomcat 10.1.x o superior.
3. Iniciar el servidor Tomcat.
4. Acceder en el navegador: `http://localhost:8080/gestor-tareas-mvc/app`

## Credenciales de prueba
* **Administrador:** `admin` / `Admin123!`
* **Usuario:** `maria` / `Maria2026!`

## Capturas
- [Lista Parte 1](capturas/p1-lista.png)
- [Formulario Parte 1](capturas/p1-formulario.png)
- [Login](capturas/login.png)
- [Lista Tareas (Parte 2)](capturas/lista-tareas.png)
- [Formulario con Errores](capturas/formulario-errores.png)
- [Restricción de Rol](capturas/restriccion-rol.png)
- [Idioma en Inglés](capturas/idioma.png)
