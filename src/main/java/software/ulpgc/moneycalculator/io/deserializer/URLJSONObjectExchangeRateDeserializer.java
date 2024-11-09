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

    public URLJSONObjectExchangeRateDeserializer(CurrencyLookup currencyLookup, URLConnection urlConnection) {
        this.currencyLookup = (MapCurrencyLookup) currencyLookup;
        this.urlConnection = urlConnection;
    }

    @Override
    public ExchangeRate deserialize(Currency from, Currency to) throws IOException {
        JSONObject jsonObject = getJSONObjectFromURL(urlConnection);
        return new ExchangeRate(LocalDate.now(),
                getExchangeRates(from, to, jsonObject),
                new Currency(from.getCode(), from.getName(), from.getSymbol()),
                new Currency(to.getCode(), to.getName(), to.getSymbol())
                );
    }

    private static double getExchangeRates(Currency from, Currency to, JSONObject jsonObject) {
        if (EurIsEqualsTo(from) && EurIsEqualsTo(to)) return getIndirectRates(from, to, jsonObject);
        if (EurIsEqualsTo(from)) return jsonObject.getJSONObject("rates").getDouble(from.getCode());
        return jsonObject.getJSONObject("rates").getDouble(to.getCode());
    }

    private static boolean EurIsEqualsTo(Currency currency) {
        return "EUR".equals(currency.getCode());
    }

    private static double getIndirectRates(Currency from, Currency to, JSONObject jsonObject) {
        return jsonObject.getJSONObject("rates").getDouble(from.getCode()) /
                jsonObject.getJSONObject("rates").getDouble(to.getCode());
    }

    public JSONObject getJSONObjectFromURL(URLConnection urlConnection) throws IOException {
        urlConnection.setRequestMethod("GET");
        EraioURLConnectionReader eraioURLConnectionReader = new EraioURLConnectionReader(urlConnection);
        StringBuilder response = eraioURLConnectionReader.readConnection();

        return new JSONObject(response.toString());
    }

}
