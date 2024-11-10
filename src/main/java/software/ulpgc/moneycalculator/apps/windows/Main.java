package software.ulpgc.moneycalculator.apps.windows;

import software.ulpgc.moneycalculator.Swing.MainFrame;
import software.ulpgc.moneycalculator.Swing.SwingMoneyDisplay;
import software.ulpgc.moneycalculator.control.CalculateCommand;
import software.ulpgc.moneycalculator.io.*;
import software.ulpgc.moneycalculator.io.deserializer.URLJSONObjectExchangeRateDeserializer;
import software.ulpgc.moneycalculator.io.deserializer.TsvCurrencyDeserializer;
import software.ulpgc.moneycalculator.io.loaders.EraioExchangeRateLoader;
import software.ulpgc.moneycalculator.io.loaders.FileCurrencyLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File(new File("currencies.tsv").getAbsolutePath());
        CurrencyLookup lookup = new MapCurrencyLookup(new FileCurrencyLoader(file, new TsvCurrencyDeserializer()).load());
        MainFrame mainFrame = new MainFrame(new FileCurrencyLoader(file, new TsvCurrencyDeserializer()).load());
        mainFrame.put("Calculate Exchange", new CalculateCommand(
                mainFrame.getMoneyDialog(),
                mainFrame.getCurrencyDialog(),
                new EraioExchangeRateLoader(new URLJSONObjectExchangeRateDeserializer(lookup,
                        new EraioURLConnection(
                                new URL("http://api.exchangeratesapi.io/v1/latest?access_key=16471ad3308b77332214706c7d925e00")))),
                new SwingMoneyDisplay()
        ));
        mainFrame.setVisible(true);
    }

}
