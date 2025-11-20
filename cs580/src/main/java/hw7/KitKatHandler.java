package hw7;

public class KitKatHandler extends SnackDispenseHandler {
    @Override
    public void handleRequest(String snackName, VendingMachine machine) {
        if (snackName.equalsIgnoreCase("KitKat")) {
            Snack snack = machine.getSnack("KitKat");
            if (snack.getQuantity() > 0) {
                snack.reduceQuantity();
                System.out.println("Dispensed KitKat. Remaining: " + snack.getQuantity());
            } else {
                System.out.println("KitKat is out of stock.");
            }
        } else if (nextHandler != null) {
            nextHandler.handleRequest(snackName, machine);
        }
    }
}