package vehiclerental.command;

import com.vehiclerental.exception.InvalidCommandException;
import com.vehiclerental.model.Command;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void testCommandParsing() {
        validate("justdoit 1 2 3", "JUSTDOIT", List.of("1", "2", "3"));
        validate("  justdoit    1    2  3 ", "JUSTDOIT", List.of("1", "2", "3"));
        validate("justdoit", "JUSTDOIT", new ArrayList<>());
        validate("   justdoitagain  ", "JUSTDOITAGAIN", new ArrayList<>());
    }

    private void validate(String input, String expectedCommandName, List<String> expectedArgs) {
        Command command = new Command(input);

        assertNotNull(command);
        assertEquals(expectedCommandName, command.getCommandName());
        assertEquals(expectedArgs, command.getArgs());
    }

    @Test
    void testCommandParsingWithEmptyInput() {
        assertThrows(InvalidCommandException.class, () -> {Command command = new Command(""); });
    }

    @Test
    void testCommandParsingWithSpacedInput() {
        assertThrows(InvalidCommandException.class, () -> {Command command = new Command("     "); });
    }

    @Test
    void testCommandParsingWithRandomInput() {
        Command command = new Command("lets break it");
        assertNotNull(command);
    }
}
