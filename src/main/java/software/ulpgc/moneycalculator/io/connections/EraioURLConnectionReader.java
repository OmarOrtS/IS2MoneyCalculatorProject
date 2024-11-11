package software.ulpgc.moneycalculator.io.connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EraioURLConnectionReader implements URLConnectionReader{
    private final URLConnection urlConnection;

    public EraioURLConnectionReader(URLConnection urlConnection) {
        this.urlConnection = urlConnection;
    }


    @Override
    public StringBuilder readConnection() throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
            bufferedReader.lines().forEach(response::append);}
        return response;
    }
}
