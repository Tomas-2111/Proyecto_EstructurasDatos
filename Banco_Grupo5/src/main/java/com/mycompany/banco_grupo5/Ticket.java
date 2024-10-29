/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco_grupo5;

/**
 *
 * @author Tomás Alfaro
 */
public class Ticket {
    
    private String nombre;
    private int id;
    private String edad;
    private String horaCreacion;
    private String tramite;
    private String tipoTicket;
    private long horaAtencion; 
   

    public Ticket(String nombre, int id, String edad, String horaCreacion, String tramite, String tipoTicket) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.horaCreacion = horaCreacion;
        this.tramite = tramite;
        this.tipoTicket = tipoTicket;
        this.horaAtencion = -1;
    }

    public String getNombre() {
         return nombre; 
        }
    public int getId() { 
        return id; 
    }
    public String getEdad() { 
        return edad;
     }
    public String getHoraCreacion() {
         return horaCreacion; 
        }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setHoraCreacion(String horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
    }

    public void setTipoTicket(String tipoTicket) {
        this.tipoTicket = tipoTicket;
    }

    public String getTramite() { 
        return tramite;
     }
    public String getTipoTicket() { 
        return tipoTicket; 
    }
    public long getHoraAtencion() {
         return horaAtencion;
         }
    public void setHoraAtencion(long horaAtencion) {
         this.horaAtencion = horaAtencion; 
        }
    
    
    
    
}
