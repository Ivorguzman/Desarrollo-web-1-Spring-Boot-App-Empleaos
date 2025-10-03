package net.itinajero.service;

import net.itinajero.model.Vacante;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Implementación concreta de la interfaz Itf_VacanteService. Esta clase se encarga de la lógica de negocio relacionada con la gestión de vacantes. En esta versión, actúa
 * como una base de datos en memoria.
 *
 * @Service: Esta es una de las anotaciones más importantes de Spring. Le indica al contenedor de Spring que: 1. Debe escanear esta clase y crear una instancia de ella (un
 * "bean"). 2. Por defecto, esta instancia será un "Singleton". Esto significa que Spring creará UN ÚNICO objeto de esta clase para toda la aplicación. Cada vez que otro
 * componente (como HomeController) pida una instancia de Itf_VacanteService, Spring le inyectará siempre este mismo objeto. 3. Marca esta clase como parte de la "capa de
 * servicio" de la aplicación, lo que es semánticamente importante para la arquitectura.
 */

@Service
public class VacanteService_Impl implements Itf_VacanteService {

	// --- SECCIÓN DE ATRIBUTOS ---

	/**
	 * Atributo que funciona como una base de datos en memoria. Es una lista de objetos Vacante. 'private': Solo es accesible dentro de esta clase. 'final': Asegura que la
	 * referencia a la lista (listaVacantes) se asigne una sola vez (en el constructor) y no pueda ser cambiada por otra lista diferente después. El contenido de la lista sí
	 * puede cambiar (añadir/quitar elementos).
	 */
	private final List<Vacante> listaVacantes;

	/**
	 * Logger para registrar eventos y errores en esta clase.
	 */
	private static final Logger log = LoggerFactory.getLogger(VacanteService_Impl.class);

	// --- SECCIÓN DE CONSTRUCTOR ---

	/**
	 * Constructor de la clase. Este bloque de código se ejecuta UNA SOLA VEZ cuando Spring crea la instancia Singleton de esta clase. Su propósito aquí es inicializar la
	 * "base de datos en memoria" con datos de prueba. En una aplicación real, este constructor podría estar vacío y los datos se cargarían desde una base de datos real a
	 * través de otros métodos.
	 */
	public VacanteService_Impl() {

		// Se define un formato de fecha estándar para convertir Strings a objetos Date.
		final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

		// Se inicializa la lista. Se usa LinkedList, que es eficiente para añadir elementos.
		listaVacantes = new LinkedList<>();

		// El bloque try-catch es fundamental para manejar errores de forma controlada.
		// DATE_FORMAT.parse() puede lanzar una excepción (ParseException) si el String de la fecha no tiene el formato correcto.
		try{
			// --- Inicio de la creación de datos de prueba (simulación de una base de datos) ---

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
			log.error("Error en el constructor de VacanteService_Impl al parsear una fecha: ", e);
		}

		// Se registra en el log que la inicialización de datos ha terminado.
		log.info("-> Constructor de VacanteService_Impl: Creación de lista de vacantes en memoria completada.");

	}

	// --- SECCIÓN DE MÉTODOS DE LA INTERFAZ ---

	/**
	 * Implementación del método buscarTodas() definido en la interfaz Itf_VacanteService. Este método cumple el contrato y devuelve la lista completa de vacantes.
	 *
	 * @return La lista de objetos Vacante que fue creada y almacenada en el constructor.
	 * @Override: Esta anotación le indica al compilador que este método está sobrescribiendo un método definido en una superclase o en una interfaz. Ayuda a prevenir
	 * errores.
	 */
	@Override
	public List<Vacante> buscarTodas() {
		// Simplemente devuelve la referencia a la lista que ya está en memoria.
		// Como la lista es un atributo de la clase, su estado se mantiene mientras la aplicación esté en ejecución.
		return listaVacantes;
	}

	@Override
	public List<Vacante> buscarPorId(Integer idVacante) {
		for (Vacante vacante : listaVacantes)

			if (Objects.equals(vacante.getId(), idVacante)){
				return List.of(vacante); // Devuelve una lista con la vacante encontrada
			}
		// Si no se encuentra la vacante, se devuelve una lista vacía.
		return List.of();
	}


}
