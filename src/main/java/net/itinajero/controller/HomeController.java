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
 * Controlador Principal de la aplicación.
 */
@Controller
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	private final Itf_VacanteService serviceVacantes;

	/**
	 * Uso de Inyección por Constructor (Mejor práctica que @Autowired en campo).
	 * Spring inyectará automáticamente la implementación de Itf_VacanteService.
	 */
	@Autowired
	public HomeController(Itf_VacanteService serviceVacantes) {
		this.serviceVacantes = serviceVacantes;
	}

	/**
	 * Maneja la petición a la página de inicio.
	 */
	@GetMapping("/")
	public String mostrarHome(Model modelo) {
		List<Vacante> listaDeVacantes = serviceVacantes.buscarTodas();
		
		// Siempre añadimos la lista, aunque esté vacía, para que la vista no reciba un null.
		modelo.addAttribute("vacantes", listaDeVacantes);

		if (!listaDeVacantes.isEmpty()){
			log.info("Enviando {} vacantes a la vista 'home'.", listaDeVacantes.size());
		} else{
			log.warn("No hay vacantes en la lista para mostrar en la página de inicio.");
		}

		return "home";
	}

	/**
	 * Muestra todas las vacantes en una tabla.
	 */
	@GetMapping("/tabla")
	public String mostrarTabla(Model modelo) {
		List<Vacante> listaDeVacantes = serviceVacantes.buscarTodas();
		modelo.addAttribute("vacantes", listaDeVacantes);
		log.info("Enviando {} vacantes a la vista 'tabla'.", listaDeVacantes.size());
		return "tabla";
	}
}
