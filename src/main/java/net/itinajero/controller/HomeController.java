package net.itinajero.controller;

import net.itinajero.model.Vacante;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;




// Esta clase es un controlador de Spring MVC
// La anotación @Controller indica que esta clase maneja solicitudes web
// y devuelve vistas a los usuarios.
// Indica a spring que esta clase es un controlador
@Controller
public class HomeController {
	/*
	@GetMapping("/listado") // Anotación que indica que este método responderá a solicitudes HTTP GET en la ruta "/listado"
	public String mostrarListado(Model modelo) {
		// Crea una lista mutable de cadenas (puede ser modificada después de su creación)
		// significa que su contenido puede ser modificado después de su creación;
		List<String> lista = new LinkedList<>();
		lista.add("Ingeniería de Software"); // Agrega un elemento a la lista
		lista.add("Auxiliar Contable"); // Agrega otro elemento a la lista
		lista.add("Vendedor"); // Agrega un tercer elemento a la lista
		lista.add("Arquitecto de Software"); // Agrega un cuarto elemento a la lista
		// Agrega la lista al modelo con el nombre "listaVacantes" para que esté disponible en la vista
		modelo.addAttribute("empleo", lista);

		return "listado"; // Devuelve la cadena "listado", que normalmente corresponde al nombre de una vista
	}
*/
	@GetMapping("/listado")// Anotación que indica que este método responderá a solicitudes HTTP GET en la ruta "/listado"
	public String mostrarListado(Model modelo) {
		// Crea una lista inmutable de cadenas (no se puede modificar después de su creación)
		List<String> lista = List.of("Ingeniería de Software", "Auxiliar Contable", "Vendedor", "Arquitecto de Software");

		// Pasamos la lista inmutable directamente al modelo. Con el nombre "listaVacantes" para que est&eacute; disponible en la vista
		modelo.addAttribute("empleo", lista);

		return "listado";
	}


	// @GetMapping("/") -> Anotación que indica que este método responderá a solicitudes HTTP GET en
	// la ruta "/" que representa la raíz del sitio web o aplicación.
	@GetMapping("/")
	public String mostrarHome(Model modelo) {
		// Atributos
		String nombre = "Auxiliar Contable";// Define un nombre
		Date fecha = new Date(); // Crea un objeto Date que representa la fecha y hora actuales
		double salario = 9000.0; // Define un salario de
		boolean vigente = true; // Define un estado de vigencia

		// El modelo se utiliza para pasar datos a la vista que se renderizará
		modelo.addAttribute("atributo_nombre", nombre); // Agrega el atributo "nombre" al modelo
		modelo.addAttribute("atributo_fecha", fecha); // Agrega el atributo "fecha" al modelo
		modelo.addAttribute("atributo_salario", salario); // Agrega el atributo "salario" al modelo
		modelo.addAttribute("atributo_vigente", vigente); // Agrega el atributo "vigente" al modelo

		/*
		 *  En Spring MVC, este String ("home") no es un texto cualquiera, es el nombre de la vista (el archivo de la plantilla, como home.html)
		 * que Spring debe buscar y renderizar.
		 */
		return "home"; // return "home"; -> Devuelve la cadena "home", que normalmente corresponde al nombre de una vista

	}

	@GetMapping("/detalle")// Anotación que indica que este método responderá a solicitudes HTTP GET en la ruta "/detalles"
	// Este método no recibe parámetros, pero puede recibir un objeto Model para pasar datos a la vista
	public String mostrarDetalles(Model modelo) {
		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero de comunicación");
		vacante.setDescripcion("Vacante para ingeniero de comunicaciones con experiencia en redes y telecomunicaciones.");
		vacante.setFecha(new Date()); // Establece la fecha actual como la fecha de la vacante
		vacante.setSalario(3800.0); // Establece el salario de la vacante
		modelo.addAttribute("vacante", vacante); // Agrega el objeto vacante al modelo con el nombre "vacante"


		return "detalle";
	}


	@GetMapping("/tabla")
	public String mostrarTabla(Model model) {

		List<Vacante> lista = getVacantes();

		model.addAttribute("vacantes", lista);


		System.out.println("Lista de Vacantes: " + lista);
		return "tabla";

	}



	// Antes de crear la capa servicio colocamos esta logic aquí por motivos pedagogic
	private List<Vacante> getVacantes() {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		List<Vacante> lista = new LinkedList<>();

		try{
			// Ofertas de trabajo 1
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero Civil");
			vacante1.setDescripcion("Solicitamos Para el equipo de construcción de puente peatonal");
			vacante1.setFecha(simpleDateFormat.parse("01/01/2025"));
			vacante1.setSalario(8500.0);


			//Oferta de trabajo 2
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Contador publico");
			vacante2.setDescripcion("Contador titulado con experiencia en contabilidades de costo");
			vacante2.setFecha(simpleDateFormat.parse("01/02/2025"));
			vacante2.setSalario(12000.0);

			//Oferta de trabajo 3
			Vacante vacante3 = new Vacante();
			vacante3.setId(3);
			vacante3.setNombre("Ingeniero Eléctrico");
			vacante3.setDescripcion("Ingeniero eléctrico con experiencia en instalaciones industriales");
			vacante3.setFecha(simpleDateFormat.parse("01/03/2025"));
			vacante3.setSalario(10500.0);

			//Oferta de trabajo 4
			Vacante vacante4 = new Vacante();
			vacante4.setId(4);
			vacante4.setNombre("Diseñador Gráfico");
			vacante4.setDescripcion("Diseñador gráfico con experiencia en diseño digital y branding");
			vacante4.setFecha(simpleDateFormat.parse("01/04/2025"));
			vacante4.setSalario(7900.0);

			lista.add(vacante1);
			lista.add(vacante2);
			lista.add(vacante3);
			lista.add(vacante4);

			System.out.println("lista = " + lista);

		} catch(ParseException e){
			System.out.println("Error: " + e.getMessage());
		}


		return lista;
	}



}

    /*
    * En Spring Boot, la anotación <<<<<<`@Controller`>>>>>> se asocia comúnmente con otras anotaciones para manejar solicitudes web.
    * Aquí un "mapa" de las anotaciones más usadas junto a `@Controller`:

- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`: Definen métodos que responden a solicitudes HTTP GET, POST, PUT y DELETE.
- `@RequestMapping`: Permite mapear rutas y métodos HTTP de forma más general.
- `@RequestParam`: Extrae parámetros de la URL.
- `@PathVariable`: Extrae variables de la ruta.
- `@ModelAttribute`: Vincula datos de formularios a objetos.
- `@RequestBody`: Recibe datos en formato JSON o XML en el cuerpo de la solicitud.
- `@ResponseBody`: Indica que el valor devuelto se escribe directamente en la respuesta HTTP (usado más en `@RestController`).

   Estas anotaciones permiten que el controlador reciba, procese y responda a solicitudes web de manera flexible.

   */


	/*


                                    <<<<<<<<<<<<<<<<<<<<  @Controller vs. @RestController:  >>>>>>>>>>>>>>>>>>>>>>>>>>>
	•@Controller: Se usa para aplicaciones web tradicionales. Los métodos típicamente devuelven un String que representa el nombre de una vista.
	•@RestController: Se usa para construir APIs RESTful. Es una anotación de conveniencia que combina @Controller y @ResponseBody.
	 Esto significa que los métodos devuelven datos (como un objeto User) directamente,
	 los cuales Spring convierte automáticamente a un formato como JSON y los escribe en
	 el cuerpo de la respuesta HTTP.( No se devuelven nombres de vistas).



--------------------------------------------------------------------------------------
Paso 1: El objeto que vamos a devolver (El "Dato Puro")Primero,
necesitamos una clase que represente los datos. Es un simple objeto Java (POJO).
________________________________________________________________________________________
	// Este es nuestro objeto de datos. No necesita anotaciones especiales.
   public class Vacante {
       private int id;
       private String titulo;
       private double salario;

    // Constructor
    public Vacante(int id, String titulo, double salario) {
        this.id = id;
        this.titulo = titulo;
        this.salario = salario;
    }


    // Getters y Setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}





------------------------------------------------------------------------------------------------------------
Paso 2: El @RestControllerAhora creamos un controlador especial para nuestra API. Nota las diferencias clave.
-------------------------------------------------------------------------------------------------------------
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// 1. Usamos @RestController en lugar de @Controller
@RestController
public class VacantesApiController {

    // 2. Mapeamos una ruta, por ejemplo /api/vacantes/{id}
    // El {id} es una variable en la URL.
    @GetMapping("/api/vacantes/{id}")
    public Vacante getVacante(@PathVariable int id) {
        // En un caso real, buscaríamos la vacante en una base de datos usando el 'id'.
        // Aquí, para el ejemplo, creamos una vacante en el momento.
        Vacante vacante = new Vacante(id, "Ingeniero de Software Senior", 120000.0);

        // 3. ¡La magia! Devolvemos el objeto 'vacante' directamente.
        // No devolvemos un String como "home" o "listado".
        return vacante;
    }
}


-----------------------------------------------------------------------------------------------------------------------------------
Paso 3: Si ahora abres tu navegador y vas a la dirección: http://localhost:8080/api/vacantes/105 No verás una página HTML.
En su lugar, verás directamente los datos del objeto Vacante que creamos,convertidos automáticamente a formato JSON por Spring Boot:
------------------------------------------------------------------------------------------------------------------------------------
{
  "id": 105,
  "titulo": "Ingeniero de Software Senior",
  "salario": 120000.0
}


	 *  Resumen de la Diferencia| Característica | @Controller (Tu HomeController) || @RestController (Nuestro VacantesApiController)
	 * | Propósito: Crear páginas web completas para humanos.|| Crear "endpoints" de API para que otras aplicaciones (o JavaScript) consuman datos.
	 * | ¿Qué devuelve?: Un String con el nombre de una vista HTML.|| Un objeto de datos (como un objeto Java) que Spring convierte a JSON.
	 * | Resultado final: El navegador renderiza una página HTML.|| El navegador (o cliente) recibe un texto en formato JSON.
	 * | @RestController es la base para construir APIs REST ful, que son el estándar de la industria para que las aplicaciones se comuniquen entre sí.



	 */
