package software.ulpgc.moneycalculator.io.loaders;

import software.ulpgc.moneycalculator.io.deserializer.ExchangeRateDeserializer;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;
import java.time.LocalDate;

public class EraioExchangeRateLoader implements ExchangeRateLoader {
    private final ExchangeRateDeserializer deserializer;

    public EraioExchangeRateLoader(ExchangeRateDeserializer deserializer) {this.deserializer = deserializer;}

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try { return deserializer.deserialize(from,to);
        } catch (IOException e) { throw new RuntimeException(e);}
    }

}
