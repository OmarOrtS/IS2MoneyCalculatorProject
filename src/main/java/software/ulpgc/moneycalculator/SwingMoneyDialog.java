package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.model.Money;

import javax.swing.*;
import java.awt.*;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private final JTextField amountField;
    private final SwingCurrencyDialog currencyDialog;


    public SwingMoneyDialog(SwingCurrencyDialog currencyDialog) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(this.amountField = amountField());
        this.add(this.currencyDialog = currencyDialog);
    }

    private JTextField amountField() {
        JTextField textField = new JTextField();
        textField.setColumns(10);
        return textField;
    }

    @Override
    public Money get() {
        return new Money(toInteger(amountField.getText()), currencyDialog.get());
    }

    private double toInteger(String text) { return Double.parseDouble(text); }
}
