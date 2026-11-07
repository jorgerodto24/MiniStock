import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Inventario {

    private Map<String, Producto> productos;
    private String rutaArchivo;


    public Inventario() {
        this("productos.txt", true);
    }

    public Inventario(boolean cargarArchivo) {
        this("productos.txt", cargarArchivo);
    }

    public Inventario(String rutaArchivo, boolean cargarArchivo) {
        this.productos = new HashMap<>();
        if (rutaArchivo == null || rutaArchivo.isBlank()) {
            throw new IllegalArgumentException("La ruta del archivo no puede estar vacía");
        }
        this.rutaArchivo = rutaArchivo;

        if (cargarArchivo) {
            cargarProductos();
        }
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

        Producto producto = productos.get(codigo);

        if (producto == null) {
            return false;
        }

        producto.setCantidad(cantidad);
        return true;

        }

    public  boolean actualizarPrecio(String codigo, double precio) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código no puede estar vacío");
        }

        Producto producto = productos.get(codigo);

        if (producto == null) {
            return false;
        }

        producto.setPrecio(precio);
        return true;

    }

    public  void guardarProductos() {
        try (FileWriter escritor = new FileWriter(rutaArchivo)) {

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
                FileReader lector = new FileReader(rutaArchivo);
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

    public List<Producto> mostrarStockBajo() {

        List<Producto> productosBajoStock = new ArrayList<>();
        for (Producto producto : this.productos.values()) {

            if (producto.getCantidad() < 10) {
                productosBajoStock.add(producto);
            }
        }
        return productosBajoStock;
    }

    public double calcularValorInventario() {
        double total = 0.00;

        for (Producto producto : productos.values()) {

            total += producto.getPrecio() * producto.getCantidad();
        }
        return total;
    }
}
