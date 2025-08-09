// Define que esta clase pertenece al paquete 'net.tinajero.controller'.
// Los paquetes son como carpetas que organizan tu código de manera lógica.
package net.itinajero.controller;

// --- SECCIÓN DE IMPORTACIONES ---
// Aquí se "importan" clases de otras librerías o paquetes para poder usarlas en este archivo.

// Importa la clase Vacante, que es el modelo de datos para una oferta de trabajo.


/*
 *                            --- SECCIÓN DE IMPORTACIONES ---
 * Aquí se "importan" las clases y anotaciones necesarias de otras librerías y paquetes
 * para que la clase HomeController pueda funcionar correctamente.
 * - Importa org.slf4j.LoggerFactory; Importa la fábrica (factory) que crea instancias del Logger.
 * - Importa net.itinajero.model.Vacante: El modelo de datos para una oferta de trabajo.
 * - Importa org.slf4j.*: Clases para el sistema de logging (SLF4J), una forma profesional de registrar eventos.
 * - Importa org.springframework.*: Anotaciones y clases del framework Spring para definir controladores,
 *   Importa mapear URLs (@GetMapping) y pasar datos a las vistas (Model).
 * - Importa java.text.*: Clases para manejar y formatear fechas.
 * - Importa java.util.*: Clases de utilidad de Java, como Date, List y LinkedList.
 */

import net.itinajero.model.Vacante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Controlador principal que maneja las peticiones de la página de inicio y los listados. La anotación @Controller indica a Spring que esta clase es un bean de controlador
 * web.
 */
@Controller
public class HomeController {

	// Es una buena práctica usar un logger en lugar de System.out.println.
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	// Definir el formato de fecha como una constante para reutilizarlo y ser más eficiente.
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

	/**
	 * Maneja las peticiones a la URL raíz ("/") de la aplicación. Muestra la página de inicio. Añadiendo valores de los tributos al modelo en forma estática
	 *
	 * @param modelo
	 * 		El modelo para pasar datos a la vista.
	 * @return El nombre de la vista "home" para ser renderizada.
	 */
	@GetMapping("/")
	public String mostrarHome(Model modelo) {
		// Este método es ideal para mostrar datos generales en la página principal.
		modelo.addAttribute("mensaje", "Bienvenidos a Empleos App");
		modelo.addAttribute("nombre", "Nombre de la vacate ");
		modelo.addAttribute("fecha", new Date());
		modelo.addAttribute("salario", "Salario de la vacante ");
		modelo.addAttribute("vigente", "SI | NO");
		log.info("Mostrando la página de inicio.");
		return "home";
	}

	/**
	 * Muestra una tabla con una lista de todas las vacantes. Añadiendo valores de los tributos al modelo en forma dinámica Mostrando ejemplos de:  log.info, log.error y
	 * log.warn en consola.
	 *
	 * @param model
	 * 		El modelo para pasar la lista de vacantes a la vista.
	 * @return El nombre de la vista "tabla".
	 */
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> lista = getVacantes();
		model.addAttribute("vacantes", lista);
		log.info("Enviando {} vacantes a la vista 'tabla'.", lista.size());
		log.error("¡Alerta roja! ¡Algo se rompió!");
		log.warn("¡Cuidado! Algo inesperado pasó, pero la nave sigue funcionando.");
		return "tabla";
	}

	/**
	 * Muestra una página con un listado simple de nombres de empleos.
	 *
	 * @param modelo
	 * 		El modelo para pasar la lista a la vista.
	 * @return El nombre de la vista "listado".
	 */
	@GetMapping("/listado")
	public String mostrarListado(Model modelo) {
		// List.of() crea una lista inmutable, lo cual es bueno para datos que no cambiarán.
		List<String> lista = List.of("Ingeniería de Software", "Auxiliar Contable", "Vendedor", "Arquitecto de Software");
		modelo.addAttribute("empleo", lista);
		return "listado";
	}

	/**
	 * Muestra la página de detalles para una vacante específica (de ejemplo).
	 *
	 * @param modelo
	 * 		El modelo para pasar el objeto Vacante a la vista.
	 * @return El nombre de la vista "detalle".
	 */
	@GetMapping("/detalle")
	public String mostrarDetalles(Model modelo) {
		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero de Comunicación");
		vacante.setDescripcion("Vacante para ingeniero de comunicaciones con experiencia en redes y telecomunicaciones.");
		vacante.setFecha(new Date());
		vacante.setSalario(10000.0);
		vacante.setDestacado(1); // Asignando datos de ejemplo
		vacante.setImagen("logo_telecom.png"); // Asignando datos de ejemplo
		modelo.addAttribute("vacante", vacante);
		return "detalleSueldo";
	}

	/**
	 * Método de utilidad para generar datos de prueba. En una aplicación real, estos datos vendrían de una capa de servicio. pero con fines de demostración didactic , se
	 * crean aquí directamente hasta que se implemente una base de datos. y la capa de servicio.
	 *
	 * @return Una lista de objetos Vacante con datos completos.
	 */
	private List<Vacante> getVacantes() {
		List<Vacante> lista = new LinkedList<>();
		try{
			// --- Creación de Objetos Vacante con datos completos ---

			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero Civil");
			vacante1.setDescripcion("Solicitamos para el equipo de construcción de puente peatonal");
			vacante1.setFecha(DATE_FORMAT.parse("01-01-2025"));
			vacante1.setSalario(8500.0);
			vacante1.setDestacado(1); // CORREGIDO: Se asigna el valor destacado.
			vacante1.setImagen("logo1.png"); // CORREGIDO: Se asigna la imagen.

			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Contador Público");
			vacante2.setDescripcion("Contador titulado con experiencia en contabilidades de costo");
			vacante2.setFecha(DATE_FORMAT.parse("01-02-2025"));
			vacante2.setSalario(12000.0);
			vacante2.setDestacado(0); // CORREGIDO: Se asigna el valor destacado.
			vacante2.setImagen("logo2.png"); // CORREGIDO: Se asigna la imagen.

			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Ingeniero Eléctrico");
			vacante3.setDescripcion("Ingeniero eléctrico con experiencia en instalaciones industriales");
			vacante3.setFecha(DATE_FORMAT.parse("01-03-2025"));
			vacante3.setSalario(10500.0);
			vacante3.setDestacado(0); // CORREGIDO: Se asigna el valor destacado.
			// No se asigna imagen, usará la de por defecto "no-image.png".

			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Diseñador Gráfico");
			vacante4.setDescripcion("Diseñador gráfico con experiencia en diseño digital y branding");
			vacante4.setFecha(DATE_FORMAT.parse("01-04-2025"));
			vacante4.setSalario(7900.0);
			vacante4.setDestacado(1); // CORREGIDO: Se asigna el valor destacado.
			vacante4.setImagen("logo4.png"); // CORREGIDO: Se asigna la imagen.

			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);

		} catch (ParseException e){
			log.error("Error al parsear la fecha en getVacantes()", e);
		}
		return lista;
	}
}
