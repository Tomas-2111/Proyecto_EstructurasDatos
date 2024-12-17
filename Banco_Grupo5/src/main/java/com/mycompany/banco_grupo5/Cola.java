/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco_grupo5;

import javax.swing.JOptionPane;

/**
 *
 * @author Tomás Alfaro
 */
public class Cola {
    private Nodo frente;
    private Nodo fin;

    public Nodo getFrente() {
        return frente;
    }

    public void setFrente(Nodo frente) {
        this.frente = frente;
    }

    public Nodo getUltimo() {
        return fin;
    }

    public void setfin(Nodo fin) {
        this.fin = fin;
    }

    

    public void agregarTicket(Ticket ticket) {
        Nodo nuevoNodo = new Nodo(ticket);
    
        if (fin == null) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.setAtras(nuevoNodo);
            fin = nuevoNodo;
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
    
    public void eliminarTicket() {
        if (frente != null) {
            Nodo nodoAEliminar = frente; 
            frente = frente.getAtras(); 

            if (frente == null) {
                fin = null; 
            }
            nodoAEliminar.setAtras(null);
        } else {
            JOptionPane.showMessageDialog(null, "No hay tickets en la cola para eliminar.");
        }
    }
    
    
    public int contar(){
        int contador=0;
        Nodo aux=frente;
        if(aux!=null){//Si la cola no esta vacía existe al menos 1
           contador=1;
           while(aux.getAtras()!=null){
                contador+=1;
                aux=aux.getAtras();
            }
           
        }
        return contador;
    }
}
