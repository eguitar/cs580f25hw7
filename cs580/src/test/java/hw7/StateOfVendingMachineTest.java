package hw7;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StateOfVendingMachineTest {

    private VendingMachine machine;

    @BeforeEach
    public void setup() {
        CokeHandler cokeHandler = new CokeHandler();
        PepsiHandler pepsiHandler = new PepsiHandler();
        CheetosHandler cheetosHandler = new CheetosHandler();
        DoritosHandler doritosHandler = new DoritosHandler();
        KitKatHandler kitkatHandler = new KitKatHandler();
        SnickersHandler snickersHandler = new SnickersHandler();

        cokeHandler.setNextHandler(pepsiHandler);
        pepsiHandler.setNextHandler(cheetosHandler);
        cheetosHandler.setNextHandler(doritosHandler);
        doritosHandler.setNextHandler(kitkatHandler);
        kitkatHandler.setNextHandler(snickersHandler);

        Snack coke = new Snack("Coke", 1.25, 2);
        machine = new VendingMachine(java.util.List.of(coke));
        machine.setSnackHandlerChain(cokeHandler);
    }

    @Test
    public void testIdleStateSnackSelectionChangesState() {
        assertTrue(machine.getState() instanceof IdleState);
        machine.getState().idleState(machine, "Coke");
        assertTrue(machine.getState() instanceof PaymentState);
    }

    @Test
    public void testPaymentStateInsertMoneyTransitions() {
        machine.getState().idleState(machine, "Coke");
        assertTrue(machine.getState() instanceof PaymentState);

        machine.getState().paymentState(machine, 1.25);
        assertTrue(machine.getState() instanceof DispenseState);

        machine.setState(new IdleState());
    }

    @Test
    public void testDispenseStateReturnsToIdle() {
        machine.getState().idleState(machine, "Coke");
        machine.getState().paymentState(machine, 1.25);
        machine.getState().dispenseState(machine);

        assertTrue(machine.getState() instanceof IdleState);
    }
}
