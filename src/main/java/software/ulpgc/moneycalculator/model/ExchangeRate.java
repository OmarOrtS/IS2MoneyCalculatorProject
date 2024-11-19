package software.ulpgc.moneycalculator.model;

public record ExchangeRate(long timestamp, double rate, Currency from, Currency to) {

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "timestamp=" + timestamp +
                ", rate=" + rate +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
