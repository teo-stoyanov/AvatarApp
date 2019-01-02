package avatar.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {
    private BufferedReader reader;

    public ConsoleReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }
}
