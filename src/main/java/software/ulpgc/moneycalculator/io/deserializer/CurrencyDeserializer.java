package software.ulpgc.moneycalculator.io.deserializer;

import software.ulpgc.moneycalculator.model.Currency;

public interface CurrencyDeserializer {
    Currency deserialize(String line);
}
