package hw7;

public class CheetosHandler extends SnackDispenseHandler {
    @Override
    public void handleRequest(String snackName, VendingMachine machine) {
        if (snackName.equalsIgnoreCase("Cheetos")) {
            Snack snack = machine.getSnack("Cheetos");
            if (snack.getQuantity() > 0) {
                snack.reduceQuantity();
                System.out.println("Dispensed Cheetos. Remaining: " + snack.getQuantity());
            } else {
                System.out.println("Cheetos are out of stock.");
            }
        } else if (nextHandler != null) {
            nextHandler.handleRequest(snackName, machine);
        }
    }
}