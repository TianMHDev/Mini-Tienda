/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventario;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Inventario inv = new Inventario();
        boolean salir = false;

        while (!salir) {
            String opcion = JOptionPane.showInputDialog(
                "=== MENÚ INVENTARIO ===\n" +
                "1. Agregar producto\n" +
                "2. Listar inventario\n" +
                "3. Comprar producto\n" +
                "4. Estadísticas\n" +
                "5. Buscar producto\n" +
                "6. Salir"
            );

            try {
                switch (opcion) {
                    case "1": agregarProducto(inv); break;
                    case "2": listar(inv); break;
                    case "3": comprar(inv); break;
                    case "4": estadisticas(inv); break;
                    case "5": buscar(inv); break;
                    case "6": salir = true; break;
                    default: JOptionPane.showMessageDialog(null, "Opción inválida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: ingrese un número válido.");
            }
        }