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
	// ... (los atributos id, nombre, etc., permanecen igual)
	private Integer id;
	private String nombre;
	private String descripcion;
	private Date fecha;
	private double salario;
	private Integer destacado;
	private String imagen = "no-image.png";

	// --- CONSTANTES PARA UMBRALES DE SALARIO ---
	/**
	 * Define los umbrales para clasificar el salario. Usar constantes evita "números mágicos" y hace que el código sea más legible y fácil de mantener.
	 */
	private static final double UMBRAL_SALARIO_BAJO = 2000.0;
	private static final double UMBRAL_SALARIO_NORMAL = 8000.0;

	public Vacante() {

	}

	// --- MÉTODOS PÚBLICOS (GETTERS Y SETTERS) ---
	// ... (todos los getters y setters permanecen igual)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Integer getDestacado() {
		return destacado;
	}

	public void setDestacado(Integer destacado) {
		this.destacado = destacado;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	// --- ENUMERACIÓN PARA CLASIFICAR EL SALARIO ---
	//	Nos aseguramos de que nuestro enum no solo represente el nivel, sino que también contenga
	//	los datos asociados (como el texto descriptivo).
	public enum NivelSalarial {
		// Cada constante tiene su propio texto descriptivo
		BAJO("Sueldo malo"), NORMAL("Sueldo normal"), ALTO("Buen sueldo");

		private final String textoDescriptivo;

		// Constructor para inicializar el texto
		NivelSalarial(String texto) {
			this.textoDescriptivo = texto;
		}

		// Getter para que Thymeleaf pueda acceder al texto
		public String getTextoDescriptivo() {
			return textoDescriptivo;
		}
	}

	// Y el método que ya tienes en tu clase Vacante
	public NivelSalarial getNivelSalarial() {
		// Usando las constantes que definimos previamente
		if (this.salario <= UMBRAL_SALARIO_BAJO){ // ej: 2000.0
			return NivelSalarial.BAJO;
		} else
			if (this.salario <= UMBRAL_SALARIO_NORMAL){ // ej: 8000.0
				return NivelSalarial.NORMAL;
			} else{
				return NivelSalarial.ALTO;
			}
	}




	// --- MÉTODO SOBREESCRITO ---
	@Override
	public String toString() {
		return "Vacante{" + "id=" + id + ", nombre='" + nombre + '\'' + ", descripcion='" + descripcion + '\'' + ", fecha=" + fecha + ", salario=" + salario + ", destacado="
				+ destacado + ", imagen='" + imagen + '\'' + '}';
	}
}
