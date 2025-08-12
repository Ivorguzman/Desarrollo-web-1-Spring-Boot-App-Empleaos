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
package net.itinajero;

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

import org.springframework.boot.SpringApplication; // Importa la clase principal de Spring Boot para iniciar la aplicación.
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa la anotación clave para una aplicación Spring Boot.

// =======================================================================================
// SECCIÓN 3: LA DECLARACIÓN DE LA CLASE PRINCIPAL (EL CORAZÓN DE LA APLICACIÓN)
// =======================================================================================

/**
 * ¿Qué es esta clase?
 * Esta es la clase principal de nuestra aplicación Spring Boot, `EmpleosApplication`.
 * Es el punto de entrada desde donde se inicia toda la aplicación.
 *
 * ¿Cómo funciona?
 * La anotación `@SpringBootApplication` es la clave aquí. Es una anotación de conveniencia
 * que combina tres anotaciones importantes de Spring Boot:
 * 1. `@Configuration`: Marca la clase como una fuente de definiciones de beans para el contexto de la aplicación.
 * 2. `@EnableAutoConfiguration`: Le dice a Spring Boot que configure automáticamente la aplicación
 *    basándose en las dependencias del classpath y otras configuraciones. Por ejemplo, si tienes
 *    `spring-webmvc` en tu classpath, configurará un servidor web Tomcat.
 * 3. `@ComponentScan`: Le dice a Spring que busque otros componentes, configuraciones y servicios
 *    en el paquete actual y en los subpaquetes para que los gestione.
 *
 * ¿Por qué se implementa así?
 * - **Simplicidad**: `@SpringBootApplication` simplifica enormemente la configuración de una
 *   aplicación Spring, reduciendo la cantidad de código boilerplate.
 * - **Auto-configuración**: Permite que Spring Boot "adivine" y configure automáticamente
 *   muchos aspectos de la aplicación, lo que acelera el desarrollo.
 * - **Convención sobre Configuración**: Sigue el principio de convención sobre configuración,
 *   donde Spring Boot proporciona valores por defecto sensatos que se pueden sobrescribir.
 */
@SpringBootApplication
public class EmpleosApplication {

	// =======================================================================================
	// SECCIÓN 4: EL MÉTODO MAIN (EL BOTÓN DE ENCENDIDO)
	// =======================================================================================

	/**
	 * ¿Qué hace este método?
	 * Este es el método `main` de Java, el punto de entrada estándar para cualquier aplicación Java.
	 * En una aplicación Spring Boot, es el método que se ejecuta para arrancar el servidor
	 * y toda la lógica de la aplicación.
	 *
	 * ¿Cómo lo logra?
	 * `SpringApplication.run(EmpleosApplication.class, args);`:
	 * - `SpringApplication.run()`: Es el método estático de la clase `SpringApplication`
	 *   que se encarga de inicializar y arrancar la aplicación Spring Boot.
	 * - `EmpleosApplication.class`: Le indica a Spring Boot cuál es la clase principal
	 *   de la aplicación (esta misma clase) para que pueda escanearla y configurarla.
	 * - `args`: Pasa cualquier argumento de línea de comandos que se haya proporcionado
	 *   al iniciar la aplicación.
	 *
	 * ¿Por qué se implementa así?
	 * - **Estándar de Java**: Es el punto de entrada reconocido por la Máquina Virtual Java (JVM).
	 * - **Inicio de Spring Boot**: Es la forma canónica de iniciar una aplicación Spring Boot,
	 *   encargándose de todo el proceso de inicialización del contexto de Spring, el servidor
	 *   web embebido (como Tomcat), y el escaneo de componentes.
	 * - **Simplicidad**: A pesar de todo lo que hace, la llamada al método `run` es muy concisa,
	 *   lo que refleja la filosofía de Spring Boot de facilitar el desarrollo.
	 */
	public static void main(String[] args) {
		SpringApplication.run(EmpleosApplication.class, args);
	}

}
