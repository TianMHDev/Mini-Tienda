/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.minitienda;

public class Main {
    public static void main(String[] args) {
        MiniTienda inventario = new MiniTienda();

        inventario.addProducto("Manzana", 2.5, 30);
        inventario.addProducto("Arroz", 4.2, 20);
        inventario.addProducto("Leche", 3.8, 15);

        inventario.listarInventario();

        System.out.println("Índice de 'Arroz': " + inventario.indexOfNombre("Arroz"));
    }
}
