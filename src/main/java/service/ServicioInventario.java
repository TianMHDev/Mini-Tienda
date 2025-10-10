/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;
import model.Producto;

public interface ServicioInventario {
    void agregarProducto(Producto producto);
    List<Producto> obtenerInventario();
    void actualizarPrecio(int id, double nuevoPrecio);
    void actualizarStock(int id, int nuevoStock);
    void eliminarProducto(int id);
    List<Producto> buscarPorNombre(String nombre);
}
