/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import javax.swing.JOptionPane;
import java.util.List;
import model.Producto;
import repository.ProductoRepositorioImpl;
import service.ServicioInventarioImpl;

public class MiniTiendaApp {

    public static void main(String[] args) {

        ServicioInventarioImpl servicio = new ServicioInventarioImpl(new ProductoRepositorioImpl());
        boolean salir = false;

        while (!salir) {
            try {
                String opcion = JOptionPane.showInputDialog("""
                        === MENÚ INVENTARIO ===
                        1. Agregar producto
                        2. Listar productos
                        3. Buscar producto por nombre
                        4. Actualizar precio
                        5. Actualizar stock
                        6. Eliminar producto
                        7. Salir
                        Selecciona una opción:
                        """);

                if (opcion == null) break; // Si cancela el diálogo

                switch (opcion) {
                    case "1" -> {
                        String nombre = JOptionPane.showInputDialog("Nombre del producto:");
                        if (nombre == null || nombre.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "❌ El nombre no puede estar vacío.");
                            continue;
                        }

                        double precio;
                        int stock;
                        try {
                            precio = Double.parseDouble(JOptionPane.showInputDialog("Precio del producto:"));
                            stock = Integer.parseInt(JOptionPane.showInputDialog("Stock del producto:"));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "❌ Ingresa valores numéricos válidos.");
                            continue;
                        }

                        Producto nuevo = new Producto(0, nombre, precio, stock);
                        servicio.agregarProducto(nuevo);
                        JOptionPane.showMessageDialog(null, "✅ Producto agregado correctamente.");
                    }

                    case "2" -> {
                        List<Producto> lista = servicio.obtenerInventario();
                        if (lista.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "⚠️ No hay productos en el inventario.");
                        } else {
                            StringBuilder sb = new StringBuilder("=== LISTA DE PRODUCTOS ===\n");
                            for (Producto p : lista) {
                                sb.append(p).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, sb.toString());
                        }
                    }

                    case "3" -> {
                        String nombreBuscar = JOptionPane.showInputDialog("Ingrese nombre o parte del nombre:");
                        if (nombreBuscar == null || nombreBuscar.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "❌ El campo no puede estar vacío.");
                            continue;
                        }

                        List<Producto> encontrados = servicio.buscarPorNombre(nombreBuscar);
                        if (encontrados.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "❌ No se encontraron productos con ese nombre.");
                        } else {
                            StringBuilder sb = new StringBuilder("=== RESULTADOS ===\n");
                            for (Producto p : encontrados) {
                                sb.append(p).append("\n");
                            }
                            JOptionPane.showMessageDialog(null, sb.toString());
                        }
                    }

                    case "4" -> {
                        try {
                            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a actualizar precio:"));
                            double nuevoPrecio = Double.parseDouble(JOptionPane.showInputDialog("Nuevo precio:"));

                            int confirm = JOptionPane.showConfirmDialog(null, "¿Confirmar actualización de precio?");
                            if (confirm == JOptionPane.YES_OPTION) {
                                servicio.actualizarPrecio(id, nuevoPrecio);
                                JOptionPane.showMessageDialog(null, "✅ Precio actualizado correctamente.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "❌ Ingrese valores numéricos válidos.");
                        }
                    }

                    case "5" -> {
                        try {
                            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a actualizar stock:"));
                            int nuevoStock = Integer.parseInt(JOptionPane.showInputDialog("Nuevo stock:"));

                            int confirm = JOptionPane.showConfirmDialog(null, "¿Confirmar actualización de stock?");
                            if (confirm == JOptionPane.YES_OPTION) {
                                servicio.actualizarStock(id, nuevoStock);
                                JOptionPane.showMessageDialog(null, "✅ Stock actualizado correctamente.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "❌ Ingrese valores numéricos válidos.");
                        }
                    }

                    case "6" -> {
                        try {
                            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a eliminar:"));
                            int confirm = JOptionPane.showConfirmDialog(null, "⚠️ ¿Seguro que deseas eliminar el producto?");
                            if (confirm == JOptionPane.YES_OPTION) {
                                servicio.eliminarProducto(id);
                                JOptionPane.showMessageDialog(null, "🗑️ Producto eliminado correctamente.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "❌ Ingresa un ID válido.");
                        }
                    }

                    case "7" -> {
                        salir = true;
                        JOptionPane.showMessageDialog(null, "👋 Saliendo del sistema...");
                    }

                    default -> JOptionPane.showMessageDialog(null, "❌ Opción no válida.");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "⚠️ Error inesperado: " + e.getMessage());
            }
        }
    }
}
