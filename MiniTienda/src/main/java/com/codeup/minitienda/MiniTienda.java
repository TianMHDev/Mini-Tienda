/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.minitienda;

import java.util.ArrayList;
import java.util.HashMap;

public class MiniTienda {
    private ArrayList<String> nombres;
    private double[] precios;
    private HashMap<String, Integer> stock;

    public MiniTienda () {
        nombres = new ArrayList<>();
        precios = new double[0];
        stock = new HashMap<>();
    }

    public void addProducto(String nombre, double precio, int cantidad) {
        if (nombres.contains(nombre)) {
            System.out.println("El producto ya existe.");
            return;
        }

        nombres.add(nombre);
        precios = expandPrecios(precios, precio);
        stock.put(nombre, cantidad);
    }

    private double[] expandPrecios(double[] oldArray, double nuevoPrecio) {
        double[] nuevo = new double[oldArray.length + 1];
        for (int i = 0; i < oldArray.length; i++) {
            nuevo[i] = oldArray[i];
        }
        nuevo[nuevo.length - 1] = nuevoPrecio;
        return nuevo;
    }

    public int indexOfNombre(String nombre) {
        return nombres.indexOf(nombre);
    }

    public ArrayList<String> getNombres() {
        return nombres;
    }

    public double[] getPrecios() {
        return precios;
    }

    public HashMap<String, Integer> getStock() {
        return stock;
    }
}
