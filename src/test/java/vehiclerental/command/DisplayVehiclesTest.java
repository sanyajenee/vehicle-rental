package vehiclerental.command;

import com.vehiclerental.command.CommandExecutor;
import com.vehiclerental.model.Command;
import com.vehiclerental.util.CommonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DisplayVehiclesTest extends CommandExecutorTest {
    CommandExecutor addBranchCommandExecutor = commandExecutorFactory.getCommandExecutor("ADD_BRANCH");
    CommandExecutor addVehicleCommandExecutor = commandExecutorFactory.getCommandExecutor("ADD_VEHICLE");
    CommandExecutor bookCommandExecutor = commandExecutorFactory.getCommandExecutor("BOOK");
    CommandExecutor displayVehiclesCommandExecutor = commandExecutorFactory.getCommandExecutor("DISPLAY_VEHICLES");

    @BeforeEach
    public void setup() {
        addBranchCommandExecutor.execute(new Command("ADD_BRANCH B1 CAR"));
        addVehicleCommandExecutor.execute(new Command("ADD_VEHICLE B1 CAR V1 100"));
        addVehicleCommandExecutor.execute(new Command("ADD_VEHICLE B1 CAR V2 400"));
        addVehicleCommandExecutor.execute(new Command("ADD_VEHICLE B1 CAR V3 50"));
        bookCommandExecutor.execute(new Command("BOOK B1 CAR 1 4"));
        bookCommandExecutor.execute(new Command("BOOK B1 CAR 6 9"));
    }

    @Test
    void testDisplayVehiclesIsValid() {
        validate(displayVehiclesCommandExecutor,"DISPLAY_VEHICLES ", Boolean.FALSE);
        validate(displayVehiclesCommandExecutor,"LETS    BREAK  IT  SO BAD IT   NEVER  RECOVERS", Boolean.FALSE);
        validate(displayVehiclesCommandExecutor,"DISPLAY_VEHICLES LETS    BREAK  IT  SO  BAD IT   NEVER  RECOVERS ", Boolean.FALSE);

        validate(displayVehiclesCommandExecutor,"DISPLAY_VEHICLES B1 1 4", Boolean.TRUE);

        validate(displayVehiclesCommandExecutor,"DISPLAY_VEHICLES B99 1 4", Boolean.FALSE);
        validate(displayVehiclesCommandExecutor,"DISPLAY_VEHICLES B1 START 4", Boolean.FALSE);
        validate(displayVehiclesCommandExecutor,"DISPLAY_VEHICLES B1 3 END", Boolean.FALSE);
        validate(displayVehiclesCommandExecutor,"DISPLAY_VEHICLES B1 -1 1", Boolean.FALSE);
        validate(displayVehiclesCommandExecutor,"DISPLAY_VEHICLES B1 16 25", Boolean.FALSE);
        validate(displayVehiclesCommandExecutor,"DISPLAY_VEHICLES B1 7 6", Boolean.FALSE);
    }

    @Test
    void testDisplayVehiclesExecute() {
        Set<String> expected = new HashSet<>(List.of("V1", "V2"));
        Set<String> actual = vehicleRentalService.getBranch("B1").listAvailableVehicles(5, 7);

        assertTrue(CommonUtils.areSetsEqual(expected, actual));
    }
}
