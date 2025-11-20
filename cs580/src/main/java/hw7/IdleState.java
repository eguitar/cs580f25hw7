package hw7;

public class IdleState implements StateOfVendingMachine {
    @Override
    public void idleState(VendingMachine machine, String snackName) {
        machine.setSelectedSnack(snackName);
        System.out.println("Snack selected: " + snackName);
        machine.setState(new PaymentState());
    }

    @Override
    public void paymentState(VendingMachine machine, double amount) {
        System.out.println("Please select snack first.");
    }

    @Override
    public void dispenseState(VendingMachine machine) {
        System.out.println("Please select snack first.");
    }
}