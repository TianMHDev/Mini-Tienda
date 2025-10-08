/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventario;
import Modelos.Alimento;
import Modelos.Electrodomestico;
import Modelos.Producto;
import Servicios.Inventario;
import javax.swing.JOptionPane;

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
                "6. Salir\n\n" +
                "Seleccione una opción:"
            );

            if (opcion == null) break; // Si cancela el diálogo, salir

            switch (opcion) {
                case "1": agregarProducto(inv); break;
                case "2": inv.listarInventario(); break;
                case "3": inv.comprarProducto(); break;
                case "4": inv.mostrarEstadisticas(); break;
                case "5": inv.buscarProducto(); break;
                case "6":
                    inv.mostrarTicketFinal();
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    // Método auxiliar para agregar productos
    private static void agregarProducto(Inventario inv) {
        try {
            String tipo = JOptionPane.showInputDialog("Tipo de producto (Alimento/Electrodomestico):");
            if (tipo == null) return;

            String nombre = JOptionPane.showInputDialog("Nombre del producto:");
            if (nombre == null || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
                return;
            }

            double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio del producto:"));
            if (precio <= 0) {
                JOptionPane.showMessageDialog(null, "El precio debe ser mayor a 0.");
                return;
            }

            int stock = Integer.parseInt(JOptionPane.showInputDialog("Cantidad en stock:"));
            if (stock <= 0) {
                JOptionPane.showMessageDialog(null, "El stock debe ser mayor a 0.");
                return;
            }

            Producto nuevo;
            if (tipo.equalsIgnoreCase("Alimento")) {
                nuevo = new Alimento(nombre, precio);
            } else if (tipo.equalsIgnoreCase("Electrodomestico")) {
                nuevo = new Electrodomestico(nombre, precio);
            } else {
                JOptionPane.showMessageDialog(null, "Tipo no válido.");
                return;
            }

            inv.agregarProducto(nuevo, stock);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
        }
    }
}
