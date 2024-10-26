/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco_grupo5;

/**
 *
 * @author Tomás Alfaro
 */
public class Cola {
    private Nodo frente;
    private Nodo ultimo;

    public Nodo getFrente() {
        return frente;
    }

    public void setFrente(Nodo frente) {
        this.frente = frente;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }

    
    public Ticket crearTicket(String nombre, int id, String edad, String horaCreacion, String tramite, String tipo) {
        return new Ticket(nombre, id, edad, horaCreacion, null, tramite, tipo);
    }

    public void agregarTicket(String nombre, int id, String edad, String horaCreacion, String tramite, String tipo) {
        Ticket nuevoTicket = crearTicket(nombre, id, edad, horaCreacion, tramite, tipo);
        Nodo nuevoNodo = new Nodo(nuevoTicket);
        hacerFila(nuevoNodo);
    }

    
    public void hacerFila(Nodo elemento)
    {
        if(frente == null) // La fila está vacía
        {
            frente = elemento;
            ultimo = elemento;
        }
        else
        {
           
            ultimo.setAtras(elemento);
            ultimo = elemento;
            
        }
    }
    
    public Nodo atender()
    {
        Nodo actual = frente;
        
        if(frente != null)
        {
            frente = frente.getAtras();
            actual.setAtras(null);
        }
        
        return actual;
    }
}
