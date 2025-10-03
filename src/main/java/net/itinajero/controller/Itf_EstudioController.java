package net.itinajero.controller;

import org.springframework.ui.Model;

/**
 * ======================================================================================================== ARCHIVO DE ESTUDIO: LA INTERFAZ DE UN CONTROLADOR (EL "CONTRATO")
 * ========================================================================================================
 *
 * ¿QUÉ ES ESTO? Esta interfaz, `Itf_EstudioController`, actúa como un "contrato" o un "plano" para un controlador. Define QUÉ debe hacer un controlador de este tipo, pero no
 * se preocupa por el CÓMO lo hace. Es un concepto de Programación Orientada a Objetos para lograr el "desacoplamiento".
 *
 * ¿POR QUÉ CREAMOS ESTA INTERFAZ? (PROPÓSITO DEL ESTUDIO) El objetivo principal de este archivo es demostrar que el mismo patrón de diseño que usamos para la capa de
 * servicio (Itf_VacanteService -> VacanteService_Impl) también se puede aplicar a la capa de controladores. Permite ver cómo una clase (EstudioController_Impl) puede "firmar
 * un contrato" y estar obligada a implementar los métodos aquí definidos.
 *
 * ¿ES UNA PRÁCTICA COMÚN PARA LOS CONTROLADORES? No mucho. En la mayoría de los proyectos de Spring, los controladores son clases concretas que no implementan una interfaz.
 * Esto se debe a que el controlador suele ser el "punto final" de una petición dentro de tu código; ninguna otra de TUS clases depende de él, por lo que el beneficio del
 * desacoplamiento es menor en este punto. Sin embargo, entender que SE PUEDE hacer es fundamental para dominar los principios de diseño de software.
 */
public interface Itf_EstudioController {

	/**
	 * Firma del método para mostrar la página de inicio. Cualquier clase que implemente `Itf_EstudioController` DEBE tener estos métodos.
	 *
	 * @param modelo
	 * 		El modelo para pasar datos a la vista.
	 * @return El nombre de la vista (un String) que se debe renderizar.
	 */
	String mostrarHomeEstudio(Model modelo);

	/**
	 * Firma del método para mostrar una tabla de datos. Cualquier clase que implemente `Itf_EstudioController` DEBE tener este método.
	 *
	 * @param model
	 * 		El modelo para pasar datos a la vista.
	 * @return El nombre de la vista que se debe renderizar.
	 */
	String mostrarTablaEstudio(Model model);

}
