package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<Cliente> clientes = generarClientes(20);
        Supermercado supermercado = new Supermercado();

        for(Cliente cliente : clientes){
            supermercado.agregarClienteAFilaEspera(cliente);
        }

        for(int i=0; i<3; i++){
            supermercado.agregarCaja(new Caja());
        }

        try{
            supermercado.distribuirClientesEnCajas();
        }catch (TarjetaConProblemasException e){
            System.out.println("Error: " + e.getMessage());
        }


        supermercado.atender();
    }

    public static List<Cliente> generarClientes(int cantidad){
        List<Cliente> clientes = new ArrayList<>();
        Random random = new Random();

        for(int i=0; i<cantidad; i++){
            int tipoCliente = random.nextInt(3);

            if(tipoCliente == 0){
                clientes.add(new Comun(MedioDePago.obtenerMedioDePagoAleatorio()));
            } else if (tipoCliente == 1) {
                clientes.add(new Jubilado(MedioDePago.obtenerMedioDePagoAleatorio()));
            }else {
                clientes.add(new Embarazada(MedioDePago.obtenerMedioDePagoAleatorio()));
            }
        }
        return clientes;
    }
}

