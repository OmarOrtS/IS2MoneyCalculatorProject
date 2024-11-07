package software.ulpgc.moneycalculator.io.deserializer;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;

public interface ExchangeRateDeserializer {
    ExchangeRate deserialize(Currency from, Currency to) throws IOException;
}
