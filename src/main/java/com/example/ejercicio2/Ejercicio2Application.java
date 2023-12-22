package com.example.ejercicio2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ejercicio2Application {

    public static void main(String[] args) {
        // Obtener el puerto del entorno o usar 8080 como predeterminado
        String port = System.getenv("PORT");
        if (port == null || port.isEmpty()) {
            port = "8080";
        }

        // Configurar el puerto
        System.setProperty("server.port", port);

        // Iniciar la aplicación Spring Boot
        SpringApplication.run(Ejercicio2Application.class, args);
    }
}
