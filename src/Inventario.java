import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Inventario {

    private ArrayList<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<>();
        cargarProductos();
    }

    public ArrayList<Producto> getProductos() {

        return productos;
    }

    public  boolean añadirProducto(Producto producto) {

        return productos.add(producto);
    }

    public  boolean buscarProducto(String nombre) {
        for (Producto producto : productos) {

            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }

        return false;
    }

    public  boolean eliminarProducto(String producto) {
        for (int i = 0; i < productos.size(); i++) {
            if (producto.equalsIgnoreCase(productos.get(i).getNombre())) {
                productos.remove(i);
                return true;
            }

        }
        return false;
    }

    public  boolean actualizarProducto(String nombre, int cantidad) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                producto.setCantidad(cantidad);
                return true;
            }
        }
        return false;
    }

    public  boolean actualizarPrecio(String nombre, double precio) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                producto.setPrecio(precio);
                return true;
            }
        }
        return false;
    }

    public  void guardarProductos() {
        try {
            FileWriter escritor = new FileWriter("productos.txt");

            for (Producto producto : productos) {
                escritor.write(
                        producto.getNombre() + ";" +
                                producto.getPrecio() + ";" +
                                producto.getCantidad() +
                                System.lineSeparator()
                );
            }

            escritor.close();
            System.out.println("Productos guardados correctamente");

        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public  void cargarProductos() {
        try {
            FileReader lector = new FileReader("productos.txt");
            BufferedReader lectorLineas = new BufferedReader(lector);

            String linea;

            while ((linea = lectorLineas.readLine()) != null) {
                try {
                    String[] datos = linea.split(";");
                    if (datos.length != 3) {
                        System.out.println("Formato incorrecto: " + linea);
                        continue;
                    }
                    String nombre = datos[0];
                    double precio = Double.parseDouble(datos[1]);
                    int cantidad = Integer.parseInt(datos[2]);
                    Producto nuevoProducto = new Producto(nombre, precio, cantidad);
                    productos.add(nuevoProducto);
                } catch (NumberFormatException e) {
                    System.out.println("Error al procesar esta línea: " + linea);
                }

            }

            lectorLineas.close();

        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
        }
    }

    public void mostrarStockBajo() {

        boolean hayBajoStock = false;
        for (Producto producto : productos) {

            if (producto.getCantidad() < 10) {
                System.out.println("El artículo " + producto.getNombre() + " tiene solo " + producto.getCantidad() + " unidades");
                hayBajoStock = true;
            }

        }
        if (!hayBajoStock) {
            System.out.println("Todos los artículos tienen stock suficiente");
        }
    }

    public void calcularValorInventario() {
        double total = 0.00;

        for (Producto producto : productos) {

            total += producto.getPrecio() * producto.getCantidad();
        }

        System.out.printf("La suma del valor del inventario es %.2f €%n", total);
    }
}
