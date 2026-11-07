import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class InventarioTest {

    @Test
    void añadirProductoCorrectamente() {
        Inventario inventario = new Inventario(false);
        Producto producto = new Producto("P001", "Leche", 1.20, 20);

        boolean resultado = inventario.añadirProducto(producto);

        assertTrue(resultado);
        assertTrue(inventario.buscarProducto("P001"));
    }

    @Test
    void añadirDosProductosMismoCodigo() {
        Inventario inventario = new Inventario(false);
        Producto producto = new Producto("P001", "Leche", 1.20, 20);
        Producto producto2 = new Producto("P001", "Atun", 3.20, 15);

        inventario.añadirProducto(producto);
        boolean resultado = inventario.añadirProducto(producto2);
        assertFalse(resultado);
        assertTrue(inventario.buscarProducto("P001"));
    }


    @Test
    void eliminarProducto() {
        Inventario inventario = new Inventario(false);
        Producto producto = new Producto("P001", "Leche", 1.20, 20);

        inventario.añadirProducto(producto);

        boolean resultado = inventario.eliminarProducto("P001");

        assertTrue(resultado);
        assertFalse(inventario.buscarProducto("P001"));
    }

    @Test
    void eliminarProductoInexistente() {
        Inventario inventario = new Inventario(false);

        boolean resultado = inventario.eliminarProducto("P999");

        assertFalse(resultado);
    }

    @Test
    void actualizarProducto() {
        Inventario inventario = new Inventario(false);
        Producto producto = new Producto("P001", "Leche", 1.20, 20);

        inventario.añadirProducto(producto);
        boolean resultado= inventario.actualizarProducto("P001", 30);
        assertTrue(resultado);
        assertEquals(30, producto.getCantidad());
    }

    @Test
    void actualizarProductoInexistente() {
        Inventario inventario = new Inventario(false);
        Producto producto = new Producto("P001", "Leche", 1.20, 20);

        inventario.añadirProducto(producto);
        boolean resultado= inventario.actualizarProducto("P002", 30);
        assertFalse(resultado);
        assertEquals(20, producto.getCantidad());
    }

    @Test
    void actualizarPrecio() {
        Inventario inventario = new Inventario(false);
        Producto producto = new Producto("P001", "Leche", 1.20, 20);

        inventario.añadirProducto(producto);

        boolean resultado= inventario.actualizarPrecio("P001", 1.50);

        assertTrue(resultado);
        assertEquals(1.50, producto.getPrecio(), 0.001);
    }

    @Test
    void actualizarPrecioInexistente() {
        Inventario inventario = new Inventario(false);
        Producto producto = new Producto("P001", "Leche", 1.20, 20);

        inventario.añadirProducto(producto);
        boolean resultado= inventario.actualizarPrecio("P002", 1.50);
        assertFalse(resultado);
        assertEquals(1.20, producto.getPrecio(), 0.001);
    }

    @Test
    void calcularValorInventario() {
        Inventario inventario = new Inventario(false);
        Producto producto = new Producto("P001", "Leche", 1.20, 20);
        Producto producto2 = new Producto("P002", "Atún", 3.20, 50);

        inventario.añadirProducto(producto);
        inventario.añadirProducto(producto2);

        double resultado = inventario.calcularValorInventario();

        assertEquals(184, resultado, 0.001);
    }


    @Test
    void mostrarStockBajo() {
        Inventario inventario = new Inventario(false);

        Producto producto1 = new Producto("P001", "Leche", 1.20, 5);
        Producto producto2 = new Producto("P002", "Atún", 3.20, 20);
        Producto producto3 = new Producto("P003", "Pan", 0.90, 3);

        inventario.añadirProducto(producto1);
        inventario.añadirProducto(producto2);
        inventario.añadirProducto(producto3);

        int resultado = inventario.mostrarStockBajo().size();

        assertEquals(2,resultado);
    }


    @Test
    void mostrarStockBajoSinProductosBajoStock() {
        Inventario inventario = new Inventario(false);
        Producto producto1 = new Producto("P001", "Leche", 1.20, 10);
        Producto producto2 = new Producto("P002", "Atún", 3.20, 20);

        inventario.añadirProducto(producto1);
        inventario.añadirProducto(producto2);

        assertTrue(inventario.mostrarStockBajo().isEmpty());
    }

    @Test
    void buscarProductExistente() {
        Inventario inventario = new Inventario(false);
        Producto producto1 = new Producto("P001", "Leche", 1.20, 10);
        Producto producto2 = new Producto("P002", "Atún", 3.20, 20);

        inventario.añadirProducto(producto1);
        inventario.añadirProducto(producto2);

        assertTrue(inventario.buscarProducto("P001"));

    }

    @Test
    void buscarProductNoExistente() {
        Inventario inventario = new Inventario(false);
        Producto producto1 = new Producto("P001", "Leche", 1.20, 10);
        Producto producto2 = new Producto("P002", "Atún", 3.20, 20);

        inventario.añadirProducto(producto1);
        inventario.añadirProducto(producto2);

        assertFalse(inventario.buscarProducto("P003"));
    }

    @Test
    void guardarProductos() throws IOException {
        Inventario inventario = new Inventario("productos-test.txt", false);
        Producto producto1 = new Producto("P001", "Leche", 1.20, 10);
        Producto producto2 = new Producto("P002", "Atún", 3.20, 20);

        inventario.añadirProducto(producto1);
        inventario.añadirProducto(producto2);
        inventario.guardarProductos();

        String contenido = Files.readString(Path.of("productos-test.txt"));
        assertTrue(contenido.contains("P001;Leche;1.2;10"));
        assertTrue(contenido.contains("P002;Atún;3.2;20"));
    }

    @Test
    void guardarProductosInventarioVacio() throws IOException {

        Inventario inventario = new Inventario("productos-test.txt", false);

        inventario.guardarProductos();

        String contenido = Files.readString(Path.of("productos-test.txt"));

        assertTrue(contenido.isEmpty());
    }

    @Test
    void cargarProductos() throws IOException {

        String contenido = "P001;Leche;1.2;10" + System.lineSeparator()
                + "P002;Atún;3.2;20";


        Files.writeString(Path.of("productos-test.txt"), contenido);
        Inventario inventario = new Inventario("productos-test.txt", true);

        assertTrue(inventario.buscarProducto("P001"));
        assertTrue(inventario.buscarProducto("P002"));
        assertEquals(10, inventario.getProductos().get("P001").getCantidad());
        assertEquals(1.20, inventario.getProductos().get("P001").getPrecio());

    }

    @Test
    void cargarProductosConDatosInvalidos() throws IOException {
        String contenido = "P001;Leche;-1.2;10" + System.lineSeparator()
                + "P002;Atún;3.2;20";


        Files.writeString(Path.of("productos-test.txt"), contenido);
        Inventario inventario = new Inventario("productos-test.txt", true);
        assertFalse(inventario.buscarProducto("P001"));
        assertTrue(inventario.buscarProducto("P002"));
    }

    @Test
    void cargarProductosDuplicados() throws IOException {

        String contenido = "P001;Leche Entera;1.2;10" + System.lineSeparator()
                + "P001;Leche Semidesnatada;1.1;20";


        Files.writeString(Path.of("productos-test.txt"), contenido);
        Inventario inventario = new Inventario("productos-test.txt", true);

        assertEquals(1, inventario.getProductos().size());
        assertEquals("Leche Entera", inventario.getProductos().get("P001").getNombre());
        assertEquals(10, inventario.getProductos().get("P001").getCantidad());
    }

    @Test
    void cargarProductosArchivoVacio() throws IOException {

        Files.writeString(Path.of("productos-test.txt"), "");
        Inventario inventario = new Inventario("productos-test.txt", true);

        assertTrue(inventario.getProductos().isEmpty());
    }

    @Test
    void cargarProductosConError() throws IOException {
        String contenido = "P001;Leche;1.2;10" + System.lineSeparator()
                + "LINEA PRUEBA CON ERROR" + System.lineSeparator()
                + "P002;Atún;3.2;20";

        Files.writeString(Path.of("productos-test.txt"), contenido);
        Inventario inventario = new Inventario("productos-test.txt", true);

        assertEquals(2, inventario.getProductos().size());
        assertEquals("Leche", inventario.getProductos().get("P001").getNombre());
        assertEquals("Atún", inventario.getProductos().get("P002").getNombre());
    }

    @Test
    void cargarProductosArchivoInexistente() {
        Inventario inventario = new Inventario("archivo-que-no-existe.txt", true);

        assertTrue(inventario.getProductos().isEmpty());
    }

}
