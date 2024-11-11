package software.ulpgc.moneycalculator.io.connections;

import java.io.IOException;
import java.io.InputStream;

public interface URLConnection {
    void setRequestMethod(String method) throws IOException;
    InputStream getInputStream() throws IOException;
}
