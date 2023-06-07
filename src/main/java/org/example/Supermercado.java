package org.example;

import org.json.JSONArray;

import java.util.*;

public class Supermercado {
    public static List<Caja> cajas;
    private static Queue<Cliente> filaEspera;

    public Supermercado(){
        cajas = new ArrayList<>();
        filaEspera = new PriorityQueue<>();
    }

    public static void agregarCaja(Caja caja){
        cajas.add(caja);
    }

    public static void agregarClienteAFilaEspera(Cliente cliente){
        filaEspera.add(cliente);
    }

    public static void distribuirClientesEnCajas() throws TarjetaConProblemasException{
        int numCajas = cajas.size();
        int numClientes = filaEspera.size();

        if(numClientes == 0){
            System.out.println("No hay clientes en la fila de espera.");
            return;
        }

        int clientesPorCaja = numClientes / numCajas; // Distribución equitativa de clientes entre las cajas
        int clientesExtras = numClientes % numCajas; //Clientes sobrantes que se distribuirán uno por cada caja

        for(int i=0; i<numCajas; i++){
            Caja caja = cajas.get(i);
            int tarjetasConProblemas = 0;

            for(int j=0; j<clientesPorCaja; j++){
                Cliente cliente = filaEspera.poll();
                if(cliente.getMedioDePago().equals(MedioDePago.TARJETACONPROBLEMAS)){
                    tarjetasConProblemas++;
                }
                caja.agregarCliente(cliente);
            }

            if(clientesExtras > 0){
                Cliente cliente = filaEspera.poll();
                if(cliente.getMedioDePago().equals(MedioDePago.TARJETACONPROBLEMAS)){
                    tarjetasConProblemas++;
                }
                caja.agregarCliente(cliente);
                clientesExtras--;
            }

            if(tarjetasConProblemas > 3){
                throw new TarjetaConProblemasException("Hay más de tres clientes con problemas en su tarjeta.");
            }

            JSONArray contenidoCaja = caja.exportarContenido();
            System.out.println("Contenido de la Caja " + i + " : " + contenidoCaja.toString());
        }

        System.out.println("Clientes distribuidos en las cajas.");
    }

    public static void atender(){
        int totalClientesAtendidos = 0;
        double tiempoGral = 0;
        for(int i=0; i<cajas.size(); i++){
            Caja caja = cajas.get(i);
            double tiempoCaja = 0;
            int numClientesAtendidos = 0;

            while(caja.obtenerTamanioFila() != 0){
                Cliente cliente = caja.atenderCliente();
                numClientesAtendidos++;
                tiempoCaja += cliente.calcularTiempoMedioEspera();
            }

            tiempoGral += tiempoCaja;
            totalClientesAtendidos += numClientesAtendidos;
            System.out.println("Resultados de la Caja " + i);
            System.out.println("Clientes atendidos: " + numClientesAtendidos);
            System.out.println("Tiempo promedio de espera: " + (tiempoCaja / numClientesAtendidos));
        }

        System.out.println("Resultados generales:");
        System.out.println("Total de Clientes atendidos: " + totalClientesAtendidos);
        System.out.println("Tiempo promedio de espera en todas las cajas: " + (tiempoGral / totalClientesAtendidos));
    }
}
