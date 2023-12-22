package com.example.ejercicio2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ejercicio2Application {

    public static void main(String[] args) {
        String port = System.getenv("PORT");
        if (port == null || port.isEmpty()) {
            port = "8080";
        }

        System.setProperty("server.port", port);

        SpringApplication.run(Ejercicio2Application.class, args);
    }
}
