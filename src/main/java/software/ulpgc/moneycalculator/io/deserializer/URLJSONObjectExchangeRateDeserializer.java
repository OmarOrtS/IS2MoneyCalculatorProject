package software.ulpgc.moneycalculator.io.deserializer;

import org.json.JSONObject;
import software.ulpgc.moneycalculator.io.CurrencyLookup;
import software.ulpgc.moneycalculator.io.EraioURLConnectionReader;
import software.ulpgc.moneycalculator.io.MapCurrencyLookup;
import software.ulpgc.moneycalculator.io.URLConnection;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;
import java.time.LocalDate;


public class URLJSONObjectExchangeRateDeserializer implements ExchangeRateDeserializer{
    private final MapCurrencyLookup currencyLookup;
    private final URLConnection urlConnection;
    private final JSONObject jsonObject;

    public URLJSONObjectExchangeRateDeserializer(CurrencyLookup currencyLookup, URLConnection urlConnection) throws IOException {
        this.currencyLookup = (MapCurrencyLookup) currencyLookup;
        this.urlConnection = urlConnection;
        urlConnection.setRequestMethod("GET");
        this.jsonObject = getJSONObjectFromURL(urlConnection);
    }

    @Override
    public ExchangeRate deserialize(Currency from, Currency to) throws IOException {
        return new ExchangeRate(LocalDate.now(),
                getExchangeRates(from, to, jsonObject),
                new Currency(from.getCode(), from.getName(), from.getSymbol()),
                new Currency(to.getCode(), to.getName(), to.getSymbol())
                );
    }

    private static double getExchangeRates(Currency from, Currency to, JSONObject jsonObject) {
        if (!EurIsEqualsTo(from) && !EurIsEqualsTo(to)) return getIndirectRates(from, to, jsonObject);
        if (!EurIsEqualsTo(from)) return getInverseRates(from, jsonObject);
        return jsonObject.getJSONObject("rates").getDouble(to.getCode());
    }

    private static double getInverseRates(Currency from, JSONObject jsonObject) {
        return 1/jsonObject.getJSONObject("rates").getDouble(from.getCode());
    }

    private static boolean EurIsEqualsTo(Currency currency) {
        return "EUR".equals(currency.getCode());
    }

    private static double getIndirectRates(Currency from, Currency to, JSONObject jsonObject) {
        return jsonObject.getJSONObject("rates").getDouble(to.getCode()) /
                jsonObject.getJSONObject("rates").getDouble(from.getCode());
    }

    private JSONObject getJSONObjectFromURL(URLConnection urlConnection) throws IOException {
        EraioURLConnectionReader eraioURLConnectionReader = new EraioURLConnectionReader(urlConnection);
        StringBuilder response = eraioURLConnectionReader.readConnection();

        return new JSONObject(response.toString());
    }

}
