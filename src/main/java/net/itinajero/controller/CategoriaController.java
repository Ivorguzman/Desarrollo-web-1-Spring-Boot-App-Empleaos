package net.itinajero.controller;// =======================================================================================
// SECCIÓN DE IMPORTACIONES (Añadimos las que nos faltan)
// =======================================================================================

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * === CLASE: CategoriaController === ANOTACIÓN A NIVEL DE CLASE: - @RequestMapping("/categorias"): ¡MEJOR PRÁCTICA! Establecemos una ruta base. Es como poner un letrero en
 * el ascensor: "PISO DE CATEGORÍAS". Todas las instrucciones internas partirán desde aquí.
 * =======================================================================================
 */
@Controller
@RequestMapping("/categorias")
public class CategoriaController {

	/**
	 * === MÉTODO: mostrarIndex === URL FINAL: /categorias/index ANOTACIÓN:== @RequestMapping(value == "/index", method = RequestMethod.GET): "Recepcionista, si alguien viene
	 * a la puerta '/index' para SOLICITAR información (GET), muéstrale la lista de categorías".
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String mostrarIndex(Model model) {
		// Aquí iría la lógica para buscar las categorías en la base de datos
		// y pasarlas al 'model' para que la vista las muestre.
		return "categorias/listCategorias";
	}

	/**
	 * === MÉTODO: crear === URL FINAL: /categorias/crear ANOTACIÓN: - @RequestMapping(value = "/crear", method = RequestMethod.GET): "Recepcionista, si alguien viene a la
	 * puerta '/crear' para SOLICITAR ver el formulario (GET), muéstrale el formulario para crear una nueva categoría".
	 */
	@RequestMapping(value = "/crear", method = RequestMethod.GET)
	public String crear() {
		return "categorias/formCategoria";
	}

	/**
	 * === MÉTODO: guardar === URL FINAL: /categorias/save (usamos 'save' por convención) ANOTACIÓN: - @RequestMapping(value = "/save", method = RequestMethod.POST):
	 * "Recepcionista, si alguien viene a la puerta '/save' para ENTREGAR información (POST), toma esos datos y guárdalos". ¡El método POST es crucial para enviar datos de
	 * forma segura!
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String guardar() {
		// Aquí iría la lógica para recibir los datos del formulario
		// y guardarlos en la base de datos.
		System.out.println("Guardando la categoría...");

		// Después de guardar, le decimos al navegador del usuario que vaya a otra página.
		// "redirect:" es una instrucción especial para no mostrar una vista, sino para
		// redirigir al usuario a la URL '/categorias/index'.
		return "redirect:/categorias/formCategoria";
	}
}
