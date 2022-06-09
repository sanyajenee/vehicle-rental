package vehiclerental;

import com.vehiclerental.Driver;
import com.vehiclerental.exception.InvalidModeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DriverTest {
    private InputStream sysInBackup;
    private PrintStream sysOutBackup;
    private final ByteArrayOutputStream stdout = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        sysInBackup = System.in;
        sysOutBackup = System.out;
        System.setOut(new PrintStream(stdout));
    }

    @AfterEach
    public void reset() {
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
    }

    @Test
    void testFileModeWithValidFile() throws IOException {
        final String expected =
                  "TRUE\n"
                + "TRUE\n"
                + "TRUE\n"
                + "FALSE\n"
                + "TRUE\n"
                + "TRUE\n"
                + "TRUE\n"
                + "FALSE\n"
                + "FALSE\n"
                + "1000\n"
                + "1200\n"
                + "100\n"
                + "100\n"
                + "-1\n"
                + "-1\n"
                + "-1\n"
                + "V2\n"
                + "FAIL\n";

        Driver.main(new String[] {"src/test/resources/testInput.txt"});
        final String actual = stdout.toString();

        assertEquals(expected, actual);
    }

    @Test
    void testFileModeWithInvalidFile() throws IOException {
        final String expected = "Invalid File - file not found\n";

        Driver.main(new String[] {"letsbreakit.txt"});
        final String actual = stdout.toString();

        assertEquals(expected, actual);
    }

    @Test
    void testInvalidMode() {
        assertThrows(InvalidModeException.class, () -> Driver.main(new String[] {"lets", "break", "it"}));
    }
}
