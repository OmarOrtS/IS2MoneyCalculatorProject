package software.ulpgc.moneycalculator;

import javax.swing.*;
import software.ulpgc.moneycalculator.model.Currency;
import java.util.List;

public class SwingCurrencyDialog extends JPanel implements CurrencyDialog {
    private final List<Currency> currencies;
    private final JComboBox<Currency> selector;

    public SwingCurrencyDialog(List<Currency> currencies) {
        this.currencies = currencies;
        this.add(this.selector = selector());
    }

    private JComboBox<Currency> selector() {
        JComboBox<Currency> comboBox = new JComboBox<>();
        currencies.forEach(comboBox::addItem);
        return comboBox;
    }

    @Override
    public Currency get() { return currencies.get(selector.getSelectedIndex()); }
}
