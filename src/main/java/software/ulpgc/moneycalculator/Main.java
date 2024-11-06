package software.ulpgc.moneycalculator;

import software.ulpgc.moneycalculator.io.CurrencyLookup;
import software.ulpgc.moneycalculator.io.MapCurrencyLookup;
import software.ulpgc.moneycalculator.io.deserializer.ExchangeRateDeserializer;
import software.ulpgc.moneycalculator.io.deserializer.JsonExchangeRateDeserializer;
import software.ulpgc.moneycalculator.io.deserializer.TsvCurrencyDeserializer;
import software.ulpgc.moneycalculator.io.loaders.EraioExchangeRateLoader;
import software.ulpgc.moneycalculator.io.loaders.FileCurrencyLoader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        URL url = new URL("http://api.exchangeratesapi.io/v1/latest?access_key=16471ad3308b77332214706c7d925e00");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();
        connection.disconnect();

        System.out.println(response.toString());

        /*
        File file = new File("E:/Is2/currencies.tsv");
        List<Currency> currencies = new FileCurrencyLoader(file, new TsvCurrencyDeserializer()).load();
        CurrencyLookup lookup = new MapCurrencyLookup(currencies);
        ExchangeRateDeserializer deserializer = new JsonExchangeRateDeserializer(lookup);
        ExchangeRate exchangeRate = new EraioExchangeRateLoader(deserializer).load(lookup.get("EUR"),lookup.get("USD"));*/
    }
}
