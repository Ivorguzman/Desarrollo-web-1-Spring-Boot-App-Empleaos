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
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * ============ SECCIÓN 3: LA DECLARACIÓN DE LA CLASE (EL EDIFICIO DE OFICINAS) ============ ¿Qué es esta clase? Es el Controlador Principal de la aplicación. Actúa como el
 * recepcionista para las URLs más comunes, como la página de inicio.
 *
 * ¿Cómo funciona? - `@Controller`: Es una anotación que le dice a Spring: "Esta clase es un componente web, escanéala y úsala para manejar peticiones HTTP". A diferencia de
 * `@RestController`, esta clase está diseñada para devolver nombres de vistas (HTML) que serán renderizadas, no solo datos crudos (como JSON).
 *
 * ¿Por qué se implementa así? - **Punto de Entrada**: Sirve como el punto de entrada principal para los usuarios que visitan el sitio. - **Patrón MVC**: Sigue el patrón
 * Modelo-Vista-Controlador, separando la lógica de la presentación y los datos, lo que hace la aplicación más organizada y mantenible.
 */
@Controller
public class HomeController {

	/*
	 * ============ SECCIÓN 4: LOS ATRIBUTOS (LA MEMORIA Y HERRAMIENTAS DE LA CLASE) ============
	 */

	/**
	 * ¿Qué es? Un objeto para registrar mensajes de log (informativos, de error, etc.). Es el estándar profesional para el seguimiento de la aplicación. ¿Cómo funciona?
	 * `LoggerFactory.getLogger(...)` crea un logger asociado a esta clase. Usamos `log.info()`, `log.error()`, etc., para escribir mensajes. ¿Por qué se usa? Es mucho más
	 * potente y configurable que `System.out.println()`. Permite controlar qué se registra y dónde (consola, archivos) en diferentes entornos (desarrollo, producción).
	 */
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	/**
	 * ¿Qué es? Es una instancia de nuestra capa de servicio. Es la herramienta que usará el controlador para obtener o manipular los datos de las vacantes. Este es el
	 * corazón de la INYECCIÓN DE DEPENDENCIAS.
	 *
	 * ¿Cómo funciona? - `private Itf_VacanteService serviceVacantes;`: Declara una variable para contener el servicio, usando la interfaz como tipo, no la clase concreta. -
	 * `@Autowired`: Esta es una anotación clave de Spring. Le dice al framework: "Busca un bean (un objeto gestionado por ti) que sea del tipo `Itf_VacanteService` y
	 * asígnalo automáticamente a esta variable cuando crees el controlador". Spring encontrará la clase `Impl_VacanteService` (porque tiene `@Service`) y la "inyectará"
	 * aquí.
	 *
	 * ¿Por qué se usa? - **Desacoplamiento**: El controlador no sabe (ni le importa) cómo se crea el servicio. Solo sabe que existe y cómo usarlo a través de su interfaz.
	 * Esto significa que podríamos cambiar la implementación del servicio (por ejemplo, a una que use una base de datos real) sin tener que cambiar NI UNA LÍNEA de código en
	 * este controlador. - **Facilita las Pruebas**: Al hacer pruebas unitarias, podemos "inyectar" una versión falsa (un mock) del servicio para probar el controlador de
	 * forma aislada.
	 */
	@Autowired
	private Itf_VacanteService serviceVacantes;

	/*
	 * =======================================================================================
	 * SECCIÓN 5: MÉTODOS DEL CONTROLADOR (LAS OFICINAS Y SUS FUNCIONES)
	 * =======================================================================================
	 */

	/**
	 * ¿Qué hace este método? Maneja las peticiones GET a la URL raíz del sitio (`/`). Su propósito es preparar y mostrar la página de inicio.
	 *
	 * ¿Cómo lo logra? - `@GetMapping("/")`: Es una anotación que mapea este método exclusivamente a peticiones HTTP GET para la URL raíz. - `List<Vacante> listaDeVacantes =
	 * serviceVacantes.buscarTodas();`: Llama al servicio para obtener la lista completa de vacantes disponibles. - `if (!listaDeVacantes.isEmpty())`: Realiza una
	 * comprobación de seguridad para asegurarse de que la lista no esté vacía antes de intentar usarla. - `modelo.addAttribute("vacantes", listaDeVacantes)`: Añade la lista
	 * completa de vacantes al "modelo". El modelo es un objeto que actúa como un puente para pasar datos desde el controlador hasta la vista (el archivo HTML). - `return
	 * "home"`: Devuelve el nombre lógico de la vista. Spring buscará un archivo llamado `home.html` en la carpeta `templates/` y se lo enviará al navegador del usuario,
	 * dándole acceso a los datos que se añadieron al modelo.
	 *
	 * ¿Por qué se implementa así? - **Página de Inicio Dinámica**: En lugar de una página estática, la página de inicio muestra datos reales y actualizados (las vacantes)
	 * cada vez que se carga. - **Lógica en el Controlador**: La responsabilidad de obtener los datos y pasarlos a la vista recae en el controlador, manteniendo la vista
	 * (HTML) lo más simple posible.
	 */
	@GetMapping("/")
	public String mostrarHome(Model modelo) {
		List<Vacante> listaDeVacantes = serviceVacantes.buscarTodas();

		if (!listaDeVacantes.isEmpty()){
			modelo.addAttribute("vacantes", listaDeVacantes);
			log.info("Enviando {} vacantes a la vista 'home'.", listaDeVacantes.size());
		} else{
			log.warn("No hay vacantes en la lista para mostrar en la página de inicio.");
		}

		return "home";
	}

	/**
	 * ¿Qué hace este método? Maneja las peticiones GET a la URL `/tabla`. Su propósito es mostrar todas las vacantes en un formato de tabla.
	 *
	 * ¿Cómo lo logra? - `@GetMapping("/tabla")`: Mapea este método a la URL `/tabla`. - `List<Vacante> listaDeVacantes = serviceVacantes.buscarTodas();`: Al igual que en
	 * `mostrarHome`, obtiene la lista completa de vacantes del servicio. - `modelo.addAttribute("vacantes", listaDeVacantes)`: Pasa la lista completa al modelo para que la
	 * vista `tabla.html` pueda acceder a ella. - `return "tabla"`: Le dice a Spring que renderice la vista `tabla.html`, la cual se espera que contenga la lógica (por
	 * ejemplo, un bucle `th:each` de Thymeleaf) para iterar sobre la lista de vacantes y construir una tabla HTML.
	 *
	 * ¿Por qué se implementa así? - **Vista Especializada**: Proporciona una vista dedicada a mostrar los datos de una manera específica (una tabla), separada de la página
	 * de inicio. - **Reutilización de Lógica**: Nótese que la lógica para obtener los datos es la misma que en `mostrarHome`. El controlador actúa como un director que
	 * decide qué datos enviar y a qué vista.
	 */
	@GetMapping("/tabla")
	public String mostrarTabla(Model modelo) {
		List<Vacante> listaDeVacantes = serviceVacantes.buscarTodas();
		modelo.addAttribute("vacantes", listaDeVacantes);
		log.info("Enviando {} vacantes a la vista 'tabla'.", listaDeVacantes.size());
		return "tabla";
	}
}
