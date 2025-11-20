package hw7;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SnackDispenseHandlerTest {

    @Test
    public void testHandlerDispensesCorrectSnack() {
        Snack coke = new Snack("Coke", 1.25, 1);
        Snack pepsi = new Snack("Pepsi", 1.25, 1);
        VendingMachine machine = new VendingMachine(java.util.List.of(coke, pepsi));

        CokeHandler cokeHandler = new CokeHandler();
        PepsiHandler pepsiHandler = new PepsiHandler();
        cokeHandler.setNextHandler(pepsiHandler);
        machine.setSnackHandlerChain(cokeHandler);

        cokeHandler.handleRequest("Coke", machine);
        assertEquals(0, machine.getSnack("Coke").getQuantity());
        assertEquals(1, machine.getSnack("Pepsi").getQuantity());

        cokeHandler.handleRequest("Pepsi", machine);
        assertEquals(0, machine.getSnack("Pepsi").getQuantity());
    }

    @Test
    public void testOutOfStockPassesToNext() {
        Snack coke = new Snack("Coke", 1.25, 0);
        Snack pepsi = new Snack("Pepsi", 1.25, 1);
        VendingMachine machine = new VendingMachine(java.util.List.of(coke, pepsi));

        CokeHandler cokeHandler = new CokeHandler();
        PepsiHandler pepsiHandler = new PepsiHandler();
        cokeHandler.setNextHandler(pepsiHandler);
        machine.setSnackHandlerChain(cokeHandler);

        pepsiHandler.handleRequest("Pepsi", machine);
        assertEquals(0, machine.getSnack("Pepsi").getQuantity());
    }
}