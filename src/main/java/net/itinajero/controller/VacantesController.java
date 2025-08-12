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

// =======================================================================================
// SECCIÓN 2: LAS IMPORTACIONES (LA CAJA DE HERRAMIENTAS)
// =======================================================================================
// ¿Qué son?
// Las declaraciones 'import' son como pedir prestadas herramientas específicas de una
// gran caja de herramientas (la biblioteca de Spring Framework y Java estándar).
// Nos permiten usar clases y anotaciones de otros paquetes sin tener que escribir
// su nombre completo (calificado) cada vez.

// ¿Cómo funcionan?
// Cuando importas una clase, le dices al compilador de Java dónde encontrar esa clase
// para que puedas referenciarla directamente por su nombre corto.

// ¿Por qué se usan?
// Para hacer el código más legible y conciso, evitando la necesidad de escribir
// nombres de paquete largos repetidamente.

import org.springframework.stereotype.Controller; // Importa @Controller: Marca esta clase como un componente de Spring que maneja peticiones web.
import org.springframework.ui.Model;             // Importa Model: Una interfaz que nos permite pasar datos desde el controlador a la vista (HTML).
import org.springframework.web.bind.annotation.PathVariable; // Importa @PathVariable: Extrae valores de la URL de la petición.
import org.springframework.web.bind.annotation.RequestMapping; // Importa @RequestMapping: Mapea peticiones web a métodos específicos del controlador.
import org.springframework.web.bind.annotation.RequestMethod; // Importa RequestMethod: Define el tipo de método HTTP (GET, POST, etc.) para una petición.

// =======================================================================================
// SECCIÓN 3: LA DECLARACIÓN DE LA CLASE (EL EDIFICIO DE OFICINAS)
// =======================================================================================

// ¿Qué es?
// Esta es la definición de la clase 'VacantesController', que actuará como el
// punto de entrada para manejar las peticiones relacionadas con las "vacantes"
// en nuestra aplicación web.

// ¿Cómo funciona?
// Spring escanea las clases anotadas con @Controller y las gestiona para que
// puedan responder a las solicitudes HTTP de los usuarios.

// ¿Por qué se implementa así?
// Es el patrón estándar en Spring MVC para crear controladores que manejan la
// lógica de negocio y la interacción con las vistas.

@Controller // ¿Qué hace? Esta anotación le dice a Spring que esta clase es un "controlador".
            // ¿Cómo lo logra? Spring la detecta durante el escaneo de componentes y la registra
            // para que pueda manejar las peticiones web.
            // ¿Por qué se usa? Es fundamental para que Spring MVC reconozca esta clase como
            // una que puede procesar solicitudes HTTP.

@RequestMapping("/vacantes") // ¿Qué hace? Establece una "ruta base" para todas las URLs manejadas por los métodos
                             // dentro de esta clase. Es como un prefijo para todas las URLs.
                             // ¿Cómo lo logra? Cualquier método dentro de esta clase que tenga su propio
                             // @RequestMapping, verá su ruta combinada con esta ruta base. Por ejemplo,
                             // si un método tiene @RequestMapping("/detalle"), la URL completa será "/vacantes/detalle".
                             // ¿Por qué se usa? Ayuda a organizar las URLs de forma lógica y evita la repetición
                             // del prefijo "/vacantes" en cada método.
public class VacantesController {

	// =======================================================================================
	// SECCIÓN 4: EL MÉTODO (LA OFICINA Y SU FUNCIÓN)
	// =======================================================================================

	// ¿Qué es?
	// Este método, 'verDetalles', está diseñado para mostrar los detalles de una vacante
	// específica cuando un usuario accede a una URL con un ID de vacante.

	// ¿Cómo funciona?
	// Recibe el ID de la vacante de la URL, lo imprime para depuración y luego pasa
	// ese ID a la vista para que pueda mostrar la información correspondiente.

	// ¿Por qué se implementa así?
	// Es la forma en que Spring MVC permite que un controlador reciba datos de la URL
	// (como un ID) y prepare una vista dinámica con esos datos.

	// @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	// ¿Qué hace? Mapea una petición HTTP GET a este método.
	//   - value = "/view/{id}": Define la URL específica que activará este método.
	//     - "/view/": Es una parte fija de la URL.
	//     - "{id}": Es una variable de ruta. Spring capturará cualquier valor en esta
	//       posición de la URL y lo asignará a un parámetro del método.
	//   - method = RequestMethod.GET: Indica que este método solo responderá a peticiones
	//     HTTP de tipo GET (cuando el navegador solicita una página para verla).
	// ¿Cómo lo logra? Spring intercepta las peticiones que coinciden con esta URL y
	// el método HTTP, y luego invoca este método 'verDetalles'.
	// ¿Por qué se usa? Para definir de manera precisa qué tipo de petición y qué URL
	// debe manejar este método en particular.
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	// public String verDetalles(@PathVariable("id") int IdVacante, Model model)
	// ¿Qué hace? Esta es la firma del método.
	//   - public: Indica que el método es accesible desde cualquier otra clase.
	//   - String: El tipo de dato que el método devolverá. En Spring MVC, un String
	//     devuelto por un método de controlador generalmente es el nombre lógico de una vista.
	//   - verDetalles: El nombre del método, descriptivo de su función.
	//   - @PathVariable("id") int IdVacante:
	//     - @PathVariable("id"): Anotación que le dice a Spring que tome el valor
	//       de la variable de ruta llamada "id" (definida en @RequestMapping("/view/{id}"))
	//       y lo inyecte en el parámetro del método.
	//     - int IdVacante: El tipo de dato (entero) y el nombre de la variable donde
	//       se almacenará el ID de la vacante.
	//   - Model model:
	//     - Model: Un objeto proporcionado por Spring que actúa como un "mapa" o
	//       "contenedor" para pasar datos desde el controlador a la vista.
	// ¿Cómo lo logra? Spring inyecta automáticamente el valor de la variable de ruta
	// y un objeto Model cuando invoca este método.
	// ¿Por qué se usa? Permite al controlador recibir datos dinámicos de la URL y
	// preparar los datos necesarios para que la vista (HTML) los muestre.
	public String verDetalles(@PathVariable("id") int IdVacante, Model model) {

		// System.out.println("IdVacante = " + IdVacante);
		// ¿Qué hace? Imprime el valor de la variable 'IdVacante' en la consola del servidor.
		// ¿Cómo lo logra? Utiliza el método estándar de Java para imprimir en la salida
		// de la consola.
		// ¿Por qué se usa? Principalmente para propósitos de depuración. Permite al
		// desarrollador verificar que el ID se está capturando correctamente de la URL.
		System.out.println("IdVacante = " + IdVacante);

		// model.addAttribute("IdVacante", IdVacante);
		// ¿Qué hace? Añade un atributo al objeto 'Model'. Este atributo estará disponible
		// en la vista (HTML) bajo el nombre "IdVacante".
		//   - "IdVacante": Es el nombre (clave) con el que se accederá a este dato en la vista.
		//   - IdVacante: Es el valor real de la variable que se está pasando.
		// ¿Cómo lo logra? El objeto Model actúa como un puente entre el controlador y la vista.
		// Spring se encarga de que los atributos añadidos al Model sean accesibles en el contexto
		// de la vista que se renderizará.
		// ¿Por qué se usa? Para enviar datos dinámicos desde el controlador a la página HTML,
		// permitiendo que la vista muestre información específica de la vacante.
		model.addAttribute("IdVacante", IdVacante);

		// return "vacantes/detalle";
		// ¿Qué hace? Indica a Spring el nombre lógico de la vista que debe renderizarse.
		// ¿Cómo lo logra? Spring, utilizando un ViewResolver configurado (por ejemplo,
		// Thymeleaf), buscará un archivo de plantilla HTML llamado 'detalle.html'
		// dentro de la carpeta 'templates/vacantes/'.
		// ¿Por qué se usa? Para dirigir al usuario a la página HTML correcta después
		// de que el controlador ha procesado la petición y preparado los datos.
		return "vacantes/detalle";
	}
}