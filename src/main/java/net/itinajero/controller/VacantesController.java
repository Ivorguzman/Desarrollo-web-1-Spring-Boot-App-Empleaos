// =======================================================================================
// SECCIÓN 1: EL PAQUETE (LA DIRECCIÓN DEL ARCHIVO)
// =======================================================================================
// ¿Qué es?
// 'package' es una forma de organizar tus clases en Java, similar a cómo organizas
// archivos en carpetas en tu computadora. Define el espacio de nombres al que pertenece
// esta clase.

// ¿Cómo funciona?
// La declaración 'package net.itinajero.controller;' indica que la clase VacantesController
// se encuentra dentro de la jerarquía de paquetes 'net.itinajero.controller'.

// ¿Por qué se usa?
// 1. Organización: Ayuda a mantener el código ordenado en proyectos grandes.
// 2. Prevención de conflictos de nombres: Permite tener clases con el mismo nombre
//    en diferentes paquetes sin que haya colisiones.
// 3. Control de acceso: Los paquetes también influyen en la visibilidad de las clases
//    y miembros (modificadores de acceso como 'default' o 'protected').
package net.itinajero.controller;

/*
 =======================================================================================
 SECCIÓN 2: LAS IMPORTACIONES (LA CAJA DE HERRAMIENTAS)
 =======================================================================================
 ¿Qué son?
 Las declaraciones 'import' son como pedir prestadas herramientas específicas de una
 gran caja de herramientas (la biblioteca de Spring Framework y Java estándar).
 Nos permiten usar clases y anotaciones de otros paquetes sin tener que escribir
 su nombre completo (calificado) cada vez.
 ¿Cómo funcionan?
 Cuando importas una clase, le dices al compilador de Java dónde encontrar esa clase
 para que puedas referenciarla directamente por su nombre corto.
 ¿Por qué se usan?
 Para hacer el código más legible y conciso, evitando la necesidad de escribir
 nombres de paquete largos repetidamente.
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para gestionar las operaciones relacionadas con las Vacantes. Esta clase maneja las peticiones web para crear, ver, editar y eliminar vacantes.
 */
@Controller
@RequestMapping("/vacantes")
public class VacantesController {


	/*
	 * ================================ SECCIÓN: ATRIBUTOS DE LA CLASE ======================
	 * ¿Qué es un Logger? Un Logger es una herramienta para registrar mensajes
	 * (información, advertencias, errores) que ocurren mientras tu aplicación se está ejecutando. Es como un diario de la aplicación. ¿Cómo funciona?
	 * `LoggerFactory.getLogger(HomeController.class)` crea una instancia de Logger asociada a esta clase. Luego, puedes usar `log.info()`, `log.error()`, etc., para escribir
	 * mensajes en la consola o en archivos de log. ¿Por qué se usa? Es una práctica mucho mejor que `System.out.println()` para depurar y monitorear aplicaciones en entornos
	 * de producción. Permite controlar el nivel de detalle de los mensajes y dónde se guardan.
	 */
	private static final Logger log = LoggerFactory.getLogger(VacantesController.class);


	/**
	 * Muestra los detalles de una vacante utilizando un PathVariable.
	 *
	 * @param idVacante
	 * 		El ID de la vacante a mostrar.
	 * @param model
	 * 		El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista de detalle.
	 */
	// @GetMapping("/view-path/{id}")// Es Una especialización en forma abreviada de @RequestMapping(value = "/view-patch/{id}"
	@RequestMapping(value = "/view-patch/{id}", method = RequestMethod.GET) // Codificación Antigua Pero Completa
	public String verDetallesPathVariable(@PathVariable("id") int idVacante, Model model) {


		/* `log. info(...)`: Se registra un mensaje informativo en el log, indicando que la página de
		 * inicio está siendo mostrada. Esto es útil para el seguimiento y depuración.*/
		log.info("Mostrando detalles para la vacante método  verDetallesPathVariable(@PathVariable('id')  "
				+ "int idVacante, Model model) con pase de parameter [http://localhost:9098/vacantes/view-patch/{}]", idVacante);



		/*
		 model.addAttribute("idVacante", idVacante);
		 ¿Qué hace? Añade un atributo al objeto 'Model'. Este atributo estará disponible en la vista (HTML) bajo el nombre "idVacante".
		   - "idVacante": Es el nombre (clave) con el que se accederá a este dato en la vista.
		   - idVacante: Es el valor real de la variable que se está pasando.
		 ¿Cómo lo logra? El objeto Model actúa como un puente entre el controlador y la vista.
		 Spring se encarga de que los atributos añadidos al Model sean accesibles en el contexto de la vista que se renderizará.
		 ¿Por qué se usa? Para enviar datos dinámicos desde el controlador a la página HTML,
		 permitiendo que la vista muestre información específica de la vacante.*/
		model.addAttribute("idVacante", idVacante);

		/*
		 return "vacantes/detalle";
		 ¿Qué hace? Indica a Spring el nombre lógico de la vista que debe renderizarse.
		 ¿Cómo lo logra? Spring, utilizando un ViewResolver configurado (por ejemplo, Thymeleaf),
		 buscará un archivo de plantilla HTML llamado 'detallePathVariable. html 'dentro de la carpeta 'templates/vacantes/'.
		 ¿Por qué se usa? Para dirigir al usuario a la página HTML correcta después
		 de que el controlador ha procesado la petición y preparado los datos.*/
		return "vacantes/detallePathVariable";
	}

	/**
	 * Muestra los detalles de una vacante utilizando un RequestParam. Muestra detalle  {@code @GetMapping("/view-request") }.
	 *
	 * @param idVacante
	 * 		El ID de la vacante a mostrar.
	 * @return El nombre de la vista de detalle.
	 */
	@GetMapping("/view-request") // Es Una especialización en forma abreviada de @RequestMapping(value = "/view-request", method = RequestMethod.GET)"
	//@RequestMapping(value = "/view-request", method = RequestMethod.GET) // Codificación Antigua Pero Completa
	public String verDatalleRequestParam(@RequestParam("idVacante") int idVacante, Model model) {

		/* Misma explicación Anterior Misma explicación Anterior  (Query String  ==> idVacante={})*/
		log.info(
				"Mostrando detalles para la vacante método verDatalleRequestParam(@RequestParam(idVacante) int idVacante) con  pase de variable [http://localhost:9098/vacantes/view-request?idVacante={}]",
				idVacante);
		model.addAttribute("idVacante", idVacante);


		return "vacantes/detalleRequestParam";/* Misma explicación Anterior */
	}

	/**
	 * @param idVacante
	 * 		El ID de la vacante a eliminar.
	 * @param model
	 * 		objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista de mensaje.
	 */
	// @GetMapping("/delete") Es Una especialización en forma abreviada de @RequestMapping(value = "/delete", method = RequestMethod.GET)"
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String eliminar(@RequestParam("id") int idVacante, Model model) {
		log.info("Mostrando detalles para la vacante método eliminar(@RequestParam(id) " + "int idVacante, Model model) "
				+ "con  pase de variable [http://localhost:9098/vacantes/delete?id={}]", idVacante);
		model.addAttribute("id", idVacante);
		return "mensaje";
	}
}

















