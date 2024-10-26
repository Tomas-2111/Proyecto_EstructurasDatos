/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.banco_grupo5;

import javax.swing.JOptionPane;


/**
 *
 * @author Tomás Alfaro
 */

public class Banco_Grupo5 {

    public static void main(String[] args) {
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

        Cola cola = new Cola(); 

        while (true) {
            String opcion = JOptionPane.showInputDialog(
                    "Bienvenido\nFavor elija una opción:\n1. Ingresar Ticket\n2. Atender Tickets\n3. Salir");
            if (opcion == null || opcion.equals("3")) {
                break; 
            }

            switch (opcion) {
                case "1":
                    /*Aqui es para asignar el ticket */
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del ticket:");
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del ticket:"));
                    String edad = JOptionPane.showInputDialog("Ingrese la edad:");
                    String horaCreacion = JOptionPane.showInputDialog("Ingrese la hora de creación:");
                    String tramite = JOptionPane.showInputDialog("Ingrese el trámite:");
                    String tipoTicket = JOptionPane.showInputDialog("Ingrese el tipo de ticket:");

                    cola.agregarTicket(nombre, id, edad, horaCreacion, tramite, tipoTicket);
                    JOptionPane.showMessageDialog(null, "Ticket ingresado exitosamente.");
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
}
