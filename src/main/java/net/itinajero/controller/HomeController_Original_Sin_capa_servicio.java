package net.itinajero.controller;

import net.itinajero.model.Vacante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * VERSIÓN ORIGINAL DEL CONTROLADOR (ANTES DE USAR SERVICIOS). Esta clase representa el estado inicial, donde la lógica para obtener los datos estaba dentro de la misma clase
 * controladora (en el método getVacantes()).
 *
 * NOTA: La anotación @Controller está comentada para evitar conflictos con el HomeController principal. Si quieres probar esta versión, descomenta la anotación y comenta la
 * del otro HomeController.
 */

//@Controller
public class HomeController_Original_Sin_capa_servicio {

	private static final Logger log = LoggerFactory.getLogger(HomeController_Original_Sin_capa_servicio.class);

	@GetMapping("/")
	public String mostrarHome(Model modelo) {
		modelo.addAttribute("mensaje", "Bienvenidos a Empleos App (Versión Original)");
		modelo.addAttribute("fecha", new Date());
		return "home";
	}

	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {
		List<Vacante> listaDeVacantes = getVacantes();
		model.addAttribute("vacantes", listaDeVacantes);
		log.info("Enviando {} vacantes a la vista 'tabla' desde el método local.", listaDeVacantes.size());
		return "tabla";
	}

	/**
	 * Método auxiliar que genera una lista de objetos Vacante con datos de prueba. En esta versión, la responsabilidad de crear los datos recae directamente en el
	 * controlador. Esto acopla fuertemente al controlador con la forma en que se generan los datos.
	 *
	 * @return Una lista de objetos Vacante de prueba.
	 */
	private List<Vacante> getVacantes() {
		final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
		List<Vacante> listaVacantes = new LinkedList<>();
		try{
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero Civil");
			vacante1.setDescripcion("Solicitamos para el equipo de construcción de puente peatonal");
			vacante1.setFecha(DATE_FORMAT.parse("01-01-2025"));
			vacante1.setSalario(14000.0);
			vacante1.setDestacado(1);
			vacante1.setImagen("logo1.png");

			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Contador Público");
			vacante2.setDescripcion("Contador titulado con experiencia en contabilidades de costo");
			vacante2.setFecha(DATE_FORMAT.parse("01-02-2025"));
			vacante2.setSalario(12000.0);
			vacante2.setDestacado(0);
			vacante2.setImagen("logo2.png");

			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Ingeniero Eléctrico");
			vacante3.setDescripcion("Ingeniero eléctrico con experiencia en instalaciones industriales");
			vacante3.setFecha(DATE_FORMAT.parse("01-03-2025"));
			vacante3.setSalario(10500.0);
			vacante3.setDestacado(0);
			// A esta vacante no se le asigna imagen, por lo que usará la imagen por defecto definida en el modelo.

			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Diseñador Gráfico");
			vacante4.setDescripcion("Diseñador gráfico con experiencia en diseño digital y branding");
			vacante4.setFecha(DATE_FORMAT.parse("01-04-2025"));
			vacante4.setSalario(7900.0);
			vacante4.setDestacado(1);
			vacante4.setImagen("logo4.png");

			// Se añaden los objetos Vacante creados a la lista en memoria.
			listaVacantes.add(vacante1);
			listaVacantes.add(vacante2);
			listaVacantes.add(vacante3);
			listaVacantes.add(vacante4);

		} catch (ParseException e){
			// Si ocurre un error al parsear una fecha, se registra en el log para que el desarrollador lo vea.
			// Esto evita que la aplicación se caiga por un formato de fecha incorrecto.
			log.error("Error en el constructor de Impl_VacanteService al parsear una fecha: ", e);
		}
		return listaVacantes;
	}
}





