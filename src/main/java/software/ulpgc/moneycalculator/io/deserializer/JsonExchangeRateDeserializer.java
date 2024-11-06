package software.ulpgc.moneycalculator.io.deserializer;

import software.ulpgc.moneycalculator.io.CurrencyLookup;
import software.ulpgc.moneycalculator.model.ExchangeRate;

public class JsonExchangeRateDeserializer implements ExchangeRateDeserializer{
    private final CurrencyLookup lookup;

    public JsonExchangeRateDeserializer(CurrencyLookup lookup) {this.lookup = lookup;}

    @Override
    public ExchangeRate deserialize(String line) {
        return null;
    }
}
