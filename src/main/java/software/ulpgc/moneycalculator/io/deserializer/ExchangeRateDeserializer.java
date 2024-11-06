package software.ulpgc.moneycalculator.io.deserializer;

import software.ulpgc.moneycalculator.model.ExchangeRate;

public interface ExchangeRateDeserializer {
    ExchangeRate deserialize(String line);
}
