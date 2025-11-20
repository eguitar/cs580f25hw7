package hw7;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {

    private VendingMachine machine;
    
    @BeforeEach
    public void setup() {
        Snack coke = new Snack("Coke", 1.25, 2);
        Snack pepsi = new Snack("Pepsi", 1.25, 2);
        Snack cheetos = new Snack("Cheetos", 1.50, 1);
        Snack doritos = new Snack("Doritos", 1.50, 1);
        Snack kitkat = new Snack("KitKat", 2.00, 1);
        Snack snickers = new Snack("Snickers", 2.00, 1);

        machine = new VendingMachine(java.util.List.of(coke, pepsi, cheetos, doritos, kitkat, snickers));

        SnackDispenseHandler cokeHandler = new CokeHandler();
        SnackDispenseHandler pepsiHandler = new PepsiHandler();
        SnackDispenseHandler cheetosHandler = new CheetosHandler();
        SnackDispenseHandler doritosHandler = new DoritosHandler();
        SnackDispenseHandler kitkatHandler = new KitKatHandler();
        SnackDispenseHandler snickersHandler = new SnickersHandler();

        cokeHandler.setNextHandler(pepsiHandler);
        pepsiHandler.setNextHandler(cheetosHandler);
        cheetosHandler.setNextHandler(doritosHandler);
        doritosHandler.setNextHandler(kitkatHandler);
        kitkatHandler.setNextHandler(snickersHandler);

        machine.setSnackHandlerChain(cokeHandler);
    }

    @Test
    public void testSuccessfulDispenseFlow() {
        machine.getState().idleState(machine, "Snickers");
        machine.getState().paymentState(machine, 2.00);
        machine.getState().dispenseState(machine);
        assertEquals(0, machine.getSnack("Snickers").getQuantity());

        machine.getState().idleState(machine, "Snickers");
        machine.getState().paymentState(machine, 2.00);
        machine.getState().dispenseState(machine);
        assertEquals(0, machine.getSnack("Snickers").getQuantity());
    }

    @Test
    public void testInsufficientFunds() {
        machine.getState().idleState(machine, "Coke");
        machine.getState().paymentState(machine, 1.00);  // Not enough money
        assertTrue(machine.getState() instanceof IdleState);
        assertEquals(2, machine.getSnack("Coke").getQuantity());
    }

    @Test
    public void testExactMoneySnackDispense() {
        machine.getState().idleState(machine, "Coke");
        machine.getState().paymentState(machine, 1.25);
        machine.getState().dispenseState(machine);
        assertEquals(1, machine.getSnack("Coke").getQuantity());
    }
}