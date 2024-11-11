package software.ulpgc.moneycalculator.io.connections;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class EraioURLConnection implements URLConnection{
    private final HttpURLConnection connection;

    public EraioURLConnection(URL url) throws IOException {
        this.connection = (HttpURLConnection) url.openConnection();
    }

    @Override
    public void setRequestMethod(String method) throws IOException {
        try { connection.setRequestMethod(method); }
        catch (ProtocolException e) { throw new IOException(e); }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return connection.getInputStream();
    }
}
