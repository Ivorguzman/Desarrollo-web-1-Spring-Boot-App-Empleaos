/*
 * === SECCIÓN 1: EL PAQUETE (LA DIRECCIÓN DEL ARCHIVO)===
 * ¿Qué es un paquete? Un paquete es como una carpeta que organiza tus clases de Java.
 * Ayuda a evitar conflictos de nombres y a estructurar tu proyecto de manera lógica. ¿Cómo funciona? La palabra clave `package` al inicio del archivo indica a qué paquete
 * pertenece esta clase. ¿Por qué se usa? Para mantener el código ordenado, modular y fácil de mantener, especialmente en proyectos grandes.
 */

package net.itinajero.controller;

/*
 * === SECCIÓN 2: LAS IMPORTACIONES (LA CAJA DE HERRAMIENTAS) ===
 *  ¿Qué son las importaciones? Las importaciones son declaraciones que permiten a tu
 * clase usar otras clases que están definidas en diferentes paquetes o librerías. ¿Cómo funcionan? La palabra clave `import` le dice al compilador dónde encontrar las clases
 * que necesitas. ¿Por qué se usan? Para reutilizar código y funcionalidades ya existentes, sin tener que reescribirlas. Esto hace que tu código sea más conciso y eficiente.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * ============ SECCIÓN 3: LA DECLARACIÓN DE LA CLASE (EL EDIFICIO DE OFICINAS) ============ ¿Qué es esta clase?. Esta clase es un 'Controlador' en el patrón de diseño
 * Model-View-Controller (MVC). Se encarga de recibir las peticiones de los usuarios relacionadas con las categorías, procesarlas y decidir qué vista (página HTML) mostrar o
 * qué acción realizar.
 * <p>
 * ¿Cómo funciona? La anotación `@Controller` le dice a Spring que esta clase es un componente especial que maneja las solicitudes web. Spring es el framework que se encarga
 * de escanear, detectar y dar vida a esta clase durante el arranque de la aplicación.
 * <p>
 * ¿Por qué se implementa así? Es la forma estándar en Spring Boot para crear controladores web. Permite organizar la lógica de la aplicación de manera clara, separando la
 * gestión de peticiones de la lógica de negocio y de la capa de presentación (la vista).
 */
@Controller
/*  ¿Qué hace esta anotación?
 * `@RequestMapping("/categorias")` establece una "ruta base" para todas las URLs manejadas
 * por los métodos dentro de esta clase. Es como un prefijo para todas las URLs.
 ¿Cómo lo logra?
 * Cualquier método dentro de esta clase que tenga su propio `@RequestMapping`, verá su ruta
 * combinada con esta ruta base. Por ejemplo, si un método tiene `@RequestMapping("/lista")`,
 * la URL completa para acceder a ese método será `/categorias/lista`.
 ¿Por qué se usa?
 * Ayuda a organizar las URLs de forma lógica y evita la repetición del prefijo `/categorias`
 * en cada método. Esto mejora la legibilidad y el mantenimiento del código.
 */
@RequestMapping("/categorias")
public class CategoriaController {

	private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);


	/**
	 * ======================================================================================= SECCIÓN 4: MÉTODOS DEL CONTROLADOR (LAS OFICINAS Y SUS FUNCIONES)
	 * ======================================================================================= ¿Qué hace este método? Este método maneja las peticiones HTTP GET que llegan a
	 * la URL `/categorias/index`. Su propósito es mostrar una lista de categorías. ¿Cómo lo logra? `@RequestMapping(value = "/index", method = RequestMethod.GET)`: Esta
	 * anotación de Spring le dice al framework que este método debe ejecutarse cuando un usuario accede a la dirección `/categorias/index` utilizando el método HTTP GET
	 * (típicamente al escribir la URL en el navegador o hacer clic en un enlace). `public String mostrarIndex(Model model)`: Define el método. Aunque aquí se recibe un
	 * objeto `Model`, en este ejemplo no se usa para pasar datos a la vista, pero es una buena práctica incluirlo si se planea usarlo en el futuro. `return
	 * "categorias/listCategorias"`: El método devuelve la cadena `"categorias/listCategorias"`. Spring interpretará esto como el nombre lógico de la vista. Buscará una
	 * plantilla HTML (por ejemplo, `listCategorias.html`) dentro de la carpeta `templates/categorias/` y la renderizará para el usuario. ¿Por qué se implementa así? -
	 * **Mapeo de URL**: Es la forma estándar de mapear una URL específica a un método controlador en Spring MVC. - **Separación de Responsabilidades**: El controlador se
	 * encarga de la lógica de la petición y de seleccionar la vista adecuada, dejando la presentación a la vista (HTML). - **Preparación para Datos Dinámicos**: Aunque no se
	 * usa aquí, el `Model` está listo para cuando se necesite pasar una lista real de categorías desde una base de datos a la vista. Aquí iría la lógica para buscar las
	 * categorías en la base de datos y pasarlas al 'model' para que la vista las muestre.
	 *
	 * @param model
	 * 		El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista "categorias/listCategorias".
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String mostrarIndex(Model model) {

		return "categorias/listCategorias";
	}

	/**
	 * ¿Qué hace este método? Este método maneja las peticiones HTTP GET que llegan a la URL `/categorias/crear`. Su propósito es mostrar el formulario para crear una nueva
	 * categoría. ¿Cómo lo logra? `@RequestMapping(value = "/crear", method = RequestMethod.GET)`: Mapea este método para que se ejecute cuando un usuario accede a
	 * `/categorias/crear` con una petición GET. `public String crear()`: Define el método que no recibe parámetros adicionales del modelo o la URL. `return
	 * "categorias/formCategoria"`: Devuelve el nombre lógico de la vista `"categorias/formCategoria"`. Spring buscará y renderizará la plantilla HTML `formCategoria.html`
	 * dentro de la carpeta `templates/categorias/`. ¿Por qué se implementa así? - **Mostrar Formularios**: Es el patrón común para presentar un formulario vacío al usuario
	 * para la entrada de datos. La petición GET es adecuada para esto, ya que solo se está solicitando una página. - **Claridad de Flujo**: Separa claramente la acción de
	 * mostrar el formulario de la acción de procesar los datos del formulario (que típicamente sería un POST).
	 *
	 * @return El nombre de la vista "categorias/formCategoria".
	 */
	@RequestMapping(value = "/crear", method = RequestMethod.GET)
	public String crear() {
		return "categorias/formCategoria";
	}

	/**
	 * ¿Qué hace este método? Este método maneja las peticiones HTTP POST que llegan a la URL `/categorias/save`. Su propósito es recibir los datos enviados desde un
	 * formulario (por ejemplo, los datos de una nueva categoría) y procesarlos (en este caso, simular que los guarda). ¿Cómo lo logra? `@RequestMapping(value = "/save",
	 * method = RequestMethod.POST)`: Mapea este método para que se ejecute cuando se envía un formulario a `/categorias/save` utilizando el método HTTP POST. El método POST
	 * es crucial para enviar datos que modifican el estado del servidor (como guardar una nueva categoría). `public String guardar()`: Define el método. En una aplicación
	 * real, este método recibiría los datos del formulario como parámetros (por ejemplo, un objeto `Categoria`). `System.out.println("Guardando la categoría...")`: Una línea
	 * de depuración que imprime un mensaje en la consola del servidor, indicando que la acción de guardar se está ejecutando. `return "redirect:/categorias/formCategoria"`:
	 * Esta es una instrucción especial. En lugar de renderizar una vista directamente, `"redirect:"` le dice al navegador del usuario que realice una nueva petición GET a la
	 * URL especificada (`/categorias/formCategoria`). Esto es conocido como el patrón Post/Redirect/Get (PRG). ¿Por qué se implementa así? - **Procesamiento de
	 * Formularios**: Es el patrón estándar para manejar el envío de datos de formularios en aplicaciones web. El método POST es seguro para esto. - **Patrón
	 * Post/Redirect/Get (PRG)**: La redirección después de un POST es una buena práctica para evitar problemas como el reenvío accidental del formulario si el usuario
	 * refresca la página, y para asegurar que la URL en el navegador refleje el estado actual de la aplicación (una petición GET). - **Simulación de Lógica de Negocio**:
	 * Aunque aquí solo se imprime un mensaje, este es el lugar donde se integraría la lógica real para guardar la categoría en una base de datos o llamar a un servicio.
	 *
	 * @return La redirección a la URL especificada después de guardar.
	 */
	//@PostMapping("/save") // Opción moderna  y resumida de @RequestMapping(value =
	// "/save", method = RequestMethod.POST) -- RECOMENDADA --
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar(@RequestParam("var_nombre") String var_nombre, @RequestParam("var_descripcion") String var_descripcion) {

		log.info("Mostrando detalles para la vacante método  verDetalles guardar(@RequestParam(\"var_nombre\") "
				+ "String var_nombre, @RequestParam(\"var_descripcion\") String var_descripcion) con pase de parameter  var_nombre: "
				+ "[http://localhost:9098/categorias/save/{}]", var_nombre);

		log.info("Mostrando detalles para la vacante método  verDetalles guardar(@RequestParam(\"var_nombre\") "
				+ "String var_nombre, @RequestParam(\"var_descripcion\") String var_descripcion) con pase de parameter var_descripcion:  "
				+ "[http://localhost:9098/categorias/save/{}]", var_descripcion);

		System.out.println("Guardando la categoría...");

		/*
		 Después de guardar, le decimos al navegador del usuario que vaya a otra página.
		 "redirect:" es una instrucción especial para no mostrar una vista, sino para
		 redirigir al usuario a la URL '/categorias/formCategoria'.
		 return "redirect:/categorias/formCategoria";
		 */
		return "categorias/formCategoria";
	}


}















