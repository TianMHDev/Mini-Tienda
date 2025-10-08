/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.minitienda;

import javax.swing.JOptionPane;

public class InventarioApp {
    public static void main(String[] args) {
        String[] opciones = {
            "Agregar producto",
            "Listar inventario",
            "Comprar producto",
            "Mostrar estadísticas",
            "Buscar producto por nombre",
            "Salir"
        };
        
        boolean salir = false;
        
        while (!salir) {
            String opcion = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una opción",
                "Menú de Inventario",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );
            
            if (opcion == null) {
                salir = true; // Si cierra la ventana, sale
                continue;
            }

            switch (opcion) {
                case "Agregar producto":
                    JOptionPane.showMessageDialog(null, "Función para agregar producto");
                    break;
                case "Listar inventario":
                    JOptionPane.showMessageDialog(null, "Función para listar inventario");
                    break;
                case "Comprar producto":
                    JOptionPane.showMessageDialog(null, "Función para comprar producto");
                    break;
                case "Mostrar estadísticas":
                    JOptionPane.showMessageDialog(null, "Función para estadísticas");
                    break;
                case "Buscar producto por nombre":
                    JOptionPane.showMessageDialog(null, "Función para buscar producto");
                    break;
                case "Salir":
                    JOptionPane.showMessageDialog(null, "Saliendo... Ticket final");
                    salir = true;
                    break;
            }
        }
    }
}
