package vehiclerental.model;

import com.vehiclerental.model.Command;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class CommandTest {
    @Test
    void testCommand() {
        Command command = new Command("   book   SUBMARINE   V2   7   90  ");

        String expectedCommandName = "BOOK";
        String actualCommandName = command.getCommandName();

        assertEquals(expectedCommandName, actualCommandName);

        List<String> expectedArgs = List.of("SUBMARINE", "V2", "7", "90");
        List<String> actualArgs = command.getArgs();

        assertLinesMatch(expectedArgs, actualArgs);
    }
}
