// =======================================================================================
// SECCIÓN 1: EL PAQUETE (LA DIRECCIÓN DEL ARCHIVO)
// =======================================================================================
// ¿Qué es un paquete?
// Un paquete es como una carpeta que organiza tus clases de Java. Ayuda a evitar conflictos de nombres
// y a estructurar tu proyecto de manera lógica.

// ¿Cómo funciona?
// La palabra clave `package` al inicio del archivo indica a qué paquete pertenece esta clase.

// ¿Por qué se usa?
// Para mantener el código ordenado, modular y fácil de mantener, especialmente en proyectos grandes.
package net.itinajero.model;

// =======================================================================================
// SECCIÓN 2: LAS IMPORTACIONES (LA CAJA DE HERRAMIENTAS)
// =======================================================================================
// ¿Qué son las importaciones?
// Las importaciones son declaraciones que permiten a tu clase usar otras clases
// que están definidas en diferentes paquetes o librerías.

// ¿Cómo funcionan?
// La palabra clave `import` le dice al compilador dónde encontrar las clases que necesitas.

// ¿Por qué se usan?
// Para reutilizar código y funcionalidades ya existentes, sin tener que reescribirlas.
// Esto hace que tu código sea más conciso y eficiente.

import java.util.Date;

// =======================================================================================
// SECCIÓN 3: LA DECLARACIÓN DE LA CLASE (EL PLANO DE LA VACANTE)
// =======================================================================================

/**
 * ¿Qué es esta clase? Esta clase, `Vacante`, es un "Modelo" o "POJO" (Plain Old Java Object). Actúa como un plano o plantilla para crear objetos que representarán una oferta
 * de trabajo en nuestra aplicación. Contiene los datos (atributos) y el comportamiento básico (métodos) de una vacante. ¿Cómo funciona? Define las características que tendrá
 * cada vacante (como ID, nombre, descripción, etc.) y proporciona métodos para acceder y modificar esos datos. ¿Por qué se implementa así? - **Representación de Datos**: Es
 * la forma estándar de representar entidades de negocio en Java. Cada objeto `Vacante` contendrá la información de una única oferta de trabajo. - **Encapsulación**: Al
 * mantener los atributos privados y exponerlos a través de métodos públicos (getters y setters), se controla cómo se accede y modifica la información, lo que mejora la
 * seguridad y el mantenimiento del código. - **Reutilización**: Este modelo puede ser utilizado por diferentes partes de la aplicación (controladores, servicios,
 * repositorios) para manejar los datos de las vacantes.
 */
public class Vacante {

	// =======================================================================================
	// SECCIÓN 4: ATRIBUTOS DE LA CLASE (LAS CARACTERÍSTICAS DE LA VACANTE)
	// =======================================================================================

	// ¿Qué son los atributos?
	// Son las variables que definen las propiedades o características de un objeto `Vacante`.
	// Cada objeto `Vacante` que crees tendrá su propio conjunto de estos valores.

	// ¿Cómo funcionan?
	// Se declaran como `private` para aplicar el principio de encapsulación, lo que significa
	// que solo pueden ser accedidos directamente desde dentro de esta clase. Para acceder
	// o modificarlos desde fuera, se utilizan los métodos `getter` y `setter`.

	// ¿Por qué se usan?
	// Para almacenar la información específica de cada vacante (ej. el ID de la vacante,
	// su nombre, la fecha de publicación, etc.).

	private Integer id; // ¿Qué? Identificador único de la vacante. ¿Por qué? Para diferenciar una vacante de otra.
	private String nombre; // ¿Qué? Título o nombre del puesto de trabajo. ¿Por qué? Para describir el puesto.
	private String descripcion; // ¿Qué? Descripción detallada de las responsabilidades y requisitos. ¿Por qué? Para informar al candidato.
	private Date fecha; // ¿Qué? Fecha de publicación de la vacante. ¿Por qué? Para saber cuándo fue publicada.
	private double salario; // ¿Qué? Salario ofrecido para el puesto. ¿Por qué? Información clave para el candidato.
	private Integer destacado; // ¿Qué? Indicador si la vacante es destacada (1) o no (0). ¿Por qué? Para darle mayor visibilidad.
	private String imagen = "no-image.png"; // ¿Qué? Nombre del archivo de imagen asociado a la vacante. ¿Por qué? Para mostrar un logo o imagen representativa. ¿Cómo? Valor por defecto si no se asigna una imagen.

	// =======================================================================================
	// SECCIÓN 5: CONSTANTES (VALORES FIJOS Y REUTILIZABLES)
	// =======================================================================================

	/**
	 * ¿Qué son estas constantes? Son valores numéricos fijos que definen los umbrales para clasificar el salario de una vacante en diferentes niveles (bajo, normal, alto).
	 * ¿Cómo funcionan? Se declaran como `private static final`: - `private`: Solo accesibles dentro de esta clase. - `static`: Pertenecen a la clase, no a una instancia
	 * específica. Solo hay una copia de estas variables, compartida por todos los objetos `Vacante`. - `final`: Su valor no puede ser cambiado una vez inicializado. Son
	 * inmutables. ¿Por qué se usan? - **Evitar "números mágicos"**: En lugar de usar `2000.0` directamente en el código, usamos un nombre descriptivo
	 * (`UMBRAL_SALARIO_BAJO`), lo que mejora la legibilidad. - **Facilidad de Mantenimiento**: Si los umbrales cambian en el futuro, solo necesitas modificar el valor en un
	 * único lugar (aquí), en lugar de buscar y cambiar cada ocurrencia en el código. - **Claridad**: Hacen que el propósito de los valores sea evidente.
	 */
	private static final double UMBRAL_SALARIO_BAJO = 2000.0;
	private static final double UMBRAL_SALARIO_NORMAL = 8000.0;

	// =======================================================================================
	// SECCIÓN 6: CONSTRUCTOR (CÓMO CREAR UN OBJETO VACANTE)
	// =======================================================================================

	/**
	 * ¿Qué es este constructor? Es el constructor por defecto de la clase `Vacante`. Es un método especial que se llama automáticamente cuando creas un nuevo objeto
	 * `Vacante` sin proporcionar ningún argumento. ¿Cómo funciona? Cuando escribes `new Vacante()`, este constructor se ejecuta. En este caso, no realiza ninguna
	 * inicialización explícita de los atributos, pero Java se encarga de asignar valores por defecto (0 para números, `null` para objetos, `false` para booleanos). Nota: El
	 * atributo `imagen` ya tiene un valor por defecto asignado en su declaración. ¿Por qué se implementa así? - **Creación de Objetos**: Permite crear instancias de la clase
	 * `Vacante`. - **Flexibilidad**: Es útil cuando quieres crear un objeto vacío y luego establecer sus propiedades usando los métodos `setter`. - **Requisito de
	 * Frameworks**: Algunos frameworks (como Spring o JPA) requieren un constructor sin argumentos para poder crear objetos automáticamente.
	 */
	public Vacante() {

	}

	// =======================================================================================
	// SECCIÓN 7: MÉTODOS GETTER Y SETTER (ACCESO Y MODIFICACIÓN DE ATRIBUTOS)
	// =======================================================================================

	// ¿Qué son los Getters y Setters?
	// Son métodos públicos que permiten acceder (get) y modificar (set) los valores
	// de los atributos privados de la clase desde fuera de ella.

	// ¿Cómo funcionan?
	// - Los `getters` (ej. `getId()`) devuelven el valor actual de un atributo.
	// - Los `setters` (ej. `setId(Integer id)`) toman un valor como argumento y lo asignan
	//   al atributo correspondiente (`this.id = id;`). La palabra clave `this` se usa
	//   para diferenciar el atributo de la clase del parámetro del método cuando tienen el mismo nombre.

	// ¿Por qué se usan?
	// - **Encapsulación**: Mantienen los atributos privados, controlando cómo se interactúa
	//   con ellos. Esto permite añadir lógica de validación en los setters si es necesario
	//   (ej. `if (id > 0) this.id = id;`).
	// - **Flexibilidad**: Si la implementación interna de un atributo cambia, los métodos
	//   getter y setter pueden adaptarse sin afectar el código externo que los utiliza.
	// - **Convención**: Son una convención estándar en Java para las clases de modelo (POJOs),
	//   y muchos frameworks (como Spring, JPA, Hibernate) dependen de ellos para funcionar correctamente.

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

	// =======================================================================================
	// SECCIÓN 8: ENUMERACIÓN (CLASIFICACIÓN DE SALARIOS)
	// =======================================================================================

	/**
	 * ¿Qué es esta enumeración (`enum`)? `NivelSalarial` es un tipo especial de clase en Java que representa un conjunto fijo de constantes. En este caso, define los
	 * posibles niveles de salario de una vacante: BAJO, NORMAL y ALTO. ¿Cómo funciona? Cada constante de la enumeración (`BAJO`, `NORMAL`, `ALTO`) es un objeto de tipo
	 * `NivelSalarial`. Estas constantes pueden tener atributos y métodos propios, como `textoDescriptivo` y `getTextoDescriptivo()`. El constructor `NivelSalarial(String
	 * texto)` se usa para inicializar el `textoDescriptivo` de cada constante cuando se define. ¿Por qué se implementa así? - **Seguridad de Tipo**: Restringe los valores
	 * posibles a un conjunto predefinido, evitando errores de escritura o valores inválidos (ej. no puedes tener un `NivelSalarial` que no sea BAJO, NORMAL o ALTO). -
	 * **Legibilidad**: Hace que el código sea más claro y fácil de entender al usar nombres descriptivos en lugar de números o cadenas arbitrarias. - **Comportamiento
	 * Asociado**: Permite asociar datos (como el `textoDescriptivo`) y comportamiento (métodos) directamente a cada constante de la enumeración.
	 */
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

	/**
	 * ¿Qué hace este método? Este método calcula y devuelve el `NivelSalarial` (BAJO, NORMAL, ALTO) de la vacante basándose en su `salario` actual. ¿Cómo lo logra? Utiliza
	 * una serie de condiciones `if-else if-else` para comparar el `salario` de la vacante con las constantes `UMBRAL_SALARIO_BAJO` y `UMBRAL_SALARIO_NORMAL`. - Si el salario
	 * es menor o igual al `UMBRAL_SALARIO_BAJO`, devuelve `NivelSalarial.BAJO`. - Si no, y el salario es menor o igual al `UMBRAL_SALARIO_NORMAL`, devuelve
	 * `NivelSalarial.NORMAL`. - En cualquier otro caso (salario mayor que `UMBRAL_SALARIO_NORMAL`), devuelve `NivelSalarial.ALTO`. ¿Por qué se implementa así? - **Lógica de
	 * Negocio**: Encapsula la lógica para clasificar el salario dentro de la propia clase `Vacante`, lo que hace que el modelo sea más inteligente y autónomo. -
	 * **Reutilización**: Permite que cualquier parte de la aplicación que tenga un objeto `Vacante` pueda obtener fácilmente su nivel salarial sin duplicar la lógica de
	 * clasificación. - **Claridad**: Al usar constantes para los umbrales y una enumeración para los niveles, el código es muy legible y fácil de entender.
	 */
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

	// =======================================================================================
	// SECCIÓN 9: MÉTODO toString() (REPRESENTACIÓN EN TEXTO DEL OBJETO)
	// =======================================================================================

	/**
	 * ¿Qué hace este método? `@Override` indica que este método está sobrescribiendo un método de la clase padre (`Object`). El método `toString()` devuelve una
	 * representación en formato de cadena de texto del objeto `Vacante`. ¿Cómo lo logra? Construye una cadena de texto que incluye los nombres y valores de todos los
	 * atributos importantes de la vacante (id, nombre, descripcion, fecha, salario, destacado, imagen). Esto se hace concatenando cadenas y los valores de los atributos.
	 * ¿Por qué se implementa así? - **Depuración**: Es extremadamente útil para depurar tu aplicación. Cuando imprimes un objeto `Vacante` (ej.
	 * `System.out.println(vacante);`), Java llama automáticamente a este método `toString()`, lo que te permite ver el estado completo del objeto de forma legible en la
	 * consola. - **Logging**: Facilita el registro de información sobre los objetos en los logs de la aplicación. - **Representación Amigable**: Proporciona una forma rápida
	 * y sencilla de obtener una descripción textual de un objeto.
	 */
	@Override
	public String toString() {
		return "Vacante{" + "id=" + id + ", nombre='" + nombre + "'" + ", descripcion='" + descripcion + "'" + ", fecha=" + fecha + ", salario=" + salario + ", destacado="
				+ destacado + ", imagen='" + imagen + "'" + '}';
	}
}
