package org.example;

import java.util.Random;

public class Cliente implements Comparable<Cliente>{
    private Cliente cliente;
    private MedioDePago medioDePago;

    private final Random random = new Random();
    private int cantidadDeArticulos;

    public Cliente(MedioDePago medioDePago) {
        this.medioDePago = medioDePago;
        this.cantidadDeArticulos = random.nextInt(50);
    }

    public Cliente() {
    }

    public MedioDePago getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(MedioDePago medioDePago) {
        this.medioDePago = medioDePago;
    }

    public int getCantidadDeArticulos() {
        return cantidadDeArticulos;
    }

    public void setCantidadDeArticulos(int cantidadDeArticulos) {
        this.cantidadDeArticulos = cantidadDeArticulos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "medioDePago='" + medioDePago + '\'' +
                ", cantidadDeArticulos=" + cantidadDeArticulos +
                '}';
    }

    public double calcularTiempoMedioEspera(){
        double tiempo = 0;

        int tiempoPorArticulos = 30 * getCantidadDeArticulos();
        tiempo += tiempoPorArticulos;

        if(cliente instanceof Comun){
            tiempo *= 1.15;
        }else if (cliente instanceof Jubilado){
            tiempo *= 1.25;
        }else {
            tiempo *= 1.20;
        }

        if(medioDePago == MedioDePago.EFECTIVO){
            tiempo *= 1.10;
        } else if (medioDePago == MedioDePago.TARJETASINPROBLEMAS) {
            tiempo *= 1.15;
        }else {
            tiempo *= 1.50;
        }

        return tiempo;
    }

    public int compareTo(Cliente otroCliente){
        return Double.compare(calcularTiempoMedioEspera(), otroCliente.calcularTiempoMedioEspera());
    }
}
