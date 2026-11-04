import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();

        boolean continuar = true;

        while (continuar) {

            mostrarMenu();


            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                scanner.nextLine();
                switch (opcion) {

                    case 1:
                        mostrarProductos(inventario.getProductos());
                        break;

                    case 2:
                        buscarProductoMenu(inventario, scanner);
                        break;

                    case 3:
                        registrarProducto(inventario, scanner);
                        break;

                    case 4:
                        eliminarProductoMenu(inventario, scanner);
                        break;

                    case 5:
                        inventario.guardarProductos();
                        continuar = false;
                        break;

                    case 6:
                        System.out.println("Actualmente hay " + inventario.getProductos().size() + " productos.");
                        break;

                    case 7:
                        actualizarStockMenu(inventario, scanner);
                        break;

                    case 8:
                        actualizarPrecioMenu(inventario, scanner);
                        break;
                    case 9:
                        inventario.guardarProductos();
                        break;
                    case 10:
                        inventario.mostrarStockBajo();
                        break;

                    case 11:
                        inventario.calcularValorInventario();
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }

            } else {
                System.out.println("No es una opción válida.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n===== GESTOR DE PRODUCTOS =====");
        System.out.println("1. Mostrar productos");
        System.out.println("2. Buscar producto");
        System.out.println("3. Añadir producto, precio y cantidad");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Salir");
        System.out.println("6. Número de productos");
        System.out.println("7. Actualizar stock");
        System.out.println("8. Actualizar precio");
        System.out.println("9. Guardar archivo con productos");
        System.out.println("10. Mostrar productos con bajo stock");
        System.out.println("11. Mostrar valor del inventario");
        System.out.print("Selecciona una opción: ");
    }

    public static void mostrarProductos(ArrayList<Producto> productos) {
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    public static void registrarProducto(Inventario inventario, Scanner scanner) {
        int cantidad;
        double precio = 0;

        System.out.print("Introduce el nombre del producto: ");
        String producto = scanner.nextLine();
        while (producto.isBlank()) {
            System.out.print("Introduce el nombre del producto correctamente: ");
            producto = scanner.nextLine();
        }

        if (inventario.buscarProducto(producto)) {
            System.out.println("Ese producto ya existe.");
            return;
        }

        while (true) {
            System.out.println("Introduce el precio");

            if (scanner.hasNextDouble()) {
                precio = scanner.nextDouble();

                if (precio > 0) {
                    break;
                }

                System.out.println("El precio debe ser mayor que cero");

            } else {
                System.out.println("Debes introducir un número válido");
                scanner.nextLine();
            }
        }

        while (true) {
            System.out.println("Introduce la cantidad");
            if (scanner.hasNextInt()) {
                cantidad = scanner.nextInt();

                if (cantidad >= 0) {
                    break;
                }
                System.out.println("La cantidad no puede ser negativa");

            } else {
                System.out.println("Debes introducir un número válido");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        Producto nuevoProducto = new Producto(producto, precio, cantidad);
        inventario.añadirProducto(nuevoProducto);
        System.out.println("Producto añadido correctamente");
    }

    public static void eliminarProductoMenu(Inventario inventario, Scanner scanner) {
        System.out.println("Introduce el nombre del producto");
        String producto = scanner.nextLine();
        boolean eliminado = inventario.eliminarProducto(producto);
        if (eliminado) {
            System.out.println("Producto eliminado correctamente");
        } else {
            System.out.println("El producto no existe");
        }
    }

    public static void actualizarStockMenu(Inventario inventario, Scanner scanner) {
        int cantidad;

        System.out.println("Introduce el nombre del producto");
        String producto = scanner.nextLine();
        while (producto.isBlank()) {
            System.out.print("Introduce el nombre del producto correctamente: ");
            producto = scanner.nextLine();
        }
        while (true) {
            System.out.println("Introduce la cantidad");
            if (scanner.hasNextInt()) {
                cantidad = scanner.nextInt();

                if (cantidad >= 0) {
                    break;
                }
                System.out.println("La cantidad no puede ser negativa");

            } else {
                System.out.println("Debes introducir un número válido");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        boolean actualizado = inventario.actualizarProducto(producto, cantidad);
        if (actualizado) {
            System.out.println("El stock ha sido actualizado");
        } else {
            System.out.println("El producto no existe");
        }

    }

    public static void buscarProductoMenu(Inventario inventario, Scanner scanner) {
        System.out.println("Introduce el nombre del producto");
        String producto = scanner.nextLine();
        boolean encontrado = inventario.buscarProducto(producto);
        if (encontrado) {
            System.out.println("El producto existe");
        } else {
            System.out.println("El producto no existe");
        }
    }

    public static void actualizarPrecioMenu(Inventario inventario, Scanner scanner) {
        double precio;

        System.out.println("Introduce el nombre del producto");
        String producto = scanner.nextLine();
        if (!inventario.buscarProducto(producto)) {
            System.out.println("El producto no existe");
            return;
        }

        while (producto.isBlank()) {
            System.out.print("Introduce el nombre del producto correctamente: ");
            producto = scanner.nextLine();
        }

        while (true) {
            System.out.println("Introduce el precio");
            if (scanner.hasNextDouble()) {
                precio = scanner.nextDouble();

                if (precio > 0) {
                    break;
                }
                System.out.println("El precio no puede ser 0 o negativo");

            } else {
                System.out.println("Debes introducir un número válido");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        boolean actualizado = inventario.actualizarPrecio(producto, precio);
        if (actualizado) {
            System.out.println("El precio ha sido actualizado");
        } else {
            System.out.println("El producto no existe");
        }

    }

}