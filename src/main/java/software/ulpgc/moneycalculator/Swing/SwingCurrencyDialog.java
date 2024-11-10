package software.ulpgc.moneycalculator.Swing;

import javax.swing.*;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.view.CurrencyDialog;

import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {
    private final List<Currency> currencies;
    private final JComboBox<String> selector;

    public SwingCurrencyDialog(List<Currency> currencies) {
        this.currencies = currencies;
        this.add(this.selector = selector());
    }

    private JComboBox<String> selector() {
        JComboBox<String> comboBox = new JComboBox<>();
        currencies.stream().map(Currency::getCode).forEach(comboBox::addItem);
        return comboBox;
    }

    @Override
    public Currency get() { return currencies.get(selector.getSelectedIndex()); }
}
