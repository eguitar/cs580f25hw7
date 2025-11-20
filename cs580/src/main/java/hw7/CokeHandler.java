package hw7;

public class CokeHandler extends SnackDispenseHandler {
    @Override
    public void handleRequest(String snackName, VendingMachine machine) {
        if (snackName.equalsIgnoreCase("Coke")) {
            Snack snack = machine.getSnack("Coke");
            if (snack.getQuantity() > 0) {
                snack.reduceQuantity();
                System.out.println("Dispensed Coke. Remaining: " + snack.getQuantity());
            } else {
                System.out.println("Coke is out of stock.");
            }
        } else if (nextHandler != null) {
            nextHandler.handleRequest(snackName, machine);
        }
    }
}
