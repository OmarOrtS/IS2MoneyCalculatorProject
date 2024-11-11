package software.ulpgc.moneycalculator.Swing;

import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.view.MoneyDisplay;

import javax.swing.*;

public class SwingMoneyDisplay implements MoneyDisplay {
    private final JTextArea resultDisplay;

    public SwingMoneyDisplay(JTextArea resultDisplay) {
        this.resultDisplay = resultDisplay;
    }

    @Override
    public void display(Money money) {
        resultDisplay.append("Converted Amount: " + money.toString() + "\n");
    }
}
