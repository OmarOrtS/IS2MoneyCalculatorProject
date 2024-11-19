package software.ulpgc.moneycalculator.io.connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class EraioURLConnectionReader implements URLConnectionReader{
    private final URL urlConnection;

    public EraioURLConnectionReader(URL urlConnection) {
        this.urlConnection = urlConnection;
    }


    @Override
    public StringBuilder readConnection() throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.openStream()))) {
            bufferedReader.lines().forEach(response::append);}
        return response;
    }
}
