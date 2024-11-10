package software.ulpgc.moneycalculator.io;

import java.io.IOException;
import java.io.InputStream;

public interface URLConnection {
    URLConnection openConnection() throws IOException;
    void setRequestMethod(String method) throws IOException;
    InputStream getInputStream() throws IOException;
}
