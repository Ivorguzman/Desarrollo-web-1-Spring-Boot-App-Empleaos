package net.itinajero; // Define el paquete al que pertenece la clase

    import org.springframework.boot.SpringApplication; // Importa la clase para arrancar la aplicación Spring Boot
    import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa la anotación para configuración automática

    @SpringBootApplication // Marca la clase como una aplicación Spring Boot con configuración automática
    public class EmpleosApplication { // Define la clase principal de la aplicación

        public static void main(String[] args) { // Método principal, punto de entrada de la aplicación
            SpringApplication.run(EmpleosApplication.class, args); // Inicia la aplicación Spring Boot
        }

    }