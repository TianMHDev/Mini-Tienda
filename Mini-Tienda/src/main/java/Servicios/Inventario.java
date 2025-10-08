/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;
import Modelos.Producto;
import java.util.*;
import javax.swing.JOptionPane;

public class Inventario {
    private ArrayList<Producto> productos = new ArrayList<>();
    private HashMap<String, Integer> stock = new HashMap<>();
    private double totalCompras = 0;

    // Agregar producto con validaciones
    public boolean agregarProducto(Producto producto, int cantidad) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(producto.getNombre())) {
                JOptionPane.showMessageDialog(null, "⚠️ El producto ya existe.");
                return false;
            }
        }
        productos.add(producto);
        stock.put(producto.getNombre(), cantidad);
        JOptionPane.showMessageDialog(null, "✅ Producto agregado correctamente.");
        return true;
    }

    // Listar inventario completo
    public void listarInventario() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Inventario vacío.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== Inventario Actual ===\n");
        for (Producto p : productos) {
            sb.append("• " + p.getDescripcion() +
                      "\nPrecio: $" + p.getPrecio() +
                      "\nStock: " + stock.get(p.getNombre()) + "\n\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Comprar producto
    public void comprarProducto() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos disponibles.");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto a comprar:");
        if (nombre == null || nombre.isEmpty()) return;

        Producto encontrado = null;
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                encontrado = p;
                break;
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(null, "❌ Producto no encontrado.");
            return;
        }

        try {
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad a comprar:"));
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.");
                return;
            }

            int stockActual = stock.get(encontrado.getNombre());
            if (cantidad > stockActual) {
                JOptionPane.showMessageDialog(null, "No hay suficiente stock disponible.");
                return;
            }

            // Actualizar stock y total
            stock.put(encontrado.getNombre(), stockActual - cantidad);
            double subtotal = cantidad * encontrado.getPrecio();
            totalCompras += subtotal;

            JOptionPane.showMessageDialog(null,
                "Compra realizada ✅\n" +
                "Producto: " + encontrado.getNombre() +
                "\nCantidad: " + cantidad +
                "\nSubtotal: $" + subtotal);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
        }
    }

    // Buscar productos por coincidencia parcial
    public void buscarProducto() {
        String texto = JOptionPane.showInputDialog("Ingrese texto a buscar:");
        if (texto == null || texto.isEmpty()) return;

        StringBuilder sb = new StringBuilder("Resultados:\n");
        boolean encontrado = false;

        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                sb.append("• " + p.getDescripcion() +
                          " | $" + p.getPrecio() +
                          " | Stock: " + stock.get(p.getNombre()) + "\n");
                encontrado = true;
            }
        }

        if (encontrado)
            JOptionPane.showMessageDialog(null, sb.toString());
        else
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
    }

    // Mostrar estadísticas: producto más caro y más barato
    public void mostrarEstadisticas() {
        if (productos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos en el inventario.");
            return;
        }

        Producto caro = productos.get(0);
        Producto barato = productos.get(0);

        for (Producto p : productos) {
            if (p.getPrecio() > caro.getPrecio()) caro = p;
            if (p.getPrecio() < barato.getPrecio()) barato = p;
        }

        JOptionPane.showMessageDialog(null,
            "📊 Estadísticas:\n" +
            "Producto más caro: " + caro.getNombre() + " ($" + caro.getPrecio() + ")\n" +
            "Producto más barato: " + barato.getNombre() + " ($" + barato.getPrecio() + ")");
    }

    // Mostrar ticket final de compras
    public void mostrarTicketFinal() {
        JOptionPane.showMessageDialog(null,
            "💰 Total de compras realizadas: $" + totalCompras +
            "\nGracias por usar el sistema de inventario.");
    }
}
