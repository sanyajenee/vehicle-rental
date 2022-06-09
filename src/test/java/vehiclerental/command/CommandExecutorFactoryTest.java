package vehiclerental.command;

import com.vehiclerental.OutputPrinter;
import com.vehiclerental.command.CommandExecutor;
import com.vehiclerental.command.CommandExecutorFactory;
import com.vehiclerental.exception.InvalidCommandException;
import com.vehiclerental.service.VehicleRentalService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandExecutorFactoryTest {
    VehicleRentalService vehicleRentalService = new VehicleRentalService();
    OutputPrinter outputPrinter = new OutputPrinter();

    CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(vehicleRentalService, outputPrinter);

    @Test
    void testInvalidCommand() {
        assertThrows(InvalidCommandException.class, () -> {
            CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor("lets break it"); });
    }
}
