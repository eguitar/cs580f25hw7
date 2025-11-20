package hw7;

public class PaymentState implements StateOfVendingMachine {
    @Override
    public void idleState(VendingMachine machine, String snackName) {
        System.out.println("Snack already selected.");
    }

    @Override
    public void paymentState(VendingMachine machine, double amount) {
        Snack snack = machine.getSnack(machine.getSelectedSnack());
        if (snack == null) {
            System.out.println("This snack is not available.");
            machine.setState(new IdleState());
        }
        else if (amount < snack.getPrice()) {
            System.out.println("Insufficient funds. Returning payment.");
            machine.setState(new IdleState());
        }
        else {
            if (snack.getQuantity() > 0) {
                if (amount == snack.getPrice()) {
                    System.out.println("Payment accepted. Snack will dispense.");
                }
                else {
                    System.out.println("Payment accepted. Returning excess money. Snack will dispense.");
                }
                machine.setState(new DispenseState());
            }
            else {
                System.out.println(snack.getName() + "is out of stock.");
                machine.setState(new IdleState());
            }
        }
    }

    @Override
    public void dispenseState(VendingMachine machine) {
        System.out.println("Please insert payment first.");
    }
}