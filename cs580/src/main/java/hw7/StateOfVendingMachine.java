package hw7;

public interface StateOfVendingMachine {
    void idleState(VendingMachine machine, String snackName);
    void paymentState(VendingMachine machine, double amount);
    void dispenseState(VendingMachine machine);
}