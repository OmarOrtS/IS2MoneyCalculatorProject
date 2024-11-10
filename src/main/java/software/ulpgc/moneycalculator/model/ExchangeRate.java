package software.ulpgc.moneycalculator.model;

import java.time.LocalDate;

public record ExchangeRate(LocalDate date, double rate, Currency from, Currency to) {

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "date=" + date +
                ", rate=" + rate +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
