package software.ulpgc.moneycalculator.io;

import java.io.IOException;

public interface URLConnectionReader {
    StringBuilder readConnection() throws IOException;
}
