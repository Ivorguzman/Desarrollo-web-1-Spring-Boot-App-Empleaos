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
 * ¿Cómo funcionan? `import` le dice al compilador dónde encontrar las definiciones de `Vacante`, `Model`, `Controller`, etc.
 * ¿Por qué se usan? Para reutilizar código y hacerlo más legible y conciso.
 */
import net.itinajero.model.Vacante;
import net.itinajero.service.Itf_VacanteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ============ SECCIÓN 3: LA DECLARACIÓN DE LA CLASE (EL EDIFICIO DE OFICINAS) ============
 * ¿Qué es esta clase? Es un Controlador de Spring MVC. Su única responsabilidad es manejar las peticiones web (clics de usuario, URLs) relacionadas con las "Vacantes".
 *
 * ¿Cómo funciona?
 * - `@Controller`: Es una anotación que le dice a Spring: "Esta clase es un componente web, escanéala y úsala para manejar peticiones HTTP".
 * - `@RequestMapping("/vacantes")`: Actúa como un prefijo para todas las URLs que maneja esta clase. Cualquier método con un mapeo aquí dentro comenzará con `/vacantes`.
 *   Por ejemplo, un método mapeado a `/view` será accesible en la URL `/vacantes/view`.
 *
 * ¿Por qué se implementa así?
 * - **Organización**: Agrupa toda la lógica de URLs para las vacantes en un solo lugar.
 * - **Claridad**: Hace que las URLs de la aplicación sean más consistentes y fáciles de entender.
 */
@Controller
@RequestMapping("/vacantes")
public class VacantesController {

	/*
	 * ============ SECCIÓN 4: LOS ATRIBUTOS (LA MEMORIA Y HERRAMIENTAS DE LA CLASE) ============
	 */

	/**
	 * ¿Qué es? Es una instancia de nuestra capa de servicio. Es la herramienta que usará el controlador para obtener o manipular los datos de las vacantes.
	 * Este es el corazón de la INYECCIÓN DE DEPENDENCIAS.
	 *
	 * ¿Cómo funciona?
	 * - `private Itf_VacanteService serviceVacantes;`: Declara una variable para contener el servicio, usando la interfaz como tipo, no la clase concreta.
	 * - `@Autowired`: Esta es una anotación mágica de Spring. Le dice al framework: "Busca un bean (un objeto gestionado por ti) que sea del tipo `Itf_VacanteService`
	 *   y asígnalo automáticamente a esta variable cuando crees el controlador". Spring encontrará la clase `VacanteService_Impl` (porque tiene `@Service`) y la "inyectará" aquí.
	 *
	 * ¿Por qué se usa?
	 * - **Desacoplamiento**: El controlador no sabe (ni le importa) cómo se crea el servicio. Solo sabe que existe y cómo usarlo a través de su interfaz.
	 *   Esto significa que podríamos cambiar la implementación del servicio (por ejemplo, a una que use una base de datos real) sin tener que cambiar NI UNA LÍNEA de código en este controlador.
	 * - **Facilita las Pruebas**: Al hacer pruebas unitarias, podemos "inyectar" una versión falsa (un mock) del servicio para probar el controlador de forma aislada.
	 */
	@Autowired
	private Itf_VacanteService serviceVacantes;

	/**
	 * ¿Qué es? Un objeto para registrar mensajes de log (informativos, de error, etc.). Es el estándar profesional para el seguimiento de la aplicación.
	 * ¿Cómo funciona? `LoggerFactory.getLogger(...)` crea un logger asociado a esta clase. Usamos `log.info()`, `log.error()`, etc., para escribir mensajes.
	 * ¿Por qué se usa? Es mucho más potente y configurable que `System.out.println()`. Permite controlar qué se registra y dónde (consola, archivos) en diferentes entornos (desarrollo, producción).
	 */
	private static final Logger log = LoggerFactory.getLogger(VacantesController.class);

	/*
	 * =======================================================================================
	 * SECCIÓN 5: MÉTODOS DEL CONTROLADOR (LAS OFICINAS Y SUS FUNCIONES)
	 * =======================================================================================
	 */

	/**
	 * ¿Qué hace este método? Muestra los detalles de una vacante específica. Identifica la vacante a través de un valor en la propia ruta de la URL (Path Variable).
	 *
	 * ¿Cómo lo logra?
	 * - `@RequestMapping(value = "/view-patch/{id}", method = RequestMethod.GET)`: Mapea las peticiones GET a URLs como `/vacantes/view-patch/3` o `/vacantes/view-patch/10`.
	 *   La parte `{id}` es un comodín o placeholder.
	 * - `@PathVariable("id") int idVacante`: Esta es la parte clave. Extrae el valor que viene en el lugar de `{id}` en la URL y lo convierte en una variable `int` llamada `idVacante`.
	 * - `model.addAttribute("idVacante", idVacante)`: Pasa el ID capturado a la vista. La vista podrá usar este valor para mostrarlo.
	 * - `return "vacantes/detallePathVariable"`: Le dice a Spring que renderice el archivo `detallePathVariable.html` que se encuentra en `templates/vacantes/`.
	 *
	 * ¿Por qué se implementa así?
	 * - **URLs Limpias (RESTful)**: Este estilo de URL es considerado más limpio y semántico para identificar un recurso específico. Es un pilar en el diseño de APIs REST.
	 * - **Demostración**: Sirve para ilustrar una de las formas más comunes de pasar parámetros a un controlador.
	 */
	@RequestMapping(value = "/view-patch/{id}", method = RequestMethod.GET)
	public String verDetallesPathVariable(@PathVariable("id") int idVacante, Model model) {
		log.info("Handler 'verDetallesPathVariable': Recibido ID de la URL (PathVariable): {}", idVacante);
		model.addAttribute("idVacante", idVacante);
		return "vacantes/detallePathVariable";
	}

	/**
	 * ¿Qué hace este método? También muestra los detalles de una vacante, pero la identifica usando un parámetro de consulta en la URL (Request Param).
	 *
	 * ¿Cómo lo logra?
	 * - `@GetMapping("/view-request")`: Es una forma moderna y corta de `@RequestMapping(method = RequestMethod.GET)`. Mapea peticiones GET a la URL `/vacantes/view-request`.
	 *   La URL completa esperada sería algo como `/vacantes/view-request?idVacante=2`.
	 * - `@RequestParam("idVacante") int idVacante`: Extrae el valor del parámetro `idVacante` que viene después del `?` en la URL y lo asigna a la variable `int`.
	 * - `List<Vacante> objeto_Vacante = serviceVacantes.buscarPorId(idVacante)`: Llama a la capa de servicio para obtener los datos completos de la vacante.
	 * - `model.addAttribute("idVacante", objeto_Vacante)`: Pasa la lista con el objeto `Vacante` encontrado a la vista. La vista ahora tiene acceso a todos los detalles (nombre, salario, etc.).
	 * - `return "vacantes/detalleRequestParam"`: Renderiza la vista `detalleRequestParam.html`.
	 *
	 * ¿Por qué se implementa así?
	 * - **Flexibilidad**: Los `@RequestParam` son ideales para filtros, paginación o parámetros opcionales. Es otra forma muy común de recibir datos.
	 * - **Flujo Completo**: Este método muestra un flujo más realista: recibe un ID, usa el servicio para buscar el objeto completo y pasa ese objeto a la vista para su visualización.
	 */
	@GetMapping("/view-request")
	public String verDatalleRequestParam(@RequestParam("idVacante") int idVacante, Model model) {
		log.info("Handler 'verDatalleRequestParam': Recibido ID como parámetro de URL (RequestParam): {}", idVacante);
		List<Vacante> objeto_Vacante = serviceVacantes.buscarPorId(idVacante);
		log.info("Vacante encontrada en el servicio: {}", objeto_Vacante);
		model.addAttribute("vacante", objeto_Vacante.get(0)); // Se pasa el objeto vacante, no la lista
		return "vacantes/detalleRequestParam";
	}

	/**
	 * ¿Qué hace este método? Simula la eliminación de una vacante, recibiendo el ID a través de un parámetro de consulta.
	 *
	 * ¿Cómo lo logra?
	 * - `@GetMapping("/delete")`: Mapea peticiones GET a `/vacantes/delete?id=...`.
	 * - `@RequestParam("id") int idVacante`: Captura el ID de la vacante a eliminar.
	 * - `model.addAttribute("id", idVacante)`: Pasa el ID a la vista, probablemente para mostrar un mensaje de confirmación como "Se eliminó la vacante con ID: X".
	 * - `return "mensaje"`: Renderiza una vista genérica llamada `mensaje.html`.
	 *
	 * ¿Por qué se implementa así? (Y una nota importante)
	 * - **Demostración**: Ilustra cómo capturar un parámetro para realizar una acción.
	 * - **¡ADVERTENCIA DE BUENA PRÁCTICA!**: Usar una petición GET para una operación destructiva (como eliminar) es una **mala práctica de seguridad**. Las peticiones GET pueden ser cacheadas, indexadas por buscadores y ejecutadas accidentalmente. Las operaciones que modifican datos (Crear, Actualizar, Eliminar) SIEMPRE deben usar métodos HTTP como POST, PUT o DELETE. Este método debería ser refactorizado para usar `@PostMapping` o `@DeleteMapping` en una aplicación real.
	 */
	@GetMapping("/delete")
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		log.info("Handler 'eliminar': Recibido ID para eliminar: {}", idVacante);
		// En una aplicación real, aquí iría la llamada al servicio: serviceVacantes.eliminar(idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}
}
