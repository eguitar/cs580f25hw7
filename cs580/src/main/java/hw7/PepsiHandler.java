package hw7;

public class PepsiHandler extends SnackDispenseHandler {
    @Override
    public void handleRequest(String snackName, VendingMachine machine) {
        if (snackName.equalsIgnoreCase("Pepsi")) {
            Snack snack = machine.getSnack("Pepsi");
            if (snack.getQuantity() > 0) {
                snack.reduceQuantity();
                System.out.println("Dispensed Pepsi. Remaining: " + snack.getQuantity());
            } else {
                System.out.println("Pepsi is out of stock.");
            }
        } else if (nextHandler != null) {
            nextHandler.handleRequest(snackName, machine);
        }
    }
}