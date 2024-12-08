package com.mycompany.banco_grupo5;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Configuracion {

    private String nombreBanco;
    private int cantidadCajas;

    public String getNombreBanco() {
        return nombreBanco;
    }

    public int getCantidadCajas() {
        return cantidadCajas;
    }

    public void cargarConfiguracion() {
        if (!archivoConfiguracionExiste()) {
            configurarPrograma();
        } else {
            leerConfiguracion();
        }
    }

    private boolean archivoConfiguracionExiste() {
        return new java.io.File("prod.txt").exists();
    }

    public void configurarPrograma() {
        nombreBanco = JOptionPane.showInputDialog("Nombre del banco:");
        cantidadCajas = Integer.parseInt(JOptionPane.showInputDialog("Número de cajas (mínimo 3):"));
        while (cantidadCajas < 3) {
            JOptionPane.showMessageDialog(null, "La cantidad de cajas debe ser mayor o igual a 3");
            cantidadCajas = Integer
                    .parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de cajas (mínimo 3 cajas): "));
        }
        guardarConfiguracion();
    }

    private void guardarConfiguracion() {
        try (DataOutputStream archivo = new DataOutputStream(new FileOutputStream("prod.txt"))) {
            archivo.writeUTF(nombreBanco);
            archivo.writeInt(cantidadCajas);
            System.out.println("Configuración guardada: " + nombreBanco + ", " + cantidadCajas);
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "Error al guardar la configuración: " + error.getMessage(),
                    "Error al guardar", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void leerConfiguracion() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("prod.txt"))) {
            nombreBanco = dis.readUTF();
            cantidadCajas = dis.readInt();
            System.out.println("Loaded: " + nombreBanco + ", " + cantidadCajas);
            JOptionPane.showMessageDialog(null,
                    "Configuración cargada:\nBanco: " + nombreBanco + "\nCajas: " + cantidadCajas);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer la configuración: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
}
