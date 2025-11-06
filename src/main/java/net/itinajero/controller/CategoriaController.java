/*
 * === SECCIÓN 1: EL PAQUETE (LA DIRECCIÓN DEL ARCHIVO) ===
 * ¿Qué es? Un paquete en Java es un contenedor que agrupa clases relacionadas, como una carpeta.
 * ¿Cómo funciona? `package net.itinajero.controller;` ubica esta clase dentro del paquete `controller`.
 * ¿Por qué se usa? Para organizar el código, evitar conflictos de nombres y controlar el acceso a las clases.
 */
package net.itinajero.controller;

/*
 * === SECCIÓN 2: LAS IMPORTACIONES (LA CAJA DE HERRAMIENTAS) ===
 * ¿Qué son? Son directivas para usar clases de otros paquetes sin escribir su nombre completo.
 * ¿Cómo funcionan? `import` le dice al compilador dónde encontrar las definiciones de `Logger`, `Model`, `Controller`, etc.
 * ¿Por qué se usan? Para reutilizar código y hacerlo más legible y conciso.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ============ SECCIÓN 3: LA DECLARACIÓN DE LA CLASE (EL EDIFICIO DE OFICINAS) ============
 * ¿Qué es esta clase? Es un Controlador de Spring MVC. Su responsabilidad es manejar las peticiones web (clics de usuario, URLs) relacionadas con las "Categorías".
 *
 * ¿Cómo funciona?
 * - `@Controller`: Es una anotación que le dice a Spring: "Esta clase es un componente web, escanéala y úsala para manejar peticiones HTTP".
 * - `@RequestMapping("/categorias")`: Actúa como un prefijo para todas las URLs que maneja esta clase. Cualquier método con un mapeo aquí dentro comenzará con `/categorias`.
 *   Por ejemplo, un método mapeado a `/index` será accesible en la URL `/categorias/index`.
 *
 * ¿Por qué se implementa así?
 * - **Organización**: Agrupa toda la lógica de URLs para las categorías en un solo lugar.
 * - **Claridad**: Hace que las URLs de la aplicación sean más consistentes y fáciles de entender.
 */
@Controller
@RequestMapping("/categorias")
public class CategoriaController {

	/*
	 * ============ SECCIÓN 4: LOS ATRIBUTOS (LA MEMORIA Y HERRAMIENTAS DE LA CLASE) ============
	 */

	/**
	 * ¿Qué es? Un objeto para registrar mensajes de log (informativos, de error, etc.). Es el estándar profesional para el seguimiento de la aplicación.
	 * ¿Cómo funciona? `LoggerFactory.getLogger(...)` crea un logger asociado a esta clase. Usamos `log.info()`, `log.error()`, etc., para escribir mensajes.
	 * ¿Por qué se usa? Es mucho más potente y configurable que `System.out.println()`. Permite controlar qué se registra y dónde (consola, archivos) en diferentes entornos (desarrollo, producción).
	 */
	private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);

	/*
	 * =======================================================================================
	 * SECCIÓN 5: MÉTODOS DEL CONTROLADOR (LAS OFICINAS Y SUS FUNCIONES)
	 * =======================================================================================
	 */

	/**
	 * ¿Qué hace este método? Maneja peticiones GET a `/categorias/index`. Su propósito es mostrar la página principal de la sección de categorías.
	 *
	 * ¿Cómo lo logra?
	 * - `@RequestMapping(value = "/index", method = RequestMethod.GET)`: Mapea este método a las peticiones GET para la URL `/categorias/index`.
	 * - `public String mostrarIndex(Model model)`: Define el método. Recibe un objeto `Model` que sirve como puente para pasar datos del controlador a la vista.
	 * - `return "categorias/listCategorias"`: Devuelve el nombre lógico de la vista. Spring buscará un archivo `listCategorias.html` dentro de la carpeta `templates/categorias/`.
	 *
	 * ¿Por qué se implementa así?
	 * - **Patrón MVC**: Separa la lógica de navegación (controlador) de la presentación (vista).
	 * - **Preparado para el futuro**: Aunque ahora no se pasan datos, el `Model` está listo para cuando necesitemos enviar una lista de categorías desde la base de datos a la vista.
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String mostrarIndex(Model model) {
		return "categorias/listCategorias";
	}

	/**
	 * ¿Qué hace este método? Maneja peticiones GET a `/categorias/crear`. Su propósito es mostrar el formulario para que el usuario pueda crear una nueva categoría.
	 *
	 * ¿Cómo lo logra?
	 * - `@RequestMapping(value = "/crear", method = RequestMethod.GET)`: Mapea este método a las peticiones GET para la URL `/categorias/crear`.
	 * - `return "categorias/formCategoria"`: Le dice a Spring que renderice la vista `formCategoria.html` que se encuentra en `templates/categorias/`.
	 *
	 * ¿Por qué se implementa así?
	 * - **Flujo de usuario claro**: Es el patrón estándar para mostrar un formulario. El usuario pide ver el formulario (GET), y luego lo enviará (POST).
	 * - **Separación de acciones**: Separa la acción de "mostrar el formulario" de la acción de "procesar los datos del formulario".
	 */
	@RequestMapping(value = "/crear", method = RequestMethod.GET)
	public String crear() {
		return "categorias/formCategoria";
	}

	/**
	 * ¿Qué hace este método? Procesa los datos enviados desde el formulario de creación de categorías. Se activa con peticiones POST a `/categorias/save`.
	 *
	 * ¿Cómo lo logra?
	 * - `@RequestMapping(value = "/save", method = RequestMethod.POST)`: Mapea este método específicamente a peticiones POST. Esto es crucial para la seguridad y las buenas prácticas, ya que las operaciones que modifican datos no deben ser GET.
	 * - `@RequestParam("var_nombre") String var_nombre`: Esta anotación extrae los datos enviados en el cuerpo de la petición POST. Busca un parámetro llamado `var_nombre` (que corresponde al atributo `name` de un campo `<input>` en el formulario HTML) y lo asigna a la variable `String var_nombre`.
	 * - `log.info(...)`: Registra los valores recibidos en el log del servidor. Esto es extremadamente útil para depurar y verificar qué datos están llegando al controlador.
	 * - `System.out.println(...)`: Una forma más simple (pero menos recomendada que el logging) de ver una salida en la consola.
	 * - `return "categorias/formCategoria"`: Después de procesar los datos, devuelve al usuario a la vista del formulario. (Nota: Una mejor práctica sería usar `redirect`, como se explica abajo).
	 *
	 * ¿Por qué se implementa así?
	 * - **Procesamiento de Formularios**: Es el patrón estándar para recibir y manejar datos enviados por el usuario.
	 * - **Seguridad**: Usar POST previene que los datos se envíen como parte de la URL y protege contra ciertos tipos de ataques.
	 * - **Depuración**: El logging permite a los desarrolladores rastrear el flujo de datos y diagnosticar problemas sin interrumpir la ejecución.
	 * - **Patrón Post-Redirect-Get (PRG) - (Mejora Sugerida)**: En una aplicación real, en lugar de `return "categorias/formCategoria";`, se usaría `return "redirect:/categorias/index";`. Esto redirige al usuario a la página de la lista después de guardar, evitando reenvíos accidentales del formulario si el usuario refresca la página. La implementación actual es más simple para fines de demostración inicial.
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@RequestParam("var_nombre") String var_nombre, @RequestParam("var_descripcion") String var_descripcion) {

		log.info("Handler 'guardar': Procesando datos del formulario para nueva categoría.");
		log.info("Nombre recibido: {}", var_nombre);
		log.info("Descripción recibida: {}", var_descripcion);

		// Aquí iría la lógica para llamar a un servicio y guardar la categoría en la base de datos.
		System.out.println("Guardando la categoría...");

		// Se devuelve al usuario a la vista del formulario.
		return "categorias/formCategoria";
	}
}
