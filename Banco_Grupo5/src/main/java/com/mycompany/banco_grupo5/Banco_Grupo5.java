/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.banco_grupo5;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 *
 * @author Tomás Alfaro
 */

public class Banco_Grupo5 {
    static Cola cola = new Cola();
    static Cola colaNormall = new Cola();
    static Cola colaNormal2 = new Cola();
    static Cola colaNormal3 = new Cola();
    static Cola colaPreferencial = new Cola();
    static Cola colaTramiteRapido = new Cola();

    public static void main(String[] args) {
                
        /* Banco
        3 cajas para clientes
        1 caja preferencial
        1 caja rapida
        
        Trámites: Depósitos, Retiros, Cambio de Divisas
        Tipos: P: preferencial, A: un solo tramite, B: dos o más tramites
        
        */
        Login login = new Login();
        String user = "";
        String pass = "";

        int tipo = 0;

        while (tipo == 0) {
            JOptionPane.showMessageDialog(null, "Bienvenido\nFavor ingrese el usuario y contraseña");
            user = JOptionPane.showInputDialog("Ingrese el usuario");
            pass = JOptionPane.showInputDialog("Ingrese la contraseña");
            tipo = login.revisarLogin(user, pass);
            if (tipo == 0) {
                JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos favor intente de nuevo");
            }
        }


        Configuracion conf = new Configuracion();
        conf.cargarConfiguracion();
        //conf.configurarPrograma();
        //conf.leerConfiguracion();

        while (true) {
            String opcion = JOptionPane.showInputDialog(
                    "Bienvenido\nFavor elija una opción:\n1. Ingresar Ticket\n2. Atender Tickets\n3. Salir");
            if (opcion == null || opcion.equals("3")) {
                break;
            }

            switch (opcion) {
                case "1":
                    /* Aqui es para asignar el ticket */
                    ingresarTicket();
                    break;

                case "2":

                    if (cola.getFrente() != null) {
                        Ticket ticketAtender = cola.getFrente().getTicket();
                        JOptionPane.showMessageDialog(null, "Atendiendo Ticket:\nNombre: " + ticketAtender.getNombre()
                                + "\nID: " + ticketAtender.getId());

                    } else {
                        JOptionPane.showMessageDialog(null, "No hay tickets en la cola.");
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida, intente de nuevo.");
                    break;
            }
        }

        JOptionPane.showMessageDialog(null, "Gracias por utilizar el sistema.");
    }
    
    
    public static void ingresarTicket(){
        int cantidadCola1=colaNormall.contar();
        int cantidadCola2=colaNormal2.contar();
        int cantidadCola3=colaNormal3.contar();
        int numeroFila=0;
        String caja="";
        System.out.println("Cantidad: "+cantidadCola1);
        LocalDateTime locaDate = LocalDateTime.now();//Obtiene hora actual
        int hours  = locaDate.getHour();
        int minutes = locaDate.getMinute();
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del ticket:");
        int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del ticket:"));
        String edad = JOptionPane.showInputDialog("Ingrese la edad:");
        String horaCreacion = hours+":"+minutes;
        String tramite = JOptionPane.showInputDialog("Ingrese el trámite:\nD: Depósitos\nR: Retiros\nC: Cambio de Divisa");
        String tipoTicket = JOptionPane.showInputDialog("Ingrese el tipo de ticket:\nP: Preferencial\nA: Un solo trámite\nB: Dos o más trámites");


        Ticket nuevoTicket = new Ticket(nombre, id, edad, horaCreacion, tramite, tipoTicket);
        nuevoTicket.setHoraAtencion(-1);
        switch (tipoTicket.toLowerCase() ) {
            case "P":
                colaPreferencial.agregarTicket(nuevoTicket);
                break;
            case "A":
                colaTramiteRapido.agregarTicket(nuevoTicket);
                break;
            case "B":
            default:
                if(cantidadCola1<=cantidadCola2 && cantidadCola1<=cantidadCola3){
                    colaNormall.agregarTicket(nuevoTicket);
                    numeroFila=cantidadCola1+1;
                    caja="Caja 1";
                    break;
                    
                }else  if(cantidadCola2<=cantidadCola1 && cantidadCola2<=cantidadCola3){
                    colaNormal2.agregarTicket(nuevoTicket);
                    numeroFila=cantidadCola2+1;
                    caja="Caja 2";
                    break;
                }else if(cantidadCola3<=cantidadCola1 && cantidadCola3<=cantidadCola2){
                    colaNormal3.agregarTicket(nuevoTicket);
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

}

