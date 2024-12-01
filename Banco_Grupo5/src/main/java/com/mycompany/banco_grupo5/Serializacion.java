/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco_grupo5;

/**
 *
 * @author Tomás Alfaro
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
public class Serializacion {

    public Serializacion() {
        
    }
    
    
    
    public void guardarCola(Cola cola, String archivo) {
        Gson gson = new GsonBuilder().create();
        try (FileWriter writer = new FileWriter(archivo)) {
            gson.toJson(cola, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Cola cargarCola(String archivo) {
        Gson gson = new GsonBuilder().create();
        try (FileReader reader = new FileReader(archivo)) {
            return gson.fromJson(reader, Cola.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void revisarTicketsGuardados() {
    String[] archivos = {
        "listaCola1.json",
        "listaCola2.json",
        "listaCola3.json",
        "listaPreferencial.json",
        "listaRapida.json"
    };

    StringBuilder sb = new StringBuilder("Tickets guardados:\n");

    for (String archivo : archivos) {
        Cola cola = cargarCola(archivo);
        if (cola != null && cola.contar() > 0) {
            Nodo nodo = cola.getFrente();
            while (nodo != null) {
                Ticket ticket = nodo.getTicket();
                sb.append("Archivo: ").append(archivo)
                  .append(", Nombre: ").append(ticket.getNombre())
                  .append(", ID: ").append(ticket.getId())
                  .append(", Trámite: ").append(ticket.getTramite()).append("\n");
                nodo = nodo.getAtras();
            }
        } else {
            sb.append("Archivo: ").append(archivo).append(" - No hay tickets.\n");
        }
    }

    // Mostrar los tickets guardados
    JOptionPane.showMessageDialog(null, sb.toString());
}
    
}
