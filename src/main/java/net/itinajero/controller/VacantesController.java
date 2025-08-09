// =======================================================================================
// SECCIÓN 1: EL PAQUETE (LA DIRECCIÓN DEL ARCHIVO)
// =======================================================================================
// 'package' es como la dirección en un sobre. Le dice a Java y a Spring
// exactamente en qué "barrio" o carpeta se encuentra esta clase.
// En este caso, está en 'net.itinajero.controller'.
package net.itinajero.controller;

// =======================================================================================
// SECCIÓN 2: LAS IMPORTACIONES (LA CAJA DE HERRAMIENTAS)
// =======================================================================================
// Aquí "importamos" o traemos las herramientas que necesitamos de la biblioteca de Spring.

// Importa la anotación @Controller. Es la herramienta que convierte esta clase
// en un "cerebro" capaz de recibir peticiones web.

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// =======================================================================================
// SECCIÓN 3: LA DECLARACIÓN DE LA CLASE (EL EDIFICIO DE OFICINAS)
// =======================================================================================

// @Controller: Marcamos esta clase como un Controlador. Spring ahora sabe que
// debe gestionarla para atender peticiones de los usuarios.
@Controller
// @RequestMapping("/vacantes"): ¡Muy importante! Establecemos una "ruta base".
// Es como poner un letrero en la entrada del edificio que dice "OFICINAS DE VACANTES".
// Todas las URLs que definamos dentro de esta clase comenzarán automáticamente con /vacantes.
@RequestMapping("/vacantes")
public class VacantesController {

	// =======================================================================================
	// SECCIÓN 4: EL MÉTODO (LA OFICINA Y SU FUNCIÓN)
	// =======================================================================================

	// Esta línea está "comentada" (ignorada por el compilador). Es un ejemplo de cómo
	// se podría haber escrito la anotación usando un atajo más moderno.
	//@GetMapping("/detalle")

	// @RequestMapping(...): Esta es la instrucción principal para este método.
	//   - value = "/view/{id}": Aquí está la magia.
	//     - "/view/": Es una parte fija de la URL.
	//     - "{id}": Es una parte VARIABLE. Le decimos a Spring: "Espera cualquier
	//               número o texto aquí y llámalo 'id'". Es una plantilla.
	//   - method = RequestMethod.GET: Este método solo se activará cuando un usuario
	//     visite la URL para VER o SOLICITAR información (una petición GET).
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	// public String verDetalles(...): La firma del método.
	//   - public: Para que Spring pueda acceder a él.
	//   - String: El métodos devolverá un String, que es el nombre de la vista HTML.
	//   - verDetalles: El nombre que le damos al método.
	//   - (@PathVariable("id") int IdVacante, Model model): Los parámetros que recibe.
	//     - @PathVariable("id"): Le dice a Spring: "Busca la parte de la URL que
	//       llamamos '{id}' en la plantilla...".
	//     - int IdVacante: "...y convierte su valor a un número entero (int) y guárdalo
	//       en una variable llamada 'IdVacante'".
	//     - Model model: Spring nos pasa una "maleta" vacía (el objeto Model) para que
	//       podamos llenarla con datos para la vista.
	public String verDetalles(@PathVariable("id") int IdVacante, Model model) {

		// System.out.println(...): Esta es una línea de depuración.
		// Imprime en la consola de tu IDE el valor de la variable 'IdVacante'.
		// Sirve para que tú, como programador, verifiques que capturaste el ID correctamente.
		System.out.println("IdVacante = " + IdVacante);

		// model.addAttribute(...): Aquí usamos la "maleta".
		//   - "IdVacante": Es la "etiqueta" que le ponemos al dato que vamos a guardar.
		//   - IdVacante: Es el valor real que guardamos (el número que vino en la URL).
		// Estamos metiendo el ID de la vacante en la maleta para enviárselo al HTML.
		model.addAttribute("IdVacante", IdVacante);

		// return "vacantes/detalle": La instrucción final.
		// Le decimos a Spring: "Busca y muestra al usuario el archivo HTML que se
		// encuentra en 'templates/vacantes/detalle.html'".
		return "vacantes/detalle";
	}
}
