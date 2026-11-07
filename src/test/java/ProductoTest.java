import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {

    @Test
    void crearProductoCorrectamente() {
        Producto producto = new Producto("P001", "Leche", 1.20, 20);

        assertEquals("P001", producto.getCodigo());
        assertEquals("Leche", producto.getNombre());
        assertEquals(1.20, producto.getPrecio());
        assertEquals(20, producto.getCantidad());
    }
    @Test
    void crearProductoConPrecioNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Producto("P002", "Pan", -1.50, 10);
        });
    }

    @Test
    void crearProductoconCantidadNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Producto("P002", "Pan", 1.50, -5);
        });
    }
    @Test
    void crearProductoconNombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Producto("P002", "", 1.50, 10);
        });
    }
}
