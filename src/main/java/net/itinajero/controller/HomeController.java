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
 * VERSIÓN INTERMEDIA DEL CONTROLADOR (INYECCIÓN POR CAMPO). Esta clase representa el primer paso de la refactorización. La lógica para obtener datos se ha movido a una capa
 * de servicio (Itf_VacanteService) y se inyecta en este controlador. En esta versión se usa la "Inyección por Campo", que es funcional pero menos recomendada que la
 * inyección por constructor. NOTA: La anotación @Controller está comentada para evitar conflictos con el HomeController principal. Si quieres probar esta versión, descomenta
 * la anotación y comenta la del otro HomeController.
 */
@Controller
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Inyección de Dependencias por Campo (Por Inyección de Campo).
	 *
	 * @Autowired: Esta anotación le dice a Spring: "Busca un bean que sea compatible con el tipo Itf_VacanteService y asígnalo directamente a esta variable". Es una forma
	 * muy concisa, pero tiene desventajas: dificulta las pruebas unitarias y permite que el objeto se construya sin sus dependencias, lo que puede llevar a
	 * NullPointerExceptions si Spring falla.
	 */
	@Autowired
	private Itf_VacanteService serviceVacantes;

	//	@GetMapping("/")
	//	public String mostrarHome(Model modelo) {
	//		modelo.addAttribute("mensaje", "Bienvenidos a Empleos App (Versión con Servicio)");
	//		modelo.addAttribute("fecha", new Date());
	//		return "home";
	//	}

	@SuppressWarnings("SequencedCollectionMethodCanBeUsed")
	@GetMapping("/")
	public String mostrarHome(Model modelo) {
		// 1. Se obtiene la lista completa de vacantes desde el servicio.
		List<Vacante> listaDeVacantes = serviceVacantes.buscarTodas();
		log.info("Enviando {} vacantes a la vista 'home' desde el servicio.", listaDeVacantes.size());
		log.info("Enviando {} contenido del objeto vacante", listaDeVacantes);

		modelo.addAttribute("mensaje", "Bienvenidos a Empleos App");

		// 2. ¡LA SOLUCIÓN! Se comprueba si la lista no está vacía.
		if (!listaDeVacantes.isEmpty()){
			// 3. Si no está vacía, se añade al modelo SOLO EL PRIMER objeto de la lista (en la posición 0).
			// Ahora, la variable "vacante" en la vista "home.html" será un único objeto Vacante,
			// por lo que la expresión "vacante.nombre" funcionará correctamente.
			modelo.addAttribute("vacante", listaDeVacantes.get(3));
			log.info("Enviando la primera vacante a la vista 'home'.");
		} else{
			log.warn("No hay vacantes en la lista para mostrar en la página de inicio.");
		}

		return "home";
	}

	/**
	 * Este método ahora delega la responsabilidad de obtener los datos al servicio inyectado. El controlador ya no sabe de dónde vienen las vacantes, solo las pide.
	 */
	@GetMapping("/tabla")
	public String mostrarTabla(Model modelo) {
		// Se llama al método del servicio para obtener la lista de vacantes.
		List<Vacante> listaDeVacantes = serviceVacantes.buscarTodas();
		modelo.addAttribute("vacantes", listaDeVacantes);
		log.info("Enviando {} vacantes a la vista 'tabla' desde el servicio.", listaDeVacantes.size());
		log.info("Enviando {} contenido del objeto vacante de la vista tabla", listaDeVacantes);
		return "tabla";
	}

	// El método privado getVacantes() ha sido eliminado. ¡La responsabilidad ahora es del servicio!



}
