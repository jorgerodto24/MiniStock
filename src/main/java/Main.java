import java.util.*;

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
                            System.out.println("Actualmente hay " + inventario.getProductos().size() + " productos.");
                            break;

                        case 6:
                            actualizarStockMenu(inventario, scanner);
                            break;

                        case 7:
                            actualizarPrecioMenu(inventario, scanner);
                            break;

                        case 8:
                            List<Producto> productosBajoStock = inventario.mostrarStockBajo();

                            if (productosBajoStock.isEmpty()) {
                                System.out.println("No hay productos con stock bajo.");
                            } else {

                                for (Producto producto : productosBajoStock) {
                                    System.out.println("El artículo " + producto.getNombre() + " tiene solo " + producto.getCantidad() + " unidades");
                                }
                            }
                            break;

                        case 9:
                            System.out.printf("La suma del valor del inventario es %.2f €%n", inventario.calcularValorInventario());
                            break;

                        case 10:
                            inventario.guardarProductos();
                            break;

                        case 11:
                            inventario.guardarProductos();
                            continuar = false;
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
        System.out.println("5. Número de productos");
        System.out.println("6. Actualizar stock");
        System.out.println("7. Actualizar precio");
        System.out.println("8. Mostrar productos con bajo stock");
        System.out.println("9. Mostrar valor del inventario");
        System.out.println("10. Guardar archivo con productos");
        System.out.println("11. Salir");
        System.out.print("Selecciona una opción: ");
    }

    public static void mostrarProductos(Map<String, Producto> productos) {
        for (Producto producto : productos.values()) {
            System.out.println(producto);
        }
    }

    public static void registrarProducto(Inventario inventario, Scanner scanner) {
        int cantidad;
        double precio;

        System.out.print("Introduce el codigo del producto: ");
        String codigo = scanner.nextLine();
        while (codigo.isBlank()) {
            System.out.print("Introduce el codigo del producto correctamente: ");
            codigo = scanner.nextLine();
        }

        System.out.print("Introduce el nombre del producto: ");
        String nombre = scanner.nextLine();
        while (nombre.isBlank()) {
            System.out.print("Introduce el nombre del producto correctamente: ");
            nombre = scanner.nextLine();
        }

        if (inventario.buscarProducto(codigo)) {
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
        Producto nuevoProducto = new Producto(codigo, nombre, precio, cantidad);
        boolean añadido = inventario.añadirProducto(nuevoProducto);

        if (añadido) {
            System.out.println("Producto añadido correctamente");
        } else {
            System.out.println("Ya existe un producto con ese código");
        }
    }

    public static void eliminarProductoMenu(Inventario inventario, Scanner scanner) {
        System.out.println("Introduce el codigo del producto");
        String codigo = scanner.nextLine();
        boolean eliminado = inventario.eliminarProducto(codigo);
        if (eliminado) {
            System.out.println("Producto eliminado correctamente");
        } else {
            System.out.println("El producto no existe");
        }
    }

    public static void actualizarStockMenu(Inventario inventario, Scanner scanner) {
        int cantidad;

        System.out.println("Introduce el codigo del producto");
        String codigo = scanner.nextLine();
        while (codigo.isBlank()) {
            System.out.print("Introduce el codigo del producto correctamente: ");
            codigo = scanner.nextLine();
        }

        if (!inventario.buscarProducto(codigo)) {
            System.out.println("El producto no existe");
            return;
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
        if (!inventario.actualizarProducto(codigo, cantidad)) {
            System.out.println("El producto no existe");
            return;
        }

        System.out.println("El stock ha sido actualizado");
    }

    public static void buscarProductoMenu(Inventario inventario, Scanner scanner) {
        System.out.println("Introduce el codigo del producto");
        String codigo = scanner.nextLine();
        boolean encontrado = inventario.buscarProducto(codigo);
        if (encontrado) {
            System.out.println("El producto existe");
        } else {
            System.out.println("El producto no existe");
        }
    }

    public static void actualizarPrecioMenu(Inventario inventario, Scanner scanner) {
        double precio;

        System.out.println("Introduce el codigo del producto");
        String codigo = scanner.nextLine();

        while (codigo.isBlank()) {
            System.out.print("Introduce el codigo del producto correctamente: ");
            codigo = scanner.nextLine();
        }

        if (!inventario.buscarProducto(codigo)) {
            System.out.println("El producto no existe");
            return;
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
        if (!inventario.actualizarPrecio(codigo, precio)) {
            System.out.println("El producto no existe");
            return;
        }

        System.out.println("El precio ha sido actualizado");
    }
}