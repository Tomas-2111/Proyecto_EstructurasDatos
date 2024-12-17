/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco_grupo5;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 *
 * @author Tomás Alfaro
 */
public class Login {
    
    private String user1="admin";
    private String user2="user";
    private String pass1="admin123";
    private String pass2="user123";
    
    
    public int revisarLogin(String user, String pass){//Revisa el login y retorna el tipo de usuario o 0 si es incorrecto
        int tipo=0;
        if(user.equals(user1) && pass.equals(pass1))
        {
            tipo=1;
            
        }else if(user.equals(user2) && pass.equals(pass2))
        {
            tipo=2;
        }
        ingresarBitacora(tipo,user);
        return tipo;
    }
    
    
    private void ingresarBitacora(int tipo, String user)//Si el ingreso fue correcto lo guarda en bitacora
    {
        LocalDateTime locaDate = LocalDateTime.now();//Obtiene hora actual
        int hours  = locaDate.getHour();
        int minutes = locaDate.getMinute();
        try{
        //aquí creamos el archivo y la entrada de datos
            DataOutputStream archivo= new DataOutputStream(new FileOutputStream("bitacora.txt", true));//El nombre del archivo que se va a crear cada que se agrega; El true concatena los datos           
            if(tipo!=0)
            {
                //Si los datos están correctos los escribimos en el archivo
                archivo.writeUTF("***Inicio de sesion exitoso el "+locaDate);//UTF es formato de texto enriquecido tipo Word
                //archivo.writeUTF("Hora: "+hours+":"+minutes);
                archivo.writeUTF("Usuario: "+user+"***");
                //Debe cerrar ela rchivo una vez se deje utilizar
                archivo.close();   
            }
            

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al agregar datos: "+e.getMessage(), "Error al agregar",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
}
