package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.io.*;
import software.ulpgc.moneycalculator.io.deserializer.ExchangeRateDeserializer;
import software.ulpgc.moneycalculator.io.deserializer.URLJSONObjectExchangeRateDeserializer;
import software.ulpgc.moneycalculator.io.deserializer.TsvCurrencyDeserializer;
import software.ulpgc.moneycalculator.io.loaders.EraioExchangeRateLoader;
import software.ulpgc.moneycalculator.io.loaders.FileCurrencyLoader;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File(new File("currencies.tsv").getAbsolutePath());
        CurrencyLookup lookup = new MapCurrencyLookup(new FileCurrencyLoader(file, new TsvCurrencyDeserializer()).load());
        ExchangeRateDeserializer deserializer = new URLJSONObjectExchangeRateDeserializer(lookup,
                new EraioURLConnection(
                new URL("http://api.exchangeratesapi.io/v1/latest?access_key=16471ad3308b77332214706c7d925e00")));
        ExchangeRate exchangeRate = new EraioExchangeRateLoader(deserializer).load(lookup.get("USD"),lookup.get("GBP"));
        System.out.println(exchangeRate.toString());

        MainFrame mainFrame = new MainFrame(new FileCurrencyLoader(file, new TsvCurrencyDeserializer()).load());

    }

}
