package software.ulpgc.moneycalculator.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EraioURLConnectionReader implements URLConnectionReader{
    private final URLConnection urlConnection;
    private final BufferedReader bufferedReader;

    public EraioURLConnectionReader(URLConnection urlConnection) throws IOException {
        this.urlConnection = urlConnection;
        this.bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
    }


    @Override
    public StringBuilder readConnection() throws IOException {
        StringBuilder response = new StringBuilder();
        bufferedReader.lines().forEach(response::append);
        bufferedReader.close();
        urlConnection.closeConnection();
        return response;
    }
}
