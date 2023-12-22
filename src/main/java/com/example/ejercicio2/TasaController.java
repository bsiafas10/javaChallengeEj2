package com.example.ejercicio2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
public class TasaController {

    @PostMapping("/calcular-tasa")
    public TasaResponse calcularTasa(@RequestBody OperacionRequest request) {
        // Lógica para calcular la tasa
        BigDecimal tasa = calcularTasaSegunMarca(request.getMarca());
        BigDecimal importeFinal = calcularImporteFinal(request.getImporte(), tasa);

        // Formatear resultados
        TasaResponse response = new TasaResponse();
        response.setTasa(tasa.setScale(2, RoundingMode.HALF_UP) + "%");
        response.setImporteFinal("$" + importeFinal.setScale(2, RoundingMode.HALF_UP));

        return response;
    }

    // Implementa los métodos de cálculo según la lógica que necesitas
    private BigDecimal calcularTasaSegunMarca(String marca) {
        // Lógica para calcular la tasa según la marca
        switch (marca.toUpperCase()) {
            case "VISA":
                // Lógica para VISA
                break;
            case "NARA":
                // Lógica para NARA
                break;
            case "AMEX":
                // Lógica para AMEX
                break;
            default:
                throw new IllegalArgumentException("Marca de tarjeta no válida: " + marca);
        }

        return BigDecimal.ZERO; // Reemplazar con la lógica real
    }

    private BigDecimal calcularImporteFinal(BigDecimal importe, BigDecimal tasa) {
        // Lógica para calcular el importe final
        return importe.multiply(BigDecimal.ONE.add(tasa.divide(BigDecimal.valueOf(100))));
    }
}
