package software.ulpgc.moneycalculator.io.deserializer;

import org.json.JSONObject;
import software.ulpgc.moneycalculator.io.connections.EraioURLConnectionReader;
import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.model.ExchangeRate;

import java.io.IOException;
import java.net.URL;


public class URLJSONObjectExchangeRateDeserializer implements ExchangeRateDeserializer{
    private final JSONObject jsonObject;

    public URLJSONObjectExchangeRateDeserializer(URL urlConnection) throws IOException {
        this.jsonObject = getJSONObjectFromURL(urlConnection);
    }

    @Override
    public ExchangeRate deserialize(Currency from, Currency to) {
        return new ExchangeRate(jsonObject.getLong("timestamp"),
                getExchangeRates(from, to, jsonObject),
                new Currency(from.Code(), from.Name(), from.Symbol()),
                new Currency(to.Code(), to.Name(), to.Symbol())
                );
    }

    private static double getExchangeRates(Currency from, Currency to, JSONObject jsonObject) {
        if (EurNotEqualsTo(from) && EurNotEqualsTo(to)) return getIndirectRates(from, to, jsonObject);
        return EurNotEqualsTo(from) ?
                getInverseRates(from, jsonObject) :
                jsonObject.getJSONObject("rates").getDouble(to.Code());
    }

    private static double getInverseRates(Currency from, JSONObject jsonObject) {
        return 1/jsonObject.getJSONObject("rates").getDouble(from.Code());
    }

    private static boolean EurNotEqualsTo(Currency currency) {
        return !"EUR".equals(currency.Code());
    }

    private static double getIndirectRates(Currency from, Currency to, JSONObject jsonObject) {
        return jsonObject.getJSONObject("rates").getDouble(to.Code()) /
                jsonObject.getJSONObject("rates").getDouble(from.Code());
    }

    private JSONObject getJSONObjectFromURL(URL urlConnection) throws IOException {
        EraioURLConnectionReader eraioURLConnectionReader = new EraioURLConnectionReader(urlConnection);
        StringBuilder response = eraioURLConnectionReader.readConnection();

        return new JSONObject(response.toString());
    }

}
