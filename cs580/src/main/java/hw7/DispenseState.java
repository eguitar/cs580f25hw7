package hw7;

public class DispenseState implements StateOfVendingMachine {
    @Override
    public void idleState(VendingMachine machine, String snackName) {
        System.out.println("Snack is being dispensed. Please wait.");
    }

    @Override
    public void paymentState(VendingMachine machine, double amount) {
        System.out.println("Snack is being dispensed. Please wait.");
    }

    @Override
    public void dispenseState(VendingMachine machine) {
        System.out.println("Snack is being dispensed...");
        SnackDispenseHandler handler = machine.getSnackHandlerChain();
        handler.handleRequest(machine.getSelectedSnack(), machine);
        machine.setState(new IdleState());
        System.out.println("Enjoy!");
    }
}