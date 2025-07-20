// Define que esta clase pertenece al paquete 'net.itinajero.model'.
// Los paquetes organizan las clases en una estructura de carpetas lógicas.
package net.itinajero.model;

// Importa la clase 'Date' del paquete 'java.util' para poder usarla en la definición de la fecha de publicación.

import java.util.Date;

/**
 * Representa un "Modelo" o "POJO" (Plain Old Java Object). Esta clase sirve como una plantilla para crear objetos que contendrán los datos de una oferta de trabajo
 * (vacante).
 */
public class Vacante {

	// --- ATRIBUTOS DE LA CLASE ---
	// Son las variables que definen las propiedades de cada objeto 'Vacante'.
	// Se declaran como 'private' para seguir el principio de encapsulamiento,
	// lo que significa que solo se puede acceder a ellos a través de los métodos públicos (getters y setters).

	/** El identificador único para cada vacante. Se usa 'Integer' en lugar de 'int' para permitir valores nulos. */
	private Integer id;

	/** El nombre o título del puesto de la vacante (ej. "Ingeniero de Software"). */
	private String nombre;

	/** Una descripción detallada de las responsabilidades y requisitos del puesto. */
	private String descripcion;

	/** La fecha en que la vacante fue publicada. */
	private Date fecha;

	/** El salario ofrecido para el puesto. Se usa 'double' para permitir valores con decimales. */
	private double salario;

	/** Un indicador para saber si la vacante es destacada (ej. 1 para sí, 0 para no). */
	private Integer destacado;

	/** El nombre del archivo de imagen asociado a la vacante (ej. "logo_empresa.png"). */
	private String imagen = "no-image.png"; // Se inicializa con una imagen por defecto.


	// --- MÉTODOS PÚBLICOS (GETTERS Y SETTERS) ---
	// Permiten un acceso controlado a los atributos privados de la clase.

	/**
	 * Método "getter" que devuelve el valor del ID de la vacante.
	 *
	 * @return el ID de la vacante.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Método "setter" que establece o modifica el valor del ID de la vacante.
	 *
	 * @param id
	 * 		El nuevo ID que se asignará a la vacante.
	 */
	public void setId(Integer id) {
		this.id = id; // 'this.id' se refiere al atributo de la clase, mientras que 'id' es el parámetro del método.
	}

	/**
	 * Devuelve el nombre de la vacante.
	 *
	 * @return el nombre del puesto.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre de la vacante.
	 *
	 * @param nombre
	 * 		El nuevo nombre del puesto.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la descripción detallada de la vacante.
	 *
	 * @return la descripción del puesto.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Establece la descripción de la vacante.
	 *
	 * @param descripcion
	 * 		La nueva descripción del puesto.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Devuelve la fecha de publicación de la vacante.
	 *
	 * @return la fecha de publicación.
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha de publicación de la vacante.
	 *
	 * @param fecha
	 * 		La nueva fecha de publicación.
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Devuelve el salario de la vacante.
	 *
	 * @return el salario.
	 */
	public double getSalario() {
		return salario;
	}

	/**
	 * Establece el salario de la vacante.
	 *
	 * @param salario
	 * 		El nuevo salario.
	 */
	public void setSalario(double salario) {
		this.salario = salario;
	}

	/**
	 * Devuelve el estado de "destacado" de la vacante.
	 *
	 * @return 1 si es destacada, 0 si no lo es, o null.
	 */
	public Integer getDestacado() {
		return destacado;
	}

	/**
	 * Establece el estado de "destacado" de la vacante.
	 *
	 * @param destacado
	 * 		El nuevo estado (1 o 0).
	 */
	public void setDestacado(Integer destacado) {
		this.destacado = destacado;
	}

	/**
	 * Devuelve el nombre del archivo de imagen.
	 *
	 * @return el nombre del archivo de la imagen.
	 */
	public String getImagen() {
		return imagen;
	}

	/**
	 * Establece el nombre del archivo de imagen.
	 *
	 * @param imagen
	 * 		El nuevo nombre del archivo.
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	// --- MÉTODOS DE LÓGICA DE NEGOCIO ---

	/**
	 * Un método auxiliar que contiene lógica simple. Devuelve 'true' si el salario es mayor a 2000.0, y 'false' en caso contrario. Es útil para no poner esta lógica
	 * directamente en la vista (HTML).
	 *
	 * @return verdadero si el salario es alto, falso si no.
	 */
	public boolean isSalarioAlto() {
		return this.salario > 2000.0;
	}



	// --- MÉTODO SOBREESCRITO ---

	/**
	 * La anotación '@Override' indica que este método está reemplazando un método de la clase padre (en este caso, la clase 'Object'). Devuelve una representación en formato
	 * de texto (String) del objeto. Es extremadamente útil para la depuración y para imprimir el estado del objeto en la consola.
	 *
	 * @return una cadena de texto con los valores de los atributos del objeto.
	 */
	@Override
	public String toString() {
		// Se ha actualizado para incluir todos los campos, lo que lo hace más útil para depurar.
		return "Vacante{" + "id=" + id + ", nombre='" + nombre + '\'' + ", descripcion='" + descripcion + '\'' + ", fecha=" + fecha + ", salario=" + salario + ", destacado="
				+ destacado + ", imagen='" + imagen + '\'' + '}';
	}
}
