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
        Login login=new Login();
        String user="";
        String pass="";
        
        int tipo=0;
        while(tipo==0){
            JOptionPane.showMessageDialog(null, "Bienvenido\nFavor ingrese el usuario y contraseña");
            user=JOptionPane.showInputDialog("Ingrese el usuario");
            pass=JOptionPane.showInputDialog("Ingrese la contraseña");
            tipo=login.revisarLogin(user, pass);
            if(tipo==0){
                JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos favor intente de nuevo");
            }
        }
        
        
        JOptionPane.showMessageDialog(null, "Bienvenido\nFavor elija una opcion\n1. Ingresar Ticket\n2. Atender Tickets");
    }
}
