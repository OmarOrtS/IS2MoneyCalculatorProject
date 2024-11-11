package software.ulpgc.moneycalculator.Swing;

import software.ulpgc.moneycalculator.control.Command;
import software.ulpgc.moneycalculator.model.Currency;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {
    private final Map<String, Command> commands;
    private final SwingMoneyDialog moneyDialog;
    private final SwingCurrencyDialog currencyDialog;
    private final JTextArea resultDisplay;

    public MainFrame(List<Currency> currencies) {
        this.commands = new HashMap<>();
        this.setTitle("MoneyCalculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1080,720);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10,10));
        this.resultDisplay = createResultDisplay();
        this.moneyDialog = createMoneyDialog(currencies);
        this.currencyDialog = createCurrencyDialog(currencies);
        JPanel dialogPanel = createDialogPanel();
        this.add(dialogPanel, BorderLayout.CENTER);
        this.add(toolbar(), BorderLayout.SOUTH);
    }

    private JPanel createDialogPanel() {
        JPanel dialogPanel = new JPanel(new GridLayout(3,1,10,10));
        Arrays.asList(createInputPanel(), createTargetCurrencyPanel(), createResultPanel())
                .forEach(dialogPanel::add);
        return dialogPanel;
    }

    private Component createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Conversion Result"));
        panel.add(new JScrollPane(resultDisplay), BorderLayout.CENTER);
        return panel;
    }

    private Component createTargetCurrencyPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Select Target Currency"));
        panel.add(currencyDialog);
        return panel;
    }

    private Component createInputPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Enter amount and source currency"));
        panel.add(moneyDialog);
        return panel;
    }

    private JTextArea createResultDisplay() {
        JTextArea resultDisplay = new JTextArea(5,30);
        resultDisplay.setEditable(false);
        return resultDisplay;
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

    private static SwingCurrencyDialog createCurrencyDialog(List<Currency> currencies) {
        return new SwingCurrencyDialog(currencies);
    }

    private static SwingMoneyDialog createMoneyDialog(List<Currency> currencies) {
        return new SwingMoneyDialog(createCurrencyDialog(currencies));
    }

    public void put(String key, Command value) { commands.put(key, value); }

    public SwingMoneyDialog MoneyDialog() { return moneyDialog; }

    public SwingCurrencyDialog CurrencyDialog() { return currencyDialog; }

    public JTextArea ResultDisplay() { return resultDisplay; }

}
