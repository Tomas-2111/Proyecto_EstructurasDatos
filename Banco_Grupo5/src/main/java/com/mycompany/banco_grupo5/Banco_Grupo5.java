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
    static GestionColas gestion= new GestionColas();

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
                    gestion.ingresarTicket();
                    break;

                case "2":

//                    if (cola.getFrente() != null) {
//                        Ticket ticketAtender = cola.getFrente().getTicket();
//                        JOptionPane.showMessageDialog(null, "Atendiendo Ticket:\nNombre: " + ticketAtender.getNombre()
//                                + "\nID: " + ticketAtender.getId());
//
//                    } else {
//                        JOptionPane.showMessageDialog(null, "No hay tickets en la cola.");
//                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida, intente de nuevo.");
                    break;
            }
        }

        JOptionPane.showMessageDialog(null, "Gracias por utilizar el sistema.");
    }
    
    
    

}

