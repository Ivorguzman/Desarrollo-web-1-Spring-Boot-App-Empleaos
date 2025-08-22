/**
 * ======================================================================================= SECCIÓN 1: EL PAQUETE (LA DIRECCIÓN DEL ARCHIVO)
 * ======================================================================================= ¿Qué es un paquete? Un paquete es como una carpeta que organiza tus clases de Java.
 * Ayuda a evitar conflictos de nombres y a estructurar tu proyecto de manera lógica. ¿Cómo funciona? La palabra clave `package` al inicio del archivo indica a qué paquete
 * pertenece esta clase. ¿Por qué se usa? Para mantener el código ordenado, modular y fácil de mantener, especialmente en proyectos grandes.
 */

package net.itinajero.controller;

/*
 =======================================================================================
 SECCIÓN 2: LAS IMPORTACIONES (LA CAJA DE HERRAMIENTAS)
 =======================================================================================
 ¿Qué son las importaciones?
 Las importaciones son declaraciones que permiten a tu clase usar otras clases
 que están definidas en diferentes paquetes o librerías.
 ¿Cómo funcionan?
 La palabra clave `import` le dice al compilador dónde encontrar las clases que necesitas.
 Por ejemplo, `import net.itinajero.model.Vacante;` significa que queremos usar la clase `Vacante`
 que se encuentra en el paquete `net.itinajero.model`.
 ¿Por qué se usan?
 Para reutilizar código y funcionalidades ya existentes, sin tener que reescribirlas.
 Esto hace que tu código sea más conciso y eficiente.

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
 * ======================================================================================= SECCIÓN 3: LA DECLARACIÓN DE LA CLASE (EL EDIFICIO DE OFICINAS)
 * ======================================================================================= ¿Qué es esta clase? Esta clase es un 'Controlador' en el patrón de diseño
 * Model-View-Controller (MVC). Se encarga de recibir las peticiones de los usuarios (por ejemplo, cuando acceden a una URL), procesarlas y decidir qué vista (página HTML)
 * mostrar. ¿Cómo funciona? La anotación `@Controller` le dice a Spring que esta clase es un componente especial que maneja las solicitudes web. Spring es el framework que se
 * encarga de gestionar y dar vida a esta clase. ¿Por qué se implementa así? Es la forma estándar en Spring Boot para crear controladores web. Permite organizar la lógica de
 * la aplicación de manera clara y separada de la lógica de negocio y de la vista.
 *
 * @author Ivor
 */
@Controller
public class HomeController {

	/**
	 * Constructor por defecto de HomeController.
	 */
	public HomeController() {
		// Constructor por defecto
	}



	private static final Logger log = LoggerFactory.getLogger(HomeController.class);





	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

	/**
	 * ========  SECCIÓN 5: MÉTODOS DEL CONTROLADOR (LAS OFICINAS Y SUS FUNCIONES) ========= ¿Qué hace este método? Este método maneja las peticiones HTTP GET que llegan a la
	 * URL raíz de la aplicación ("/"). Su propósito es preparar los datos necesarios y mostrar la página de inicio (home). ¿Cómo lo logra? 1. `@GetMapping("/")`: Esta
	 * anotación de Spring le dice al framework que este método debe ejecutarse cuando un usuario accede a la dirección principal de la aplicación (ej.
	 * htt://localhost:8080/). 2. `Model modelo`: Spring inyecta automáticamente un objeto `Model` aquí. Este objeto actúa como un "bolso" donde puedes guardar datos que
	 * quieres enviar a la vista (la página HTML). 3. `modelo.addAttribute("clave", "valor")`: Se añaden varios atributos (pares clave-valor) al modelo. Estos atributos
	 * estarán disponibles en la plantilla HTML para ser mostrados. Por ejemplo, `"mensaje"` y `"nombre"` son cadenas de texto, y `"fecha"` es un objeto `Date`. 4.
	 * `log.info(...)`: Se registra un mensaje informativo en el log, indicando que la página de inicio está siendo mostrada. Esto es útil para el seguimiento y depuración.
	 * 5. `return "home"`: El método devuelve la cadena "home". Spring interpretará esto como el nombre de la plantilla HTML (por ejemplo, `home.html` en la carpeta
	 * `templates`) que debe renderizarse y enviarse al navegador del usuario. ¿Por qué se implementa así? - **Separación de Responsabilidades**: El controlador se encarga de
	 * la lógica de la petición y de preparar los datos, dejando la presentación a la vista (HTML). - **Facilidad de Uso**: Spring MVC simplifica el mapeo de URLs y el paso
	 * de datos a las vistas. - **Depuración**: El uso del logger permite ver el flujo de la aplicación sin afectar la salida al usuario.
	 *
	 * @param modelo
	 * 		El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista "home".
	 */
	@GetMapping("/")
	public String mostrarHome(Model modelo) {
		// Se añade el valor del atributo en forma manual
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
	 * ¿Qué hace este método? Este método maneja las peticiones HTTP GET a la URL " /tabla". Su función es obtener una lista de vacantes (ofertas de trabajo) y pasarlas a una
	 * vista para que se muestren en una tabla. También demuestra el uso de diferentes niveles de logging (info, error, warn). ¿Cómo lo logra? 1. `@GetMapping("/tabla")` :
	 * Mapea este método a la URL `/tabla`. Cuando un usuario navega `htt://localhost:8080/tabla`, este método se ejecuta. 2. `List<Vacante> lista = getVacantes();`: Llama al
	 * método auxiliar `getVacantes()` (definido más abajo en esta misma clase) para obtener una lista de objetos `Vacante`. En una aplicación real, esta lista provendría de
	 * una base de datos o un servicio externo. 3. `model.addAttribute("vacantes", lista);`: Añade la lista de vacantes al objeto `Model`. Esto hace que la lista esté
	 * disponible para la plantilla HTML bajo el nombre "vacantes". 4. `log.info(...)`, `log.error(...)`, `log.warn(...)`: Se utilizan diferentes niveles de log para mostrar
	 * mensajes en la consola. Esto es útil para: - `info`: Mensajes generales sobre el flujo normal de la aplicación. - `error`: Indicar que ha ocurrido un problema grave
	 * que podría afectar la funcionalidad. - `warn`: Señalar situaciones inesperadas o potenciales problemas que no son críticos pero que deberían ser revisados. 5. `return
	 * "tabla"`: Devuelve el nombre de la vista "tabla", lo que indica a Spring que debe renderizar la plantilla `tabla.html` (o similar) y enviarla al navegador. ¿Por qué se
	 * implementa así? - **Demostración de Datos Dinámicos**: Muestra cómo pasar una colección de objetos (vacantes) desde el controlador a la vista, lo cual es fundamental
	 * para aplicaciones que manejan listas de información. - **Ejemplo de Logging**: Ilustra la importancia de usar un sistema de logging robusto para el monitoreo y la
	 * depuración de la aplicación, en lugar de `System.out.println()`. - **Simulación de Datos**: Aunque `getVacantes()` es un método de prueba, simula cómo se obtendrían
	 * datos en un escenario real, preparando el terreno para futuras integraciones con bases de datos.
	 *
	 * @param model
	 * 		El objeto Model para pasar datos a la vista.
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
	 * ¿Qué hace este método? Este método maneja las peticiones HTTP GET a la URL " /listado". Su propósito es crear una lista simple de nombres de empleos (cadenas de texto)
	 * y pasarla a una vista para su visualización. ¿Cómo lo logra? 1. `@GetMapping("/listado")`: Mapea este método para que se ejecute cuando se acceda a la URL `/listado`.
	 * 2. `List<String> lista = List.of(...)`: Crea una lista inmutable de cadenas de texto. - `List.of()` es un método conveniente introducido en Java 9 para crear listas
	 * pequeñas y fijas de forma concisa. Al ser inmutable, no se pueden añadir ni quitar elementos después de su creación, lo cual es adecuado para datos estáticos como
	 * estos. 3. `modelo.addAttribute("empleo", lista);`: Añade esta lista de cadenas al objeto `Model`, haciéndola disponible en la vista bajo el nombre "empleo". 4. `return
	 * "listado"`: Indica a Spring que debe renderizar la plantilla `listado.html`. ¿Por qué se implementa así? - **Simplicidad**: Demuestra cómo pasar datos sencillos (una
	 * lista de Strings) a la vista. - **Inmutabilidad**: Utiliza `List.of()` para crear una lista inmutable, lo cual es una buena práctica cuando los datos no necesitan ser
	 * modificados, ya que mejora la seguridad y la claridad del código. - **Ejemplo Básico**: Sirve como un ejemplo fundamental de cómo un controlador puede preparar datos y
	 * enviarlos a una plantilla para su presentación.
	 *
	 * @param modelo
	 * 		El objeto Model para pasar datos a la vista.
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
	 * ¿Qué hace este método? Este método maneja las peticiones HTTP GET a la URL " /detalle". Su función es crear un objeto `Vacante` de ejemplo con datos predefinidos y
	 * pasarlo a una vista para mostrar los detalles de una única vacante. ¿Cómo lo logra? 1. `@GetMapping("/detalle")`: Mapea este método para que se active cuando se acceda
	 * a la URL `/detalle`. 2. `Vacante vacante = new Vacante();`: Se crea una nueva instancia de la clase `Vacante`. 3. `vacante.setNombre(...)`,
	 * `vacante.setDescripcion(...)`, etc.: Se establecen los valores de las propiedades de la vacante de forma manual. Estos son datos de ejemplo, no provienen de una base
	 * de datos ni de la entrada del usuario. 4. `modelo.addAttribute("vacante", vacante);`: El objeto `vacante` completo se añade al `Model`, haciéndolo disponible en la
	 * vista bajo el nombre "vacante". 5. `return "detalleSueldo"`: Indica a Spring que debe renderizar la plantilla `detalleSueldo.html`. ¿Por qué se implementa así? -
	 * **Demostración de Objeto Único**: Muestra cómo pasar un objeto complejo (no solo una lista o un String) desde el controlador a la vista, permitiendo que la vista
	 * acceda a sus propiedades. - **Prototipado Rápido**: Es útil para probar el diseño de la vista de detalles sin necesidad de tener una base de datos o un servicio de
	 * datos configurado. Permite ver cómo se renderizarían los datos de una vacante. - **Base para Funcionalidad Real**: Sirve como punto de partida para una funcionalidad
	 * real donde los datos de la vacante se cargarían dinámicamente (por ejemplo, por un ID en la URL).
	 *
	 * @param modelo
	 * 		El objeto Model para pasar datos a la vista.
	 * @return El nombre de la vista "detalleSueldo".
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
	 * ¿Qué hace este método? Este es un método auxiliar (privado) que genera una lista de objetos `Vacante` con datos de prueba. Simula lo que en una aplicación real sería
	 * ¿Cómo lo logra? 1. `List<Vacante> lista = new LinkedList<>();`: Crea una nueva lista vacía de tipo `Vacante` usando `LinkedList`. `LinkedList` es una buena opción
	 * cuando se van a realizar muchas inserciones o eliminaciones en el medio de la lista, aunque para este caso simple, `ArrayList` también sería adecuado. 2. `try-catch
	 * (ParseException e)`: Envuelve el código que parsea las fechas en un bloque `try-catch`. - `DATE_FORMAT.parse("dd-MM-yyyy")`: Intenta convertir una cadena de texto (ej.
	 * "01-01-2025") en un objeto `Date` utilizando el formato definido en `DATE_FORMAT`. - `ParseException`: Si la cadena de fecha no coincide con el formato esperado, se
	 * lanza esta excepción. El bloque `catch` la captura y registra un mensaje de error usando el logger, evitando que la aplicación se detenga abruptamente. 3. Creación de
	 * objetos `Vacante`: Se crean varias instancias de `Vacante` (`vacante1`, `vacante2`, etc.). Para cada vacante, se establecen sus propiedades (id, nombre, descripción,
	 * fecha, salario, destacado, imagen) con valores de ejemplo. 4. `lista.add(vacanteX)`: Cada objeto `Vacante` creado se añade a la lista. 5. `return lista`: Finalmente,
	 * el método devuelve la lista completa de vacantes de prueba. ¿Por qué se implementa así? - **Simulación de Datos**: En las primeras etapas de desarrollo, antes de
	 * integrar una base de datos, es común tener métodos que generen datos de prueba. Esto permite que el resto de la aplicación (controladores, vistas) funcione y pueda ser
	 * probada. - **Encapsulación**: Al ser un método `private`, solo puede ser llamado desde dentro de esta clase. Esto mantiene la lógica de generación de datos de prueba
	 * contenida y separada de la lógica principal del controlador. - **Manejo de Errores**: El bloque `try-catch` es un ejemplo fundamental de cómo manejar excepciones en
	 * Java, asegurando que la aplicación sea más robusta frente a errores inesperados (como un formato de fecha incorrecto). - **Preparación para el Futuro**: Este método
	 * será reemplazado más adelante por una capa de servicio que interactúe con una base de datos real, pero cumple su función didáctica y de prototipado inicial.
	 *
	 * @return Una lista de objetos Vacante de prueba.
	 */
	private List<Vacante> getVacantes() {
		List<Vacante> lista = new LinkedList<>();
		try{
			/* --- Creación de Objetos Vacante con datos completos --- */

			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero Civil");
			vacante1.setDescripcion("Solicitamos para el equipo de construcción de puente peatonal");
			vacante1.setFecha(DATE_FORMAT.parse("01-01-2025"));
			vacante1.setSalario(8500.0);
			vacante1.setDestacado(1); // Se asigna el valor destacado.
			vacante1.setImagen("logo1.png"); // Se asigna la imagen.

			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Contador Público");
			vacante2.setDescripcion("Contador titulado con experiencia en contabilidades de costo");
			vacante2.setFecha(DATE_FORMAT.parse("01-02-2025"));
			vacante2.setSalario(12000.0);
			vacante2.setDestacado(0); // Se asigna el valor destacado.
			vacante2.setImagen("logo2.png"); // Se asigna la imagen.

			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Ingeniero Eléctrico");
			vacante3.setDescripcion("Ingeniero eléctrico con experiencia en instalaciones industriales");
			vacante3.setFecha(DATE_FORMAT.parse("01-03-2025"));
			vacante3.setSalario(10500.0);
			vacante3.setDestacado(0); // Se asigna el valor destacado.
			/* No se asigna imagen, usará la de por defecto "no-image.png". */

			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Diseñador Gráfico");
			vacante4.setDescripcion("Diseñador gráfico con experiencia en diseño digital y branding");
			vacante4.setFecha(DATE_FORMAT.parse("01-04-2025"));
			vacante4.setSalario(7900.0);
			vacante4.setDestacado(1); //  Se asigna el valor destacado.
			vacante4.setImagen("logo4.png"); //  Se asigna la imagen.

			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);

		} catch (ParseException e){
			log.error("Error al parsear la fecha en getVacantes()", e);
		}
		/* System.out.println("Valor del Objeto lista = " + lista); // Poco Professional */
		log.info("Valor del Objeto lista =  {} ", lista);
		return lista;

	}
}
