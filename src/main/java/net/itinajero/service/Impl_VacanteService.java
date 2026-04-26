/*
 * === SECCIÓN 1: EL PAQUETE (LA DIRECCIÓN DEL ARCHIVO) ===
 * ¿Qué es? Un paquete en Java es un contenedor que agrupa clases relacionadas. Funciona como una carpeta en un sistema de archivos.
 * ¿Cómo funciona? La declaración `package net.itinajero.service;` indica que esta clase, `Impl_VacanteService`, pertenece al paquete `service`.
 * ¿Por qué se usa? Para organizar el código de manera lógica (por ejemplo, todos los servicios juntos), evitar conflictos de nombres y controlar el acceso a las clases.
 */
package net.itinajero.service;

/*
 * === SECCIÓN 2: LAS IMPORTACIONES (LA CAJA DE HERRAMIENTAS) ===
 * ¿Qué son? Son directivas que le permiten a esta clase utilizar otras clases que no están en el mismo paquete.
 * ¿Cómo funcionan? La palabra clave `import` le dice al compilador de Java dónde encontrar las definiciones de las clases que se usan en el código, como `Vacante`, `List`, `Service`, etc.
 * ¿Por qué se usan? Para reutilizar código. En lugar de reescribir qué es una `List` o un `Logger`, simplemente los importamos y usamos su funcionalidad ya definida.
 */

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
 * ============ SECCIÓN 3: LA DECLARACIÓN DE LA CLASE (EL MOTOR DE LA APLICACIÓN) ============ ¿Qué es esta
 * clase? Es una clase de "Servicio". Su responsabilidad es manejar la lógica de negocio relacionada con las
 * vacantes. En esta implementación específica, simula ser una base de datos guardando los datos en memoria.
 * <p>
 * ¿Cómo funciona? - `public class Impl_VacanteService implements Itf_VacanteService`: Declara una clase pública
 * que "implementa" o "cumple el contrato" definido por la interfaz `Itf_VacanteService`. Esto significa que
 * está obligada a proporcionar una implementación para todos los métodos declarados en esa interfaz. -
 * `@Service`: Esta es una anotación clave de Spring. Le dice al framework: "Oye, quiero que gestiones esta
 * clase por mí". Spring entonces: 1. Crea una única instancia (objeto) de esta clase cuando la aplicación
 * arranca. Esto se conoce como un "bean" con ámbito "singleton". 2. Cuando otra clase (como un controlador)
 * pida una `Itf_VacanteService`, Spring le "inyectará" (le pasará) esta única instancia.
 * <p>
 * ¿Por qué se implementa así? - **Separación de Responsabilidades**: Separa la lógica de negocio (cómo se
 * gestionan las vacantes) de la capa de presentación (el controlador). - **Inversión de Control (IoC)**: No
 * creamos nosotros el objeto `new Impl_VacanteService()`, sino que dejamos que el framework de Spring lo haga y
 * nos lo proporcione. - **Programación orientada a interfaces**: Al depender de la interfaz
 * `Itf_VacanteService` y no de la clase concreta, podríamos cambiar fácilmente esta implementación por una que
 * use una base de datos real sin tener que modificar los controladores.
 */
@Service
public class Impl_VacanteService implements Itf_VacanteService {

/*
 * ============ SECCIÓN 4: LOS ATRIBUTOS (LA MEMORIA INTERNA DE LA CLASE) ============
 */

/**
 * ¿Qué es? Es un atributo que almacenará la lista de todas las vacantes. Actúa como nuestra base de datos en
 * memoria. ¿Cómo funciona? - `private`: Solo se puede acceder a esta lista desde dentro de esta misma clase.
 * Protege los datos. - `List<Vacante>`: Declara que es una lista que solo puede contener objetos de tipo
 * `Vacante`. - `listaVacantes`: Es el nombre que le damos a la variable. ¿Por qué se declara aquí? Al ser un
 * atributo de la instancia, la lista persistirá y mantendrá su estado mientras la aplicación esté en ejecución.
 * Cada vez que un método en esta clase la modifique o la lea, estará trabajando sobre la misma lista.
 */
private List<Vacante> listaVacantes = null;

/**
 * ¿Qué es? Un objeto para registrar mensajes de log (informativos, de error, etc.). ¿Cómo funciona? - `private
 * static final Logger log`: Se crea una única instancia de Logger para toda la clase. -
 * `LoggerFactory.getLogger(...)`: Es la forma estándar de obtener un logger para la clase actual. ¿Por qué se
 * usa? Es la forma profesional de registrar lo que pasa en la aplicación. Es mucho más potente y configurable
 * que usar `System.out.println()`.
 */
private static final Logger log = LoggerFactory.getLogger(Impl_VacanteService.class);

/**
 * ============ SECCIÓN 5: EL CONSTRUCTOR (EL MOMENTO DE LA CREACIÓN) ============ ¿Qué es? El constructor es un
 * método especial que se ejecuta UNA SOLA VEZ, justo cuando Spring crea la instancia de esta clase. ¿Cómo
 * funciona? No lleva tipo de retorno y se llama igual que la clase. El código que está aquí dentro inicializa
 * el estado del objeto. ¿Por qué se usa aquí? Es el lugar perfecto para preparar los datos iniciales. En este
 * caso, lo usamos para crear las vacantes de ejemplo y llenar nuestra "base de datos en memoria". En una
 * aplicación real, este constructor podría estar vacío o usarse para configurar la conexión a una base de datos
 * real.
 */
public Impl_VacanteService() {

	// ¿Qué es? Un objeto que nos ayudará a convertir texto (String) en fechas (Date).
	// ¿Cómo funciona? Se le da un patrón ("dd-MM-yyyy") y sabrá cómo interpretar un texto como "01-02-2025".
	// ¿Por qué se usa? Las fechas en Java son objetos, no simple texto. Necesitamos una forma de "parsear" o interpretar el texto para crear estos objetos.
	final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

	// ¿Qué es? Es la inicialización de nuestra lista.
	// ¿Cómo funciona? `new LinkedList<>()` crea un objeto de tipo lista enlazada, que es una implementación concreta de la interfaz `List`.
	// ¿Por qué se usa? Aquí se "da vida" a la variable `listaVacantes` que declaramos antes. A partir de este punto, ya podemos agregarle elementos.
	listaVacantes = new LinkedList<>();

	// ¿Qué es? Un bloque de control de errores.
	// ¿Cómo funciona? El código que puede fallar (en este caso, `DATE_FORMAT.parse()`) se pone en el bloque `try`. Si ocurre un error (una `ParseException`), la ejecución salta al bloque `catch` en lugar de detener la aplicación.
	// ¿Por qué se usa? Para hacer la aplicación robusta. Si una de las fechas estuviera mal escrita, la aplicación no se "caería", simplemente registraría el error y continuaría.
	try{
		// --- Inicio de la creación de datos de prueba (simulación de una base de datos) ---

		// Paso 1: Crear un objeto Vacante vacío.
		Vacante vacante1 = new Vacante();
		// Paso 2: Asignar valores a sus atributos usando sus métodos "set".
		vacante1.setId(1);
		vacante1.setNombre("Ingeniero Civil");
		vacante1.setDescripcion("Solicitamos para el equipo de construcción de puente peatonal");
		// Aquí se usa el formateador para crear el objeto Date.
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

		// ¿Qué es? Agregar los objetos ya creados a nuestra lista en memoria.
		// ¿Cómo funciona? El método `add()` de la lista se encarga de añadir el elemento al final.
		// ¿Por qué se usa? Para poblar nuestra "base de datos".
		listaVacantes.add(vacante1);
		listaVacantes.add(vacante2);
		listaVacantes.add(vacante3);
		listaVacantes.add(vacante4);

	} catch (ParseException e){
		// ¿Qué es? El bloque que se ejecuta si algo sale mal en el `try`.
		// ¿Cómo funciona? Recibe el objeto de la excepción (`e`) que contiene los detalles del error.
		// ¿Por qué se usa? Aquí registramos el error para que el desarrollador pueda investigarlo, en lugar de dejar que la aplicación falle silenciosamente o se detenga.
		log.error("Error en el constructor de Impl_VacanteService al parsear una fecha: ", e);
	}

	// Se registra en el log que la inicialización de datos ha terminado.
	log.info("-> Constructor de Impl_VacanteService: Creación de lista de vacantes en memoria completada.");
}

/*
 * ============ SECCIÓN 6: LA IMPLEMENTACIÓN DE LOS MÉTODOS (LAS TAREAS DEL SERVICIO) ============
 */

/**
 * ¿Qué hace este método? Cumple con el contrato de la interfaz `Itf_VacanteService`. Su única tarea es devolver
 * la lista completa de vacantes que tiene almacenada. ¿Cómo lo logra? - `@Override`: Es una anotación que le
 * indica al compilador (y a otros programadores) que este método está sobrescribiendo un método de su interfaz
 * o clase padre. Ayuda a evitar errores. - `public List<Vacante> buscarTodas()`: La firma del método es
 * idéntica a la de la interfaz. - `return listaVacantes;`: Devuelve la referencia al atributo de la clase que
 * contiene todas las vacantes. ¿Por qué se implementa así? Es la forma más directa de cumplir con la solicitud.
 * Como el servicio es un singleton y la lista es un atributo, cualquier controlador que llame a este método
 * recibirá siempre la misma lista actualizada.
 */
@Override
public List<Vacante> buscarTodas() {
	return listaVacantes;
}

/**
 * ¿Qué hace este método? Busca una única vacante dentro de la lista basándose en su `id`. ¿Cómo lo logra? -
 * Recorre cada objeto `Vacante` en la `listaVacantes` usando un bucle "for-each". - Para cada vacante, compara
 * su ID (`vacante.getId()`) con el ID que se pasó como parámetro (`idVacante`). - Se usa `Objects.equals()`
 * para la comparación. Esto es más seguro que `==` porque evita errores si alguno de los valores fuera `null`.
 * - Si encuentra una coincidencia, devuelve inmediatamente una lista que contiene únicamente esa vacante
 * (`List.of(vacante)`). - Si el bucle termina y no se encontró ninguna coincidencia, devuelve una lista vacía
 * (`List.of()`). ¿Por qué se implementa así? Es un algoritmo de búsqueda lineal simple y efectivo para una
 * cantidad pequeña de datos en memoria. Devolver siempre una lista (aunque esté vacía) puede simplificar el
 * código que llama a este método, ya que no tiene que preocuparse por manejar un valor `null`.
 */
@Override
public List<Vacante> buscarPorId(Integer idVacante) {
	for (Vacante vacante : listaVacantes){
		if (Objects.equals(vacante.getId(), idVacante)){
			return List.of(vacante); // Devuelve una lista inmutable con la vacante encontrada
		}
	}
	// Si no se encuentra la vacante, se devuelve una lista inmutable vacía.
	return List.of();
}

@Override
public void guardar(Vacante vacante) {
	listaVacantes.add(vacante);
	log.info("Valor Vacante agregada   : {}", vacante);

}
}
