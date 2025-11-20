package hw7;

public class SnickersHandler extends SnackDispenseHandler {
    @Override
    public void handleRequest(String snackName, VendingMachine machine) {
        if (snackName.equalsIgnoreCase("Snickers")) {
            Snack snack = machine.getSnack("Snickers");
            if (snack.getQuantity() > 0) {
                snack.reduceQuantity();
                System.out.println("Dispensed Snickers. Remaining: " + snack.getQuantity());
            } else {
                System.out.println("Snickers is out of stock.");
            }
        } else if (nextHandler != null) {
            nextHandler.handleRequest(snackName, machine);
        }
    }
}