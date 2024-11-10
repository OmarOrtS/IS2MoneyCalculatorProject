package software.ulpgc.moneycalculator.Swing;

import software.ulpgc.moneycalculator.model.Money;
import software.ulpgc.moneycalculator.view.MoneyDisplay;

public class SwingMoneyDisplay implements MoneyDisplay {
    @Override
    public void display(Money money) {
        System.out.println(money);
    }
}
