package software.ulpgc.moneycalculator.model;

import java.text.DecimalFormat;

public class Money {
    private final double amount;
    private final Currency currency;

    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double Amount() {
        return amount;
    }

    public Currency Currency() {
        return currency;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(amount) + currency.Symbol();
    }
}
