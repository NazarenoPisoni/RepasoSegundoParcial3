package org.example;

import java.util.Random;

public enum MedioDePago{
    EFECTIVO("Efectivo") ,TARJETASINPROBLEMAS("Tarjeta sin problemas"), TARJETACONPROBLEMAS("Tarjeta con problemas");

    private final String tipo;

    private MedioDePago(String tipo){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static MedioDePago obtenerMedioDePagoAleatorio(){
        MedioDePago[] mediosPago = MedioDePago.values();
        Random random = new Random();
        int indiceAleatorio = random.nextInt(mediosPago.length);

        return mediosPago[indiceAleatorio];
    }
}