/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import javax.swing.JOptionPane;
import java.util.List;
import model.Producto;
import service.ServicioInventarioImpl;
import service.ServicioInventario;
import repository.ProductoRepositorioImpl;

public class MiniTiendaApp {

    public static void main(String[] args) {

        ServicioInventario servicio = new ServicioInventarioImpl(new ProductoRepositorioImpl());

        int opcion = 0;
        int altas = 0, bajas = 0, actualizaciones = 0;
        boolean continuar = true;

        while (continuar) {
            String menu = """
                    ===== MINI-TIENDA (JDBC + JOptionPane) =====
                    
                    1. Agregar producto
                    2. Listar inventario
                    3. Actualizar precio
                    4. Actualizar stock
                    5. Eliminar producto
                    6. Buscar producto por nombre
                    7. Salir
                    
                    Selecciona una opción:
                    """;

            try {
                String input = JOptionPane.showInputDialog(menu);
                if (input == null) break; // Si el usuario cancela
                opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1 -> {
                        String nombre = JOptionPane.showInputDialog("Nombre del producto:");
                        String precioStr = JOptionPane.showInputDialog("Precio del producto:");
                        String stockStr = JOptionPane.showInputDialog("Stock del producto:");

                        if (nombre == null || nombre.isEmpty() || precioStr == null || precioStr.isEmpty()
                                || stockStr == null || stockStr.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Campos vacíos. Intenta nuevamente.");
                            break;
                        }

                        double precio = Double.parseDouble(precioStr);
                        int stock = Integer.parseInt(stockStr);

                        Producto nuevo = new Producto(0, nombre, precio, stock);
                        servicio.agregarProducto(nuevo);
                        JOptionPane.showMessageDialog(null, "Producto agregado correctamente.");
                        altas++;
                    }

                    case 2 -> {
                        List<Producto> lista = servicio.obtenerInventario();
                        if (lista.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No hay productos registrados.");
                        } else {
                            StringBuilder sb = new StringBuilder("=== INVENTARIO ===\n");
                            for (Producto p : lista) {
                                sb.append(p.getId()).append(" | ")
                                  .append(p.getNombre()).append(" | $")
                                  .append(p.getPrecio()).append(" | Stock: ")
                                  .append(p.getStock()).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, sb.toString());
                        }
                    }

                    case 3 -> {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a actualizar precio:"));
                        double nuevoPrecio = Double.parseDouble(JOptionPane.showInputDialog("Nuevo precio:"));
                        servicio.actualizarPrecio(id, nuevoPrecio);
                        JOptionPane.showMessageDialog(null, "Precio actualizado correctamente.");
                        actualizaciones++;
                    }

                    case 4 -> {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a actualizar stock:"));
                        int nuevoStock = Integer.parseInt(JOptionPane.showInputDialog("Nuevo stock:"));
                        servicio.actualizarStock(id, nuevoStock);
                        JOptionPane.showMessageDialog(null, "Stock actualizado correctamente.");
                        actualizaciones++;
                    }

                    case 5 -> {
                        int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a eliminar:"));
                        servicio.eliminarProducto(id);
                        JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
                        bajas++;
                    }

                    case 6 -> {
                        String texto = JOptionPane.showInputDialog("Buscar producto por nombre:");
                        List<Producto> encontrados = servicio.buscarPorNombre(texto);
                        if (encontrados.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No se encontraron productos con ese nombre.");
                        } else {
                            StringBuilder sb = new StringBuilder("=== RESULTADOS ===\n");
                            for (Producto p : encontrados) {
                                sb.append(p.getId()).append(" | ")
                                  .append(p.getNombre()).append(" | $")
                                  .append(p.getPrecio()).append(" | Stock: ")
                                  .append(p.getStock()).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, sb.toString());
                        }
                    }

                    case 7 -> {
                        JOptionPane.showMessageDialog(null, """
                                ==== RESUMEN FINAL ====
                                Altas realizadas: """ + altas +
                                "\nActualizaciones: " + actualizaciones +
                                "\nBajas realizadas: " + bajas +
                                "\nGracias por usar la Mini-Tienda.");
                        continuar = false;
                    }

                    default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Usa solo números.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }
}
