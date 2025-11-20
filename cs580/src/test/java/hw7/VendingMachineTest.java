package hw7;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineTest {

    @Test
    public void testSuccessfulDispense() {
        Snack coke = new Snack("Coke", 1.25, 1);
        VendingMachine machine = new VendingMachine(java.util.List.of(coke));

        SnackDispenseHandler handler = new CokeHandler();
        machine.setSnackHandlerChain(handler);

        machine.selectSnack("Coke");
        machine.insertMoney(1.25);
        machine.dispenseSnack();

        assertEquals(0, machine.getSnack("Coke").getQuantity());
        assertTrue(machine.getState() instanceof IdleState);
    }

    @Test
    public void testInsufficientFunds() {
        Snack coke = new Snack("Coke", 1.25, 1);
        VendingMachine machine = new VendingMachine(java.util.List.of(coke));

        SnackDispenseHandler handler = new CokeHandler();
        machine.setSnackHandlerChain(handler);

        machine.selectSnack("Coke");
        machine.insertMoney(1.00);
        assertTrue(machine.getState() instanceof IdleState);
        assertEquals(1, machine.getSnack("Coke").getQuantity());
    }
}