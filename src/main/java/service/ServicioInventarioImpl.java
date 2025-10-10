/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package service;

import java.util.List;
import javax.swing.JOptionPane;
import model.Producto;
import repository.ProductoRepositorioImpl;

public class ServicioInventarioImpl implements ServicioInventario {

    private final ProductoRepositorioImpl repo;

    public ServicioInventarioImpl(ProductoRepositorioImpl repo) {
        this.repo = repo;
    }

    @Override
    public void agregarProducto(Producto producto) {
        if (producto == null || producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del producto no puede estar vacío.");
            return;
        }
        if (producto.getPrecio() < 0) {
            JOptionPane.showMessageDialog(null, "Precio inválido.");
            return;
        }
        if (producto.getStock() < 0) {
            JOptionPane.showMessageDialog(null, "Stock inválido.");
            return;
        }
        repo.crear(producto);
    }

    @Override
    public List<Producto> obtenerInventario() {
        return repo.buscarTodos();
    }

    @Override
    public void actualizarPrecio(int id, double nuevoPrecio) {
        repo.actualizarPrecio(id, nuevoPrecio);
    }

    @Override
    public void actualizarStock(int id, int nuevoStock) {
        repo.actualizarStock(id, nuevoStock);
    }

    @Override
    public void eliminarProducto(int id) {
        repo.eliminar(id);
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        return (List<Producto>) repo.buscarPorNombre(nombre);
    }
}
