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
    
}
