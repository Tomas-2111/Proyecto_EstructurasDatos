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
    Serializacion serializacion= new Serializacion();
    Cola colaAtendidos= serializacion.cargarCola("listaAtendidos.json")==null?new Cola():serializacion.cargarCola("listaAtendidos.json");
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
        //nuevoTicket.setHoraAtencion(-1);
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
    
    
    public void atenderTicket(int cola){
        LocalDateTime localDate = LocalDateTime.now();//Obtiene hora actual
        Nodo ticketAtendido=null;
        switch(cola){
            case 1:
                ticketAtendido=colaNormall.atender();
                serializacion.guardarCola(colaNormall, "listaCola1.json");
                break;
            case 2:
                ticketAtendido=colaNormal2.atender();
                serializacion.guardarCola(colaNormal2, "listaCola2.json");
                break;
            case 3:
                ticketAtendido=colaNormal3.atender();
                serializacion.guardarCola(colaNormal2, "listaCola3.json");
                break;
            case 4:
                ticketAtendido=colaPreferencial.atender();
                serializacion.guardarCola(colaPreferencial, "listaPreferencial.json");
                break;
            case 5:
                ticketAtendido=colaTramiteRapido.atender();
                serializacion.guardarCola(colaTramiteRapido, "listaRapida.json");
                break;
        }
        ticketAtendido.getTicket().setHoraAtencion(String.valueOf(localDate));
        colaAtendidos.agregarTicket(ticketAtendido.getTicket());
        JOptionPane.showMessageDialog(null, "Atendiendo ticket\n"+ticketAtendido.getTicket().getId()
        +"\nTrámite: "+ticketAtendido.getTicket().getTramite());
        serializacion.guardarCola(colaAtendidos, "listaAtendidos.json");
        tramite(ticketAtendido.getTicket().getTramite());
        
    }
    
    public void tramite(String tramite){
        float monto=0;
        switch(tramite.toUpperCase()){
            case "D":
                monto=Float.valueOf(JOptionPane.showInputDialog("Ingrese el monto a depositar"));
                JOptionPane.showMessageDialog(null, "Monto depositado : ₡"+monto);
                System.out.println("Deposito");
                break;
            case "R":
                monto=Float.valueOf(JOptionPane.showInputDialog("Ingrese el monto a retirar"));
                JOptionPane.showMessageDialog(null, "Monto depositado : ₡"+monto);
                //Retiro
                System.out.println("Retiro");
                break;
            case "C":
                //Cambio divisas
                int opcion=Integer.valueOf(JOptionPane.showInputDialog("Ingrese la opcion deseada:\n1. Colones a dolares\n2. Dolares a colones"));
                if(opcion==1){
                    monto=Float.valueOf(JOptionPane.showInputDialog("Ingrese el monto en colones a cambiar:"));
                    
                    //Conectar servicio web
                }else if(opcion==2){
                    monto=Float.valueOf(JOptionPane.showInputDialog("Ingrese el monto en dolares a cambiar:"));
                     //Conectar servicio web
                }
                System.out.println("Cambio");
                break;
        }
    }

}
