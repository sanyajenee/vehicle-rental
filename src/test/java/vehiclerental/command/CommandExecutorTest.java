package vehiclerental.command;

import com.vehiclerental.OutputPrinter;
import com.vehiclerental.command.CommandExecutor;
import com.vehiclerental.command.CommandExecutorFactory;
import com.vehiclerental.model.Command;
import com.vehiclerental.service.VehicleRentalService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class CommandExecutorTest {
    protected VehicleRentalService vehicleRentalService = new VehicleRentalService();
    protected OutputPrinter outputPrinter = new OutputPrinter();

    protected CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(vehicleRentalService, outputPrinter);

    protected void validate(CommandExecutor commandExecutor, String input, Boolean expected) {
        Command command = new Command(input);
        assertNotNull(command);

        boolean actual = commandExecutor.isValid(command);
        assertEquals(expected, actual);
    }
}
