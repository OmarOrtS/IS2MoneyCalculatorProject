package software.ulpgc.moneycalculator.io.loaders;

import software.ulpgc.moneycalculator.model.Currency;
import software.ulpgc.moneycalculator.io.deserializer.CurrencyDeserializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileCurrencyLoader implements CurrencyLoader {
    private final File file;
    private final CurrencyDeserializer deserializer;

    public FileCurrencyLoader(File file, CurrencyDeserializer deserializer) {
        this.file = file;
        this.deserializer = deserializer;
    }


    @Override
    public List<Currency> load() throws IOException {
        List<Currency> currencies = new ArrayList<>();
        List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines) {
            currencies.add(deserializer.deserialize(line));
        }
        return currencies;
    }
}
