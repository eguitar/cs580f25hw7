package hw7;

public class Main {
    public static void main(String[] args) {
        Snack coke = new Snack("Coke", 1.25, 3);
        Snack pepsi = new Snack("Pepsi", 1.25, 2);
        Snack cheetos = new Snack("Cheetos", 1.50, 1);
        Snack doritos = new Snack("Doritos", 1.50, 1);
        Snack kitkat = new Snack("KitKat", 2.00, 1);
        Snack snickers = new Snack("Snickers", 2.00, 2);

        VendingMachine machine = new VendingMachine(java.util.List.of(coke, pepsi, cheetos, doritos, kitkat, snickers));

        SnackDispenseHandler cokeHandler = new CokeHandler();
        SnackDispenseHandler pepsiHandler = new PepsiHandler();
        SnackDispenseHandler cheetosHandler = new CheetosHandler();
        SnackDispenseHandler doritosHandler = new DoritosHandler();
        SnackDispenseHandler kitkatHandler = new KitKatHandler();
        SnackDispenseHandler snickersHandler = new SnickersHandler();

        cokeHandler.setNextHandler(pepsiHandler);
        pepsiHandler.setNextHandler(cheetosHandler);
        cheetosHandler.setNextHandler(doritosHandler);
        doritosHandler.setNextHandler(kitkatHandler);
        kitkatHandler.setNextHandler(snickersHandler);

        machine.setSnackHandlerChain(cokeHandler);

        System.out.println("User selects Snickers and inserts $2.00 (twice to exhaust quantity):");
        for (int i = 0; i < 2; i++) {
            machine.selectSnack("Snickers");
            machine.insertMoney(2.00);
            machine.dispenseSnack();
            System.out.println("Snickers quantity left: " + machine.getSnack("Snickers").getQuantity());
            System.out.println();
        }

        System.out.println("User tries to buy Snickers again (out of stock):");
        machine.selectSnack("Snickers");
        machine.insertMoney(2.00);
        machine.dispenseSnack();
        System.out.println();

        System.out.println("User selects Coke and inserts exact change $1.25:");
        machine.selectSnack("Coke");
        machine.insertMoney(1.25);
        machine.dispenseSnack();
        System.out.println("Coke quantity left: " + machine.getSnack("Coke").getQuantity());
        System.out.println();

        System.out.println("User selects Pepsi and inserts insufficient money $1.00:");
        machine.selectSnack("Pepsi");
        machine.insertMoney(1.00);
        System.out.println("Pepsi quantity left: " + machine.getSnack("Pepsi").getQuantity());
    }
}
