/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco_grupo5;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 *
 * @author Tomás Alfaro
 */
public class GestionColas {
    Cola cola = new Cola();
    Serializacion serializacion= new Serializacion();
    Cola colaNormall = serializacion.cargarCola("listaCola1.json")==null?new Cola():serializacion.cargarCola("listaCola1.json");
    Cola colaNormal2 =serializacion.cargarCola("listaCola2.json")==null?new Cola():serializacion.cargarCola("listaCola2.json");
    Cola colaNormal3 = serializacion.cargarCola("listaCola3.json")==null?new Cola():serializacion.cargarCola("listaCola3.json");
    Cola colaPreferencial = serializacion.cargarCola("listaPreferencial.json")==null?new Cola():serializacion.cargarCola("listaPreferencial.json");
    Cola colaTramiteRapido = serializacion.cargarCola("listaRapida.json")==null?new Cola():serializacion.cargarCola("listaRapida.json");
    
    
    public void ingresarTicket(){
        int cantidadCola1=colaNormall.contar();
        int cantidadCola2=colaNormal2.contar();
        int cantidadCola3=colaNormal3.contar();
        int cantidadPreferencial=colaPreferencial.contar();
        int cantidadRapida= colaTramiteRapido.contar();
        int numeroFila=0;
        String caja="";
        System.out.println("Cantidad: "+cantidadCola1);
        LocalDateTime locaDate = LocalDateTime.now();//Obtiene hora actual
        int hours  = locaDate.getHour();
        int minutes = locaDate.getMinute();
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la persona:");
        int id = cantidadCola1+cantidadCola2+cantidadCola3+cantidadPreferencial+cantidadRapida+1;
        String edad = JOptionPane.showInputDialog("Ingrese la edad:");
        String horaCreacion = hours+":"+minutes;
        String tramite = JOptionPane.showInputDialog("Ingrese el trámite:\nD: Depósitos\nR: Retiros\nC: Cambio de Divisa");
        String tipoTicket = JOptionPane.showInputDialog("Ingrese el tipo de ticket:\nP: Preferencial\nA: Un solo trámite\nB: Dos o más trámites");


        Ticket nuevoTicket = new Ticket(nombre, id, edad, horaCreacion, tramite, tipoTicket);
        nuevoTicket.setHoraAtencion(-1);
        switch (tipoTicket.toLowerCase() ) {
            case "p":
                colaPreferencial.agregarTicket(nuevoTicket);
                serializacion.guardarCola(colaPreferencial, "listaPreferencial.json");
                numeroFila=cantidadPreferencial+1;
                caja="Caja Preferencial";
                break;
            case "a":
                colaTramiteRapido.agregarTicket(nuevoTicket);
                serializacion.guardarCola(colaTramiteRapido, "listaRapida.json");
                numeroFila=cantidadRapida+1;
                caja="Caja Rápida";
                break;
            case "b":
            default:
                if(cantidadCola1<=cantidadCola2 && cantidadCola1<=cantidadCola3){
                    colaNormall.agregarTicket(nuevoTicket);
                    serializacion.guardarCola(colaNormall, "listaCola1.json");
                    numeroFila=cantidadCola1+1;
                    caja="Caja 1";
                    break;
                    
                }else  if(cantidadCola2<=cantidadCola1 && cantidadCola2<=cantidadCola3){
                    colaNormal2.agregarTicket(nuevoTicket);
                    serializacion.guardarCola(colaNormal2, "listaCola2.json");
                    numeroFila=cantidadCola2+1;
                    caja="Caja 2";
                    break;
                }else if(cantidadCola3<=cantidadCola1 && cantidadCola3<=cantidadCola2){
                    colaNormal3.agregarTicket(nuevoTicket);
                    serializacion.guardarCola(colaNormal3, "listaCola3.json");
                    numeroFila=cantidadCola3+1;
                    caja="Caja 3";
                    break;
                }else{
                    colaNormall.agregarTicket(nuevoTicket);
                    numeroFila=cantidadCola1+1;
                    caja="Caja 1";
                    break;
                }
               
        }
         /*Aqui muestro la info del ticket */
         JOptionPane.showMessageDialog(null, "Ticket ingresado exitosamente:\n" +
         "Nombre: " + nombre + "\nID: " + id + "\nEdad: " + edad + "\nCaja: "+caja+
         "\nHora de Creación: " + horaCreacion+ "\nNúmero atención: "+ numeroFila + "\nTrámite: " + tramite +
         "\nTipo de Ticket: " + tipoTicket +
         "\nCola: " + (tipoTicket.equalsIgnoreCase("P") ? "Preferencial" : 
                       tipoTicket.equalsIgnoreCase("A") ? "Trámite Rápido" : "Dos o más tramites"));


    }
    public void atender() {
        // Revisar y mostrar los tickets guardados
        serializacion.revisarTicketsGuardados();
    
        String ticketsDisponibles = "Tickets disponibles para atender:\n";
        int contador = 1;
        Cola[] colas = {colaPreferencial, colaTramiteRapido, colaNormall, colaNormal2, colaNormal3};
    
        // Mostrar tickets de todas las colas
        for (Cola cola : colas) {
            Nodo nodo = cola.getFrente();
            while (nodo != null) {
                Ticket ticket = nodo.getTicket();
                ticketsDisponibles += contador + ". Nombre: " + ticket.getNombre() +
                        ", ID: " + ticket.getId() + ", Trámite: " + ticket.getTramite() + "\n";
                nodo = nodo.getAtras();
                contador++;
            }
        }
    
        // Si no hay tickets disponibles
        if (contador == 1) {
            JOptionPane.showMessageDialog(null, "No hay tickets en la cola.");
            return;
        }
    
        // Preguntar al usuario qué ticket desea atender
        String seleccion = JOptionPane.showInputDialog(ticketsDisponibles + "Seleccione el número del ticket a atender:");
    
        // Validar la selección
        try {
            int index = Integer.parseInt(seleccion);
            if (index < 1 || index >= contador) {
                JOptionPane.showMessageDialog(null, "Selección no válida.");
                return;
            }
    
            // Atender el ticket seleccionado
            Ticket ticketAtender = null;
            int actualIndex = 1;
    
            for (Cola cola : colas) {
                Nodo nodo = cola.getFrente();
                while (nodo != null) {
                    if (actualIndex == index) {
                        ticketAtender = nodo.getTicket();
                        cola.atender(); // Atender el ticket
                        serializacion.guardarCola(cola, "lista" + (cola == colaPreferencial ? "Preferencial" : 
                                                                      cola == colaTramiteRapido ? "Rapida" : 
                                                                      cola == colaNormall ? "Cola1" : 
                                                                      cola == colaNormal2 ? "Cola2" : "Cola3") + ".json");
                        break;
                    }
                    nodo = nodo.getAtras();
                    actualIndex++;
                }
                if (ticketAtender != null) break; // Salir si se atendió el ticket
            }
    
            if (ticketAtender != null) {
                JOptionPane.showMessageDialog(null, "Atendiendo Ticket:\nNombre: " + ticketAtender.getNombre() +
                        "\nID: " + ticketAtender.getId() + "\nTrámite: " + ticketAtender.getTramite());
            }
    
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Selección no válida.");
        }
    }
    public void mostrarTicketsGuardados() {
        serializacion.revisarTicketsGuardados();
    }
    public void cargarCola(Cola cola,String archivo){
        
    }
    
}
