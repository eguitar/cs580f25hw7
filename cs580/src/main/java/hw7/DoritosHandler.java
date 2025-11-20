package hw7;

public class DoritosHandler extends SnackDispenseHandler {
    @Override
    public void handleRequest(String snackName, VendingMachine machine) {
        if (snackName.equalsIgnoreCase("Doritos")) {
            Snack snack = machine.getSnack("Doritos");
            if (snack.getQuantity() > 0) {
                snack.reduceQuantity();
                System.out.println("Dispensed Doritos. Remaining: " + snack.getQuantity());
            } else {
                System.out.println("Doritos are out of stock.");
            }
        } else if (nextHandler != null) {
            nextHandler.handleRequest(snackName, machine);
        }
    }
}