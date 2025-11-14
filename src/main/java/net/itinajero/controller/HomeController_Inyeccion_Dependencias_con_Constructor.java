/*
 * === SECCIÓN 1: EL PAQUETE (LA DIRECCIÓN DEL ARCHIVO) ===
 * ¿Qué es? Un paquete en Java es un contenedor que agrupa clases relacionadas.
 * ¿Cómo funciona? `package net.itinajero.controller;` ubica esta clase dentro del paquete `controller`.
 * ¿Por qué se usa? Para organizar el código y mantener una estructura de proyecto limpia.
 */
package net.itinajero.controller;

/*
 * === SECCIÓN 2: LAS IMPORTACIONES (LA CAJA DE HERRAMIENTAS) ===
 * ¿Qué son? Son directivas para usar clases de otros paquetes.
 * ¿Cómo funcionan? `import` le dice al compilador dónde encontrar las definiciones de las clases y anotaciones que necesitamos.
 * ¿Por qué se usan? Para reutilizar código y hacerlo más legible.
 */

import net.itinajero.model.Vacante;
import net.itinajero.service.Itf_VacanteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

/**
 * ============ SECCIÓN 3: LA DECLARACIÓN DE LA CLASE (UN CONTROLADOR MEJORADO) ============ ¿Qué es esta clase? Es una versión refactorizada del `HomeController` que
 * demuestra la forma recomendada de manejar las dependencias.
 *
 * ¿Cómo funciona? - `@Controller`: Le dice a Spring que esta clase es un controlador web. (Nota: Está comentada para no entrar en conflicto con el `HomeController` original.
 * Para activarla, se debe descomentar esta línea y comentar la anotación en la otra clase).
 *
 * ¿Por qué se implementa así? - Para ilustrar la "Inyección de Dependencias por Constructor", una práctica que hace el código más robusto, seguro y fácil de probar.
 */
// @Controller
public class HomeController_Inyeccion_Dependencias_con_Constructor {

	/*
	 * ============ SECCIÓN 4: ATRIBUTOS Y CONSTRUCTOR (INYECCIÓN DE DEPENDENCIAS POR CONSTRUCTOR) ============
	 */

	/**
	 * ¿Qué es? Es el atributo que contendrá la referencia al servicio de vacantes. Es la herramienta que necesita este controlador para funcionar. ¿Cómo funciona? - `private
	 * final Itf_VacanteService serviceVacantes;`: Se declara como `final`. Esto es CRUCIAL. Significa que su valor DEBE ser asignado en el constructor y no puede ser
	 * cambiado nunca más. Esto garantiza que el controlador siempre tendrá su dependencia y esta no puede ser nula o modificada accidentalmente.
	 */
	private final Itf_VacanteService serviceVacantes;

	private static final Logger log = LoggerFactory.getLogger(HomeController_Inyeccion_Dependencias_con_Constructor.class);

	/**
	 * ¿Qué es este método? Es el CONSTRUCTOR de la clase. Aquí se aplica la **Inyección de Dependencias por Constructor**.
	 *
	 * ¿Cómo funciona? - `@Autowired`: Se coloca en el constructor. Le dice a Spring: "Para poder construir un objeto de esta clase, es OBLIGATORIO que primero encuentres un
	 * bean de tipo `Itf_VacanteService`. Una vez que lo tengas, úsalo como argumento para llamar a este constructor". - `this.serviceVacantes = serviceVacantes;`: Dentro del
	 * constructor, se asigna el servicio proporcionado por Spring al atributo `final` de la clase. Esta asignación ocurre una sola vez en la vida del objeto.
	 *
	 * ¿Por qué se implementa así? (¡Esta es la MEJOR PRÁCTICA para inyección de dependencias!) - **Dependencias Explícitas y Obligatorias**: El "contrato" del constructor
	 * deja claro qué dependencias son OBLIGATORIAS. La clase no puede ser creada en un estado inválido (sin su servicio). - **Inmutabilidad**: Al usar `final`, las
	 * dependencias son inmutables. Esto hace la clase más segura, especialmente en aplicaciones multi-hilo como las aplicaciones web. - **Mayor Seguridad**: Evita errores de
	 * `NullPointerException`, ya que el objeto no puede existir si sus dependencias no fueron inyectadas en el momento de la creación. - **Facilidad de Prueba**: Es muy
	 * fácil crear instancias de esta clase en pruebas unitarias, simplemente llamando al constructor con una implementación "falsa" (mock) del servicio, sin necesidad de
	 * Spring.
	 */
	@Autowired
	public HomeController_Inyeccion_Dependencias_con_Constructor(Itf_VacanteService serviceVacantes) {
		this.serviceVacantes = serviceVacantes;
	}

	/*
	 * =======================================================================================
	 * SECCIÓN 5: MÉTODOS DEL CONTROLADOR (REQUEST HANDLERS)
	 * =======================================================================================
	 */

	/**
	 * ¿Qué hace este método? Maneja peticiones GET a la URL raíz (`/`) y muestra la página de inicio. ¿Cómo lo logra? Añade varios atributos de ejemplo al modelo y devuelve
	 * el nombre de la vista "home".
	 */
	@GetMapping("/")
	public String mostrarHome(Model modelo) {
		return "home"; // Simplificado para usar los datos del método mostrarTabla
	}

	/**
	 * ¿Qué hace este método? Maneja peticiones GET a `/tabla`. Obtiene la lista de vacantes y la pasa a la vista. ¿Cómo lo logra? Usa la dependencia `serviceVacantes` (que
	 * fue inyectada en el constructor) para llamar a `buscarTodas()` y añade la lista resultante al modelo.
	 */
	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> listaDeVacantes = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", listaDeVacantes);
		log.info("Enviando {} vacantes a la vista 'tabla'.", listaDeVacantes.size());
		return "tabla";
	}

	/**
	 * ¿Qué hace este método? Maneja peticiones GET a `/listado`. Es un ejemplo de cómo pasar una lista simple a una vista. ¿Cómo lo logra? Crea una lista de Strings estática
	 * (`List.of(...)`) y la añade al modelo.
	 */
	@GetMapping("/listado")
	public String mostrarListado(Model modelo) {
		List<String> lista = List.of("Ingeniero de Software", "Auxiliar Contable", "Vendedor", "Arquitecto de Software");
		modelo.addAttribute("empleos", lista);
		return "listado";
	}

	/**
	 * ¿Qué hace este método? Maneja peticiones GET a `/detalle`. Es un ejemplo de cómo pasar un único objeto complejo a una vista. ¿Cómo lo logra? Crea un objeto `Vacante`
	 * con datos "hard-coded" (escritos directamente en el código) y lo añade al modelo.
	 */
	@GetMapping("/detalle")
	public String mostrarDetalles(Model modelo) {
		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero de Comunicaciones");
		vacante.setDescripcion("Se solicita ingeniero para dar soporte a intranet.");
		vacante.setFecha(new Date());
		vacante.setSalario(9700.0);
		modelo.addAttribute("vacante", vacante);
		return "detalle";
	}
}
