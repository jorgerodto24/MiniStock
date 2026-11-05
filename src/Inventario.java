import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Inventario {

    private Map<String, Producto> productos;

    public Inventario() {
        this.productos = new HashMap<>();
        cargarProductos();
    }

    public Map<String, Producto> getProductos() {
        return Collections.unmodifiableMap(productos);
    }

    public boolean añadirProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }

        if (!productos.containsKey(producto.getCodigo())) {
            productos.put(producto.getCodigo(), producto);
            return true;
        } else {
            return false;
        }
    }

    public  boolean buscarProducto(String codigo) {


        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código no puede estar vacío");
        }

        return productos.containsKey(codigo);
    }


    public boolean eliminarProducto(String codigo) {


        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código no puede estar vacío");
        }

        return productos.remove(codigo) != null;
    }

    public  boolean actualizarProducto(String codigo, int cantidad) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código no puede estar vacío");
        }

        if (!productos.containsKey(codigo)) {
            return false;
        }

        Producto producto = productos.get(codigo);
        producto.setCantidad(cantidad);
        return true;

        }

    public  boolean actualizarPrecio(String codigo, double precio) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código no puede estar vacío");
        }

        if (!productos.containsKey(codigo)) {
            return false;
        }

        Producto producto = productos.get(codigo);
        producto.setPrecio(precio);
        return true;

    }

    public  void guardarProductos() {
        try (FileWriter escritor = new FileWriter("productos.txt")) {

            for (Producto producto : productos.values()) {
                escritor.write(
                            producto.getCodigo() + ";" +
                                producto.getNombre() + ";" +
                                producto.getPrecio() + ";" +
                                producto.getCantidad() +
                                System.lineSeparator()
                );
            }

            System.out.println("Productos guardados correctamente");

        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public  void cargarProductos() {
        try (
                FileReader lector = new FileReader("productos.txt");
                BufferedReader lectorLineas = new BufferedReader(lector)
        ) {

            String linea;

            while ((linea = lectorLineas.readLine()) != null) {
                try {
                    String[] datos = linea.split(";");
                    if (datos.length != 4) {
                        System.out.println("Formato incorrecto: " + linea);
                        continue;
                    }
                    String codigo = datos[0];
                    String nombre = datos[1];
                    double precio = Double.parseDouble(datos[2]);
                    int cantidad = Integer.parseInt(datos[3]);

                    if (productos.containsKey(codigo)) {
                        System.out.println("Código duplicado: " + codigo);
                        continue;
                    }
                    Producto nuevoProducto = new Producto(codigo, nombre, precio, cantidad);
                    productos.put(nuevoProducto.getCodigo(), nuevoProducto);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error al procesar esta línea: " + linea + ". Motivo: " + e.getMessage());
                }

            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void mostrarStockBajo() {

        boolean hayBajoStock = false;
        for (Producto producto : productos.values()) {

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

        for (Producto producto : productos.values()) {

            total += producto.getPrecio() * producto.getCantidad();
        }

        System.out.printf("La suma del valor del inventario es %.2f €%n", total);
    }
}
