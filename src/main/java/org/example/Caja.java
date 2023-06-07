package org.example;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.LinkedList;
import java.util.Queue;

public class Caja {

    private Queue<Cliente> fila;

    public Caja(){
        fila = new LinkedList<>();
    }

    public void agregarCliente(Cliente cliente){
        fila.add(cliente);
    }

    public Cliente atenderCliente(){
        return fila.poll();
    }

    public int obtenerTamanioFila(){
        return fila.size();
    }
    public Queue<Cliente> getFila() {
        return fila;
    }

    public JSONArray exportarContenido(){
        JSONArray jsonArray = new JSONArray();
        for(Cliente cliente : fila){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("medioDePago", cliente.getMedioDePago().toString());
            jsonObject.put("cantidadDeArticulos", cliente.getCantidadDeArticulos());
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
}
