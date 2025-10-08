/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.codeup.minitienda;
import java.util.ArrayList;
import java.util.HashMap;

public class MiniTienda {

    // Estructuras de datos principales
    private ArrayList<String> nombres;     
    private double[] precios;              
    private HashMap<String, Integer> stock; 

    // Constructor
    public MiniTienda() {
        nombres = new ArrayList<>();
        precios = new double[0];
        stock = new HashMap<>();
    }

    // Método para agregar un nuevo producto
    public void addProducto(String nombre, double precio, int cantidad) {
        if (nombres.contains(nombre)) {
            System.out.println("El producto ya existe en el inventario.");
            return;
        }
        nombres.add(nombre);
        precios = expandPrecios(precios, precio);
        stock.put(nombre, cantidad);

        System.out.println("✅ Producto agregado correctamente: " + nombre);
    }

    // Método utilitario para expandir el array de precios
    private double[] expandPrecios(double[] preciosOriginal, double nuevoPrecio) {
        double[] nuevoArray = new double[preciosOriginal.length + 1];

        for (int i = 0; i < preciosOriginal.length; i++) {
            nuevoArray[i] = preciosOriginal[i];
        }
        nuevoArray[nuevoArray.length - 1] = nuevoPrecio;

        return nuevoArray;
    }

    public int indexOfNombre(String nombre) {
        return nombres.indexOf(nombre);
    }

    // Método para listar el inventario 
    public void listarInventario() {
        System.out.println("Inventario actual:");
        for (int i = 0; i < nombres.size(); i++) {
            String nombre = nombres.get(i);
            double precio = precios[i];
            int cantidad = stock.get(nombre);
            System.out.println("- " + nombre + " | Precio: $" + precio + " | Stock: " + cantidad);
        }
    }
}
