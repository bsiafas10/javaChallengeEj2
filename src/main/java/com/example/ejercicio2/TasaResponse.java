package com.example.ejercicio2;


public class TasaResponse {
    private String tasa;
    private String importeFinal;

    public TasaResponse(String tasa, String importeFinal) {
        this.tasa = tasa;
        this.importeFinal = importeFinal;
    }    

    public String getTasa() {
        return tasa;
    }

    public void setTasa(String tasa) {
        this.tasa = tasa;
    }

    public String getImporteFinal() {
        return importeFinal;
    }

    public void setImporteFinal(String importeFinal) {
        this.importeFinal = importeFinal;
    }
}
