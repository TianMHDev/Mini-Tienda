/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import javax.swing.JOptionPane;

public class MiniTiendaApp {

    public static void main(String[] args) {
        boolean salir = false;
        int altas = 0;
        int actualizaciones = 0;
        int bajas = 0;

        while (!salir) {
            String menu = """
                    === MINI TIENDA (JDBC + JOptionPane) ===
                    1. Agregar producto
                    2. Listar inventario
                    3. Actualizar precio
                    4. Actualizar stock
                    5. Eliminar producto
                    6. Buscar producto por nombre
                    7. Salir
                    """;

            String opcion = JOptionPane.showInputDialog(menu);

            if (opcion == null) { // Si el usuario cierra la ventana
                salir = true;
                continue;
            }

            try {
                int op = Integer.parseInt(opcion);

                switch (op) {
                    case 1 -> {
                        JOptionPane.showMessageDialog(null, "Opción: Agregar producto");
                        altas++;
                    }
                    case 2 -> JOptionPane.showMessageDialog(null, "Opción: Listar inventario");
                    case 3 -> {
                        JOptionPane.showMessageDialog(null, "Opción: Actualizar precio");
                        actualizaciones++;
                    }
                    case 4 -> {
                        JOptionPane.showMessageDialog(null, "Opción: Actualizar stock");
                        actualizaciones++;
                    }
                    case 5 -> {
                        JOptionPane.showMessageDialog(null, "Opción: Eliminar producto");
                        bajas++;
                    }
                    case 6 -> JOptionPane.showMessageDialog(null, "Opción: Buscar producto por nombre");
                    case 7 -> salir = true;
                    default -> JOptionPane.showMessageDialog(null, "Opción no válida. Intenta de nuevo.");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debes ingresar un número válido.");
            }
        }

        // Mostrar resumen final
        String resumen = """
                === RESUMEN DE OPERACIONES ===
                Altas: %d
                Actualizaciones: %d
                Bajas: %d
                Gracias por usar MiniTienda!
                """.formatted(altas, actualizaciones, bajas);

        JOptionPane.showMessageDialog(null, resumen);
    }
}
