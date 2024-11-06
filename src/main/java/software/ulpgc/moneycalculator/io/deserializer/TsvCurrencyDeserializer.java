package software.ulpgc.moneycalculator.io.deserializer;

import software.ulpgc.moneycalculator.model.Currency;

public class TsvCurrencyDeserializer implements CurrencyDeserializer {
    @Override
    public Currency deserialize(String line) {
        return deserialize(line.split("\t"));
    }

    private Currency deserialize(String[] fields) {
        return new Currency(fields[0],fields[1],fields[2]);
    }
}
