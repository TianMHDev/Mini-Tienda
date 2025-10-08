/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeup.minitienda;
import javax.swing.JOptionPane;

public class InventarioApp {

    public static void main(String[] args) {
        MiniTienda tienda = new MiniTienda();
        double totalCompras = 0;

        String[] opciones = {
            "Agregar producto",
            "Listar inventario",
            "Comprar producto",
            "Mostrar estadísticas",
            "Buscar producto por nombre",
            "Salir"
        };

        boolean salir = false;

        while (!salir) {
            String opcion = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una opción",
                "Menú de Inventario",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );

            if (opcion == null) {
                salir = true;
                continue;
            }

            switch (opcion) {

                // 1. Agregar producto
                case "Agregar producto":
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
                    if (nombre == null || nombre.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nombre inválido.");
                        break;
                    }

                    try {
                        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio:"));
                        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el stock:"));

                        if (precio <= 0 || cantidad <= 0) {
                            JOptionPane.showMessageDialog(null, "El precio y el stock deben ser mayores que 0.");
                            break;
                        }

                        tienda.addProducto(nombre, precio, cantidad);
                        JOptionPane.showMessageDialog(null, "Producto agregado correctamente.");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Error: ingrese valores numéricos válidos.");
                    }
                    break;

                // 2. Listar inventario
                case "Listar inventario":
                    if (tienda.getNombres().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay productos en el inventario.");
                        break;
                    }

                    StringBuilder inventario = new StringBuilder("Inventario actual:\n\n");
                    for (int i = 0; i < tienda.getNombres().size(); i++) {
                        String prod = tienda.getNombres().get(i);
                        double precio = tienda.getPrecios()[i];
                        int cantidad = tienda.getStock().get(prod);

                        inventario.append(String.format("- %-15s | Precio: $%.2f | Stock: %d%n", prod, precio, cantidad));
                    }

                    JOptionPane.showMessageDialog(null, inventario.toString());
                    break;

                // 3. Comprar producto
                case "Comprar producto":
                    if (tienda.getNombres().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay productos disponibles para comprar.");
                        break;
                    }

                    String producto = JOptionPane.showInputDialog("Ingrese el nombre del producto a comprar:");
                    if (producto == null || producto.trim().isEmpty()) break;

                    int index = tienda.indexOfNombre(producto);

                    if (index == -1) {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                    } else {
                        try {
                            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántas unidades desea comprar?"));
                            int stockActual = tienda.getStock().get(producto);

                            if (cantidad <= 0) {
                                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que 0.");
                            } else if (cantidad > stockActual) {
                                JOptionPane.showMessageDialog(null, "Stock insuficiente. Disponible: " + stockActual);
                            } else {
                                double precio = tienda.getPrecios()[index];
                                double total = precio * cantidad;
                                totalCompras += total;

                                tienda.getStock().put(producto, stockActual - cantidad);
                                JOptionPane.showMessageDialog(null, String.format(
                                    "Compra realizada con éxito:\nProducto: %s\nCantidad: %d\nTotal: $%.2f",
                                    producto, cantidad, total
                                ));
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error: ingrese una cantidad válida.");
                        }
                    }
                    break;

                // 4. Mostrar estadísticas
                case "Mostrar estadísticas":
                    if (tienda.getPrecios().length == 0) {
                        JOptionPane.showMessageDialog(null, "No hay productos registrados.");
                        break;
                    }

                    double[] precios = tienda.getPrecios();
                    double min = precios[0], max = precios[0];
                    String prodMin = tienda.getNombres().get(0);
                    String prodMax = prodMin;

                    for (int i = 1; i < precios.length; i++) {
                        if (precios[i] < min) {
                            min = precios[i];
                            prodMin = tienda.getNombres().get(i);
                        }
                        if (precios[i] > max) {
                            max = precios[i];
                            prodMax = tienda.getNombres().get(i);
                        }
                    }

                    JOptionPane.showMessageDialog(null, String.format(
                        "Estadísticas de precios:\nProducto más barato: %s ($%.2f)\nProducto más caro: %s ($%.2f)",
                        prodMin, min, prodMax, max
                    ));
                    break;

                // 5. Buscar producto por nombre
                case "Buscar producto por nombre":
                    if (tienda.getNombres().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay productos para buscar.");
                        break;
                    }

                    String busqueda = JOptionPane.showInputDialog("Ingrese parte del nombre:");
                    if (busqueda == null || busqueda.trim().isEmpty()) break;

                    StringBuilder encontrados = new StringBuilder("Resultados de búsqueda:\n\n");
                    boolean hallado = false;

                    for (String prod : tienda.getNombres()) {
                        if (prod.toLowerCase().contains(busqueda.toLowerCase())) {
                            hallado = true;
                            double precio = tienda.getPrecios()[tienda.indexOfNombre(prod)];
                            int stock = tienda.getStock().get(prod);
                            encontrados.append(String.format("- %-15s | Precio: $%.2f | Stock: %d%n", prod, precio, stock));
                        }
                    }

                    if (!hallado)
                        JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
                    else
                        JOptionPane.showMessageDialog(null, encontrados.toString());
                    break;

                // 6. Salir
                case "Salir":
                    JOptionPane.showMessageDialog(null, String.format(
                        "Ticket final:\nTotal gastado: $%.2f\nGracias por su compra.",
                        totalCompras
                    ));
                    salir = true;
                    break;
            }
        }
    }
}
