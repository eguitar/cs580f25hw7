package hw7;

import java.util.*;

public class VendingMachine {
    private StateOfVendingMachine state;
    private Map<String, Snack> snacks;
    private SnackDispenseHandler snackHandlerChain;
    private String selectedSnack;

    public VendingMachine(List<Snack> snackList) {
        this.state = new IdleState();
        this.snacks = new HashMap<>();
        for (Snack snack : snackList)
            snacks.put(snack.getName(), snack);
    }

    public void setSnackHandlerChain(SnackDispenseHandler handler) {
        snackHandlerChain = handler;
    }

    public SnackDispenseHandler getSnackHandlerChain() { return snackHandlerChain; }
    public void setState(StateOfVendingMachine newState) { state = newState; }
    public StateOfVendingMachine getState() { return state; }

    public void setSelectedSnack(String name) { selectedSnack = name; }
    public String getSelectedSnack() { return selectedSnack; }

    public Snack getSnack(String name) { return snacks.get(name); }

    public void selectSnack(String name) { state.idleState(this, name); }
    public void insertMoney(double amount) { state.paymentState(this, amount); }
    public void dispenseSnack() { state.dispenseState(this); }
}