package net.itinajero.service;

import net.itinajero.model.Vacante;

import java.util.List;

/**
 * Esta es una interfaz de servicio. En la programación orientada a objetos y en arquitecturas como la de Spring,
 * una interfaz actúa como un "contrato" o un "plano".
 *
 * ¿Qué es un contrato?
 * Un contrato define QUÉ se debe hacer, pero no CÓMO se debe hacer. En este caso, la interfaz Itf_VacanteService
 * establece que cualquier clase que quiera ser considerada un "servicio de vacantes" DEBE proporcionar un conjunto
 * específico de funcionalidades. Por ahora, solo exige una funcionalidad: la capacidad de "buscar todas las vacantes".
 *
 * ¿Por qué es tan importante?
 * 1. DESACOPLAMIENTO: Componentes como HomeController no dependen de una clase concreta (como VacanteService_Impl),
 *    sino de esta interfaz (el contrato). Esto significa que podemos cambiar la implementación (el CÓMO) sin tener
 *    que modificar el controlador. Por ejemplo, podemos pasar de una lista en memoria a una base de datos real
 *    creando una nueva clase que implemente esta misma interfaz, y el controlador seguirá funcionando sin cambios.
 *
 * 2. POLIMORFISMO: Permite que Spring inyecte cualquier clase que cumpla este contrato en los componentes que lo necesiten.
 *    El controlador solo pide "alguien que sepa hacer lo que dice Itf_VacanteService", y Spring le proporciona la
 *    implementación que esté disponible y configurada (en nuestro caso, VacanteService_Impl).
 *
 * 3. CLARIDAD DE API: Define un API claro para la capa de negocio. Cualquiera que mire esta interfaz sabe qué
 *    operaciones están disponibles para las vacantes, sin necesidad de mirar los detalles de la implementación.
 */
public interface Itf_VacanteService {

    /**
     * Firma del método que forma parte del contrato del servicio de vacantes.
     * Toda clase que implemente esta interfaz está OBLIGADA a proporcionar una implementación para este método.
     *
     * Propósito: Recuperar y devolver una lista con todos los objetos de tipo Vacante disponibles en la fuente de datos
     * (sea una lista en memoria, una base de datos, un servicio web externo, etc.).
     *
     * @return una java.util.List<Vacante> que contiene todas las entidades de vacante. Si no hay ninguna, debe devolver
     *         una lista vacía, nunca null.
     */
    List<Vacante> buscarTodas();

    // A futuro, se podrían añadir más métodos a este contrato, por ejemplo:
    //
    // Vacante buscarPorId(int idVacante);
    // void guardar(Vacante vacante);
    // void eliminar(int idVacante);
    //
    // Y todas las clases que implementen la interfaz tendrían que añadir la lógica para estos nuevos métodos.
}
