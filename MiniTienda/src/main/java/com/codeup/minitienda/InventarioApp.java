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

                case "Agregar producto":
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto:");
                    if (nombre == null || nombre.isEmpty()) break;

                    try {
                        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio:"));
                        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el stock:"));
                        tienda.addProducto(nombre, precio, cantidad);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error: ingrese datos válidos.");
                    }
                    break;

                case "Listar inventario":
                    StringBuilder inventario = new StringBuilder("Inventario:\n");
                    for (int i = 0; i < tienda.getNombres().size(); i++) {
                        String prod = tienda.getNombres().get(i);
                        double precio = tienda.getPrecios()[i];
                        int cantidad = tienda.getStock().get(prod);
                        inventario.append("- ").append(prod)
                                  .append(" | Precio: $").append(precio)
                                  .append(" | Stock: ").append(cantidad)
                                  .append("\n");
                    }
                    JOptionPane.showMessageDialog(null, inventario.toString());
                    break;

                case "Comprar producto":
                    String producto = JOptionPane.showInputDialog("Ingrese el nombre del producto a comprar:");
                    if (producto == null) break;
                    int index = tienda.indexOfNombre(producto);

                    if (index == -1) {
                        JOptionPane.showMessageDialog(null, " Producto no encontrado.");
                    } else {
                        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántas unidades desea comprar?"));
                        int stockActual = tienda.getStock().get(producto);

                        if (cantidad > stockActual) {
                            JOptionPane.showMessageDialog(null, "No hay suficiente stock.");
                        } else {
                            double precio = tienda.getPrecios()[index];
                            double total = precio * cantidad;
                            totalCompras += total;
                            tienda.getStock().put(producto, stockActual - cantidad);
                            JOptionPane.showMessageDialog(null, "Compra realizada. Total: $" + total);
                        }
                    }
                    break;

                case "Mostrar estadísticas":
                    double[] precios = tienda.getPrecios();
                    if (precios.length == 0) {
                        JOptionPane.showMessageDialog(null, "No hay productos en el inventario.");
                        break;
                    }

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

                    JOptionPane.showMessageDialog(null,
                        "Producto más barato: " + prodMin + " ($" + min + ")\n" +
                        "Producto más caro: " + prodMax + " ($" + max + ")");
                    break;

                case "Buscar producto por nombre":
                    String busqueda = JOptionPane.showInputDialog("Ingrese parte del nombre:");
                    if (busqueda == null || busqueda.isEmpty()) break;

                    StringBuilder encontrados = new StringBuilder("Resultados:\n");
                    for (String prod : tienda.getNombres()) {
                        if (prod.toLowerCase().contains(busqueda.toLowerCase())) {
                            encontrados.append("- ").append(prod)
                                       .append(" | Precio: $").append(tienda.getPrecios()[tienda.indexOfNombre(prod)])
                                       .append("\n");
                        }
                    }
                    JOptionPane.showMessageDialog(null, encontrados.toString());
                    break;

                case "Salir":
                    JOptionPane.showMessageDialog(null, "Total gastado en la sesión: $" + totalCompras);
                    salir = true;
                    break;
            }
        }
    }
}
