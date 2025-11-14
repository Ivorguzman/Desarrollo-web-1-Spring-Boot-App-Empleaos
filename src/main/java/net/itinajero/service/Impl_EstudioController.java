/*
 * === SECCIÓN 1: EL PAQUETE (LA DIRECCIÓN DEL ARCHIVO) ===
 * ¿Qué es? Un paquete en Java es un contenedor que agrupa clases relacionadas.
 * ¿Cómo funciona? `package net.itinajero.service;` ubica esta clase en el paquete `service`.
 * ¿Por qué se usa? Para organizar el código. (Nota: Aunque esta clase es un Controlador, se ha colocado aquí como parte de un ejercicio de estudio. En un proyecto real, debería estar en el paquete `net.itinajero.controller`).
 */
package net.itinajero.service;

/*
 * === SECCIÓN 2: LAS IMPORTACIONES (LA CAJA DE HERRAMIENTAS) ===
 * ¿Qué son? Son directivas para usar clases de otros paquetes.
 * ¿Cómo funcionan? `import` le dice al compilador dónde encontrar las definiciones de las clases y anotaciones que necesitamos.
 * ¿Por qué se usan? Para reutilizar código y hacerlo más legible.
 */

import net.itinajero.model.Vacante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * ============ SECCIÓN 3: LA DECLARACIÓN DE LA CLASE (UN CONTROLADOR BASADO EN CONTRATO) ============ ¿Qué es esta clase? Es una implementación alternativa de un controlador
 * principal. Su propósito es demostrar un enfoque de diseño más robusto y desacoplado.
 *
 * ¿Cómo funciona? - `public class Impl_EstudioController implements Itf_EstudioController`: Esta clase "firma un contrato". Al implementar la interfaz
 * `Itf_EstudioController`, se compromete a proporcionar una implementación para todos los métodos definidos en esa interfaz. - `@Controller`: Al igual que otros
 * controladores, esta anotación le dice a Spring que la clase maneja peticiones web. (Nota: Como se indica en los comentarios originales, solo un controlador debe estar
 * activo para una misma ruta. Para usar este, habría que desactivar `HomeController`).
 *
 * ¿Por qué se implementa así? (¡Este es el punto clave del ejercicio!) - **Programación Orientada a Interfaces**: El código no depende de una clase concreta, sino de un
 * "contrato" (la interfaz). Esto permite cambiar la implementación (por ejemplo, crear `OtroController_Impl`) sin afectar al resto de la aplicación. - **Flexibilidad y
 * Pruebas**: Este enfoque hace que las pruebas unitarias sean mucho más sencillas, ya que se puede "simular" (mock) la interfaz fácilmente. - **Claridad de Intención**: La
 * interfaz `Itf_EstudioController` define claramente "qué" debe hacer el controlador, mientras que esta clase `_Impl` define "cómo" lo hace.
 */
// @Controller
public class Impl_EstudioController implements Itf_EstudioController {

	private static final Logger log = LoggerFactory.getLogger(Impl_EstudioController.class);

	/*
	 * ============ SECCIÓN 4: ATRIBUTOS Y CONSTRUCTOR (INYECCIÓN DE DEPENDENCIAS MEJORADA) ============
	 */

	/**
	 * ¿Qué es? Es el atributo que contendrá la referencia al servicio de vacantes. Es la herramienta que necesita este controlador para funcionar. ¿Cómo funciona? - `private
	 * final Itf_VacanteService serviceVacantes;`: Se declara como `final`. Esto significa que su valor DEBE ser asignado en el constructor y no puede ser cambiado nunca más.
	 * Esto garantiza que el controlador siempre tendrá su dependencia.
	 */
	private final Itf_VacanteService serviceVacantes;

	/**
	 * ¿Qué es este método? Es el CONSTRUCTOR de la clase. Y aquí se está aplicando la **Inyección de Dependencias por Constructor**.
	 *
	 * ¿Cómo funciona? - `@Autowired`: Se coloca en el constructor. Le dice a Spring: "Para poder construir un objeto de tipo `Impl_EstudioController`, primero necesitas
	 * encontrar un bean de tipo `Itf_VacanteService`. Una vez que lo tengas, úsalo como argumento para llamar a este constructor". - `this.serviceVacantes =
	 * serviceVacantes;`: Dentro del constructor, se asigna el servicio proporcionado por Spring al atributo `final` de la clase.
	 *
	 * ¿Por qué se implementa así? (¡Esta es la MEJOR PRÁCTICA para inyección de dependencias!) - **Dependencias Explícitas**: Deja muy claro qué dependencias son
	 * OBLIGATORIAS para que la clase funcione. La clase no se puede crear sin ellas. - **Inmutabilidad**: Al usar `final`, nos aseguramos de que las dependencias no puedan
	 * ser cambiadas accidentalmente después de la creación del objeto. - **Mayor Seguridad**: Evita errores de `NullPointerException`, ya que el objeto no puede existir si
	 * sus dependencias no fueron inyectadas. - **Facilidad de Prueba**: Es muy fácil crear instancias de esta clase en pruebas unitarias, simplemente llamando al constructor
	 * con una implementación "falsa" (mock) del servicio.
	 */
	@Autowired
	public Impl_EstudioController(Itf_VacanteService serviceVacantes) {
		this.serviceVacantes = serviceVacantes;
	}

	/*
	 * =======================================================================================
	 * SECCIÓN 5: MÉTODOS DEL CONTROLADOR (CUMPLIENDO EL CONTRATO)
	 * =======================================================================================
	 */

	/**
	 * ¿Qué hace este método? Es la implementación del método `mostrarHomeEstudio` definido en la interfaz `Itf_EstudioController`.
	 *
	 * ¿Cómo lo logra? - `@Override`: Es una anotación que confirma al compilador que estamos sobrescribiendo correctamente un método de la interfaz que implementamos. -
	 * `@GetMapping("/estudioHome")`: Mapea este método a la URL `/estudioHome` para evitar conflictos con el `/` del `HomeController` principal. -
	 * `modelo.addAttribute(...)`: Pasa un mensaje a la vista para confirmar que este controlador es el que está respondiendo. - `return "home"`: Reutiliza la misma vista
	 * `home.html` que usa el otro controlador.
	 */
	@Override
	@GetMapping("/estudioHome")
	public String mostrarHomeEstudio(Model modelo) {
		log.info("Ejecutando 'mostrarHomeEstudio' desde la implementación Impl_EstudioController");
		modelo.addAttribute("mensaje", "Bienvenido desde el Controlador de Estudio (Implementación)");
		return "home";
	}

	/**
	 * ¿Qué hace este método? Es la implementación del método `mostrarTablaEstudio` definido en la interfaz `Itf_EstudioController`.
	 *
	 * ¿Cómo lo logra? - `@Override`: Confirma que estamos implementando el método del contrato. - `@GetMapping("/estudioTabla")`: Mapea este método a la URL `/estudioTabla`.
	 * - Llama al servicio (`serviceVacantes.buscarTodas()`) para obtener los datos, los añade al modelo y devuelve el nombre de la vista `tabla.html` para ser renderizada.
	 */
	@Override
	@GetMapping("/estudioTabla")
	public String mostrarTablaEstudio(Model model) {
		log.info("Ejecutando 'mostrarTablaEstudio' desde la implementación Impl_EstudioController");
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		return "tabla";
	}
}
