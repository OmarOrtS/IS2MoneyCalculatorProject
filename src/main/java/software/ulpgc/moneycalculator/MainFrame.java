package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.model.Currency;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {
    private final Map<String, Command> commands;
    private final List<Currency> currencies;
    private SwingMoneyDialog moneyDialog;
    private SwingCurrencyDialog currencyDialog;

    public MainFrame(List<Currency> currencies) {
        this.currencies = currencies;
        this.commands = new HashMap<>();
        this.setTitle("MoneyCalculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(toolbar(), BorderLayout.SOUTH);
        this.add(compositeDialog());
    }

    private Component compositeDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.add(this.moneyDialog = new SwingMoneyDialog(new SwingCurrencyDialog(currencies)));
        panel.add(this.currencyDialog = new SwingCurrencyDialog(currencies));
        return panel;
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(button("Calculate Exchange"));
        return panel;
    }

    private Component button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(e -> commands.get(name).execute());
        return button;
    }

    public Command put(String key, Command value) { return commands.put(key, value); }

    public SwingMoneyDialog getMoneyDialog() { return moneyDialog; }

    public SwingCurrencyDialog getCurrencyDialog() { return currencyDialog; }
}
