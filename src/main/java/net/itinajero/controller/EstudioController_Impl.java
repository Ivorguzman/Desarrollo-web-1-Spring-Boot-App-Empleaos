package net.itinajero.controller;

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
 * Nota:Esta será la clase "trabajadora" que implementa la interfaz que acabamos de crear. Contendrá la lógica real, la inyección de dependencias del servicio de vacantes y
 * las anotaciones de Spring.
 *
 * Implementación concreta de la interfaz Itf_EstudioController (Ejercicio de estudio). Esta clase "firma" el contrato definido en la interfaz y proporciona la lógica real
 * para cada uno de los métodos del contrato. NOTA: La anotación @Controller está comentada para evitar conflictos con el HomeController principal. Si quisieras probar esta
 * versión, deberías: 1. Comentar la anotación
 *
 * @Controller en la clase HomeController. 2. Descomentar la anotación @Controller de esta clase.
 */
@Controller
public class EstudioController_Impl implements Itf_EstudioController {

	private static final Logger log = LoggerFactory.getLogger(EstudioController_Impl.class);

	// Este controlador también necesita el servicio de vacantes para funcionar,
	// por lo que también usamos inyección de dependencias.
	private final Itf_VacanteService serviceVacantes;

	/**
	 * Se utiliza inyección por constructor, que es la mejor práctica. Spring inyectará aquí el bean del servicio de vacantes cuando cree este controlador.
	 *
	 * @param serviceVacantes
	 * 		El servicio de vacantes gestionado por Spring.
	 */
	@Autowired
	public EstudioController_Impl(Itf_VacanteService serviceVacantes) {
		this.serviceVacantes = serviceVacantes;
	}

	/**
	 * Implementación del método del contrato para mostrar la página de inicio.
	 *
	 * @Override: Confirma que estamos implementando un método de la interfaz.
	 * @GetMapping: Mapea este método a una URL específica. Usamos "/estudioHome" para no chocar con la URL "/" del otro controlador.
	 */
	@Override
	@GetMapping("/estudioHome")
	public String mostrarHomeEstudio(Model modelo) {
		log.info("Ejecutando mostrarHomeEstudio desde EstudioController_Impl");
		modelo.addAttribute("mensaje", "Bienvenido desde el Controlador de Estudio");
		// Reutilizamos la misma vista "home.html"
		return "home";
	}

	/**
	 * Implementación del método del contrato para mostrar la tabla de vacantes.
	 *
	 * @Override: Confirma que estamos implementando un método de la interfaz.
	 * @GetMapping: Mapea este método a la URL "/estudioTabla".
	 */
	@Override
	@GetMapping("/estudioTabla")
	public String mostrarTablaEstudio(Model model) {
		log.info("Ejecutando mostrarTablaEstudio desde EstudioController_Impl");
		List<Vacante> lista = serviceVacantes.buscarTodas();
		model.addAttribute("vacantes", lista);
		// Reutilizamos la misma vista "tabla.html"
		return "tabla";
	}
}
