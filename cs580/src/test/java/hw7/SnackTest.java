package hw7;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SnackTest {

    @Test
    public void testSnackCreation() {
        Snack snack = new Snack("Coke", 1.25, 5);
        assertEquals("Coke", snack.getName());
        assertEquals(1.25, snack.getPrice());
        assertEquals(5, snack.getQuantity());
    }

    @Test
    public void testReduceQuantity() {
        Snack snack = new Snack("Pepsi", 1.50, 2);
        snack.reduceQuantity();
        assertEquals(1, snack.getQuantity());
        snack.reduceQuantity();
        assertEquals(0, snack.getQuantity());
        snack.reduceQuantity();
        assertEquals(0, snack.getQuantity());
    }
}
