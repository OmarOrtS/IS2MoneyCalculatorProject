package software.ulpgc.moneycalculator.io.connections;

import java.io.IOException;

public interface URLConnectionReader {
    StringBuilder readConnection() throws IOException;
}
