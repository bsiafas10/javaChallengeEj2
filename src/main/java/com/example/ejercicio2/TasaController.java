package com.example.ejercicio2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;

@RestController
public class TasaController {

    @PostMapping("/calcular-tasa")
    public ResponseEntity<TasaResponse> calcularTasa(@RequestBody OperacionRequest request) {
        if (request.getMarca() == null || request.getImporte() == null) {
            return ResponseEntity.badRequest()
                    .body(new TasaResponse("La solicitud debe incluir la marca de la tarjeta y el importe de la operación.", "0"));
        }
    
        try {
            BigDecimal tasa = calcularTasaSegunMarca(request.getMarca());
            BigDecimal importeFinal = calcularImporteFinal(request.getImporte(), tasa);
    
            TasaResponse response = new TasaResponse(
                tasa.setScale(2, RoundingMode.HALF_UP) + "%",
                "$" + importeFinal.setScale(2, RoundingMode.HALF_UP)
            );
    
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new TasaResponse("Error: " + e.getMessage(), "0"));
        }
    }
    
    private BigDecimal calcularTasaSegunMarca(String marca) {
        switch (marca.toUpperCase()) {
            case "VISA":
                return calcularTasaVisa();
            case "NARA":
                return calcularTasaNara();
            case "AMEX":
                return calcularTasaAmex();
            default:
                throw new IllegalArgumentException("Marca de tarjeta no válida: " + marca);
        }
    }

    private BigDecimal calcularTasaVisa() {
        Calendar calendar = Calendar.getInstance();
        int anioActualVisa = calendar.get(Calendar.YEAR) % 100;
        int mesActualVisa = calendar.get(Calendar.MONTH) + 1;
        return BigDecimal.valueOf(Math.max(0.3, Math.min(anioActualVisa / (double) mesActualVisa, 5.0)));
    }

    private BigDecimal calcularTasaNara() {
        Calendar calendar = Calendar.getInstance();
        int diaMesActualNara = calendar.get(Calendar.DAY_OF_MONTH);
        return BigDecimal.valueOf(Math.max(0.3, Math.min(diaMesActualNara * 0.5, 5.0)));
    }

    private BigDecimal calcularTasaAmex() {
        Calendar calendar = Calendar.getInstance();
        int mesActualAmex = calendar.get(Calendar.MONTH) + 1;
        return BigDecimal.valueOf(Math.max(0.3, Math.min(mesActualAmex * 0.1, 5.0)));
    }

    private BigDecimal calcularImporteFinal(BigDecimal importe, BigDecimal tasa) {
        return importe.multiply(BigDecimal.ONE.add(tasa.divide(BigDecimal.valueOf(100))));
    }
}
