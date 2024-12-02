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
        if (frente == null) {
            frente = nuevoNodo;
        } else {
            Nodo temp = frente;
            while (temp.getAtras() != null) {
                temp = temp.getAtras();
            }
            temp.setAtras(nuevoNodo);
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
    
    public int contar(){
        int contador=0;
        Nodo aux=fin;
        if(aux!=null){//Si la cola no esta vacía existe al menos 1
           contador=fin.getTicket().getId(); 
        }
        
        return contador;
    }
}
