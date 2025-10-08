/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;



// Subclase Electrodomestico
public class Electrodomestico extends Producto {
    public Electrodomestico(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public String getDescripcion() {
        return "Electrodoméstico: " + getNombre();
    }
}

