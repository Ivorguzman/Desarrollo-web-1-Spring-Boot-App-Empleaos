/**
 * La declaración de paquete define el espacio de nombres donde reside esta clase. Ayuda a organizar el código y a prevenir conflictos de nombres. En este caso, la clase
 * HomeController pertenece al paquete 'net.itinajero.controller'.
 */
package net.itinajero.controller;

// --- SECCIÓN DE IMPORTACIONES ---
// Aquí se declaran las clases de otras bibliotecas o paquetes que esta clase necesita para funcionar.

import net.itinajero.model.Vacante;
import net.itinajero.service.Impl_VacanteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

/**
 * Controlador principal de la aplicación que maneja las peticiones web para la página de inicio y otras páginas generales. La anotación @Controller le indica al contenedor
 * de Spring que esta clase es un componente de tipo "Controlador" en el patrón MVC. Spring la escaneará, creará una instancia (un "bean") y la gestionará en su ciclo de
 * vida.
 *
 * @author Ivor
 */
//@Controller
public class HomeController_codigoNativo_java {

	// --- SECCIÓN DE ATRIBUTOS (DEPENDENCIAS) ---

	/**
	 * Creación de una instancia del logger para esta clase. 'private static final': Es una constante (final) compartida (static) por todas las instancias de HomeController.
	 * El logger se usa para registrar mensajes sobre el flujo de la aplicación, lo cual es una práctica estándar para depuración y monitoreo.
	 */
	private static final Logger log = LoggerFactory.getLogger(HomeController_codigoNativo_java.class);


	// --- SECCIÓN DE MÉTODOS MANEJADORES (REQUEST HANDLERS) ---

	/**
	 * Método que maneja las peticiones HTTP GET a la URL raíz ("/") de la aplicación.
	 *
	 * @param modelo
	 * 		El objeto Model que Spring inyecta. Se usa para pasar datos a la vista.
	 * @return El nombre de la vista (plantilla de Thymeleaf, ej. "home.html") que se debe renderizar.
	 * @GetMapping("/"): Mapea este método a la URL raíz. Cada vez que un usuario visite "http://localhost:8080/", este método se ejecutará.
	 */
	@GetMapping("/")
	public String mostrarHome(Model modelo) {
		// modelo.addAttribute: Añade un atributo al modelo. Consiste en un par clave-valor.
		// La clave ("mensaje") será usada en la plantilla HTML para acceder al valor ("Bienvenidos a Empleos App").
		modelo.addAttribute("mensaje", "Bienvenidos a Empleos App");
		modelo.addAttribute("nombre", "Nombre de la vacate ");
		modelo.addAttribute("fecha", new Date());
		modelo.addAttribute("salario", "Salario de la vacante ");
		modelo.addAttribute("vigente", "SI | NO");

		// Se registra un mensaje informativo en la consola o archivo de log.
		log.info("Mostrando la página de inicio.");

		// Devuelve el nombre lógico de la vista. Spring buscará un archivo llamado "home.html" en el directorio de plantillas.
		return "home";
	}

	/**
	 * Método que maneja las peticiones HTTP GET a la URL "/tabla". Su propósito es obtener la lista de todas las vacantes desde la capa de servicio y mostrarla en una
	 * vista.
	 *
	 * @param model
	 * 		El objeto Model para pasar la lista de vacantes a la vista.
	 * @return El nombre de la vista "tabla" que mostrará los datos.
	 */
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {

		final Impl_VacanteService serviceVacantes = new Impl_VacanteService();

		// Llama al método buscarTodas() de la capa de servicio (la dependencia que fue inyectada en el constructor).
		// Esto desacopla al controlador de la lógica de cómo se obtienen los datos (que ahora reside en Impl_VacanteService).
		List<Vacante> listaDeVacantes = serviceVacantes.buscarTodas();

		// Añade la lista completa de vacantes al modelo bajo el nombre "vacantes".
		// La plantilla "tabla.html" podrá iterar sobre esta lista para mostrar cada vacante.
		model.addAttribute("vacantes", listaDeVacantes);

		// Ejemplos de logging con diferentes niveles de severidad.
		log.info("Enviando {} vacantes a la vista 'tabla'.", listaDeVacantes.size());
		log.error("¡Alerta roja! ¡Algo se rompió!"); // Para errores críticos.
		log.warn("¡Cuidado! Algo inesperado pasó, pero la nave sigue funcionando."); // Para advertencias.

		// Devuelve el nombre de la vista que se va a renderizar.
		return "tabla";
	}

	/**
	 * Método que maneja las peticiones HTTP GET a la URL "/listado". Muestra un ejemplo de cómo pasar una lista simple de Strings a una vista.
	 *
	 * @param modelo
	 * 		El objeto Model para pasar la lista de empleos.
	 * @return El nombre de la vista "listado".
	 */
	@GetMapping("/listado")
	public String mostrarListado(Model modelo) {
		// List.of() es un método de fábrica de Java 9+ que crea una lista inmutable.
		// Es ideal para datos estáticos que no cambiarán.
		List<String> lista = List.of("Ingeniería de Software", "Auxiliar Contable", "Vendedor", "Arquitecto de Software");

		// Añade la lista al modelo con el nombre "empleo".
		modelo.addAttribute("empleo", lista);

		// Devuelve el nombre de la vista.
		return "listado";
	}

	/**
	 * Método que maneja las peticiones HTTP GET a la URL "/detalle". Muestra un ejemplo de cómo pasar un único objeto complejo (una Vacante) a una vista.
	 *
	 * @param modelo
	 * 		El objeto Model para pasar el objeto vacante.
	 * @return El nombre de la vista "detalleSueldo".
	 */
	@GetMapping("/detalle")
	public String mostrarDetalles(Model modelo) {
		// Se crea un objeto 'Vacante' con datos de ejemplo (hard-coded).
		// Esto es útil para diseñar y probar la vista de detalle sin tener una base de datos completa.
		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero de Comunicación");
		vacante.setDescripcion("Vacante para ingeniero de comunicaciones con experiencia en redes y telecomunicaciones.");
		vacante.setFecha(new Date());
		vacante.setSalario(10000.0);
		vacante.setDestacado(1);
		vacante.setImagen("logo_telecom.png");

		// Se añade el objeto completo al modelo bajo el nombre "vacante".
		// La vista podrá acceder a todas sus propiedades (ej. vacante.nombre, vacante.salario).
		modelo.addAttribute("vacante", vacante);

		// Devuelve el nombre de la vista de detalle.
		return "detalleSueldo";
	}
}
