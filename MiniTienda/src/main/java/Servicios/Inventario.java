/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicios;
import Modelos.Producto;
import java.util.*;

public class Inventario {
    private ArrayList<Producto> productos = new ArrayList<>();
    private HashMap<String, Integer> stock = new HashMap<>();

    public ArrayList<Producto> getProductos() { return productos; }
    public HashMap<String, Integer> getStock() { return stock; }

    // Agregar producto
    public boolean agregarProducto(Producto producto, int cantidad) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(producto.getNombre())) {
                return false; // Ya existe
            }
        }
        productos.add(producto);
        stock.put(producto.getNombre(), cantidad);
        return true;
    }
}
