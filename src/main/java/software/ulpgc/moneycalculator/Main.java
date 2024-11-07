package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.io.*;
import software.ulpgc.moneycalculator.io.deserializer.ExchangeRateDeserializer;
import software.ulpgc.moneycalculator.io.deserializer.URLJsonExchangeRateDeserializer;
import software.ulpgc.moneycalculator.io.deserializer.TsvCurrencyDeserializer;
import software.ulpgc.moneycalculator.io.loaders.EraioExchangeRateLoader;
import software.ulpgc.moneycalculator.io.loaders.FileCurrencyLoader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File(new File("currencies.tsv").getAbsolutePath());
        List<Currency> currencies = new FileCurrencyLoader(file, new TsvCurrencyDeserializer()).load();
        CurrencyLookup lookup = new MapCurrencyLookup(currencies);
        ExchangeRateDeserializer deserializer = new URLJsonExchangeRateDeserializer(lookup,
                new EraioURLConnection(
                new URL("http://api.exchangeratesapi.io/v1/latest?access_key=16471ad3308b77332214706c7d925e00")));
        ExchangeRate exchangeRate = new EraioExchangeRateLoader(deserializer).load(lookup.get("USD"),lookup.get("GBP"));
        System.out.println(exchangeRate.toString());

        /*


        ExchangeRate exchangeRate = new ExchangeRate(
                LocalDate.now(),
                jsonObject.getJSONObject("rates").getDouble("EUR"),
                new Currency("EUR", "Euro", "€"),
                new Currency("USD", "Dólar", "$"));

        */
    }

}
