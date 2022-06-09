package vehiclerental.command;

import com.vehiclerental.command.CommandExecutor;
import com.vehiclerental.model.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddVehicleTest extends CommandExecutorTest {
    CommandExecutor addBranchCommandExecutor = commandExecutorFactory.getCommandExecutor("ADD_BRANCH");
    CommandExecutor addVehicleCommandExecutor = commandExecutorFactory.getCommandExecutor("ADD_VEHICLE");

    @BeforeEach
    public void setup() {
        addBranchCommandExecutor.execute(new Command("ADD_BRANCH B1 CAR"));
        addVehicleCommandExecutor.execute(new Command("ADD_VEHICLE B1 CAR V1 100"));
    }

    @Test
    void testAddVehicleIsValid() {
        validate(addVehicleCommandExecutor,"ADD_VEHICLE ", Boolean.FALSE);
        validate(addVehicleCommandExecutor,"LETS    BREAK  IT  SO BAD IT   NEVER  RECOVERS", Boolean.FALSE);
        validate(addVehicleCommandExecutor,"ADD_VEHICLE LETS    BREAK  IT  SO  BAD IT   NEVER  RECOVERS ", Boolean.FALSE);

        validate(addVehicleCommandExecutor,"ADD_VEHICLE B1 CAR V1 300", Boolean.FALSE);
        validate(addVehicleCommandExecutor,"ADD_VEHICLE B1 CAR V2 COSTLY", Boolean.FALSE);
        validate(addVehicleCommandExecutor,"ADD_VEHICLE B2 CAR V3 300", Boolean.FALSE);

        validate(addVehicleCommandExecutor,"ADD_VEHICLE B1 CAR V4 200", Boolean.TRUE);
    }

    @Test
    void testAddVehicleExecute() {
        addVehicleCommandExecutor.execute(new Command("ADD_VEHICLE B1 CAR V99 5000"));
        assertTrue(vehicleRentalService.getBranch("B1").vehicleIdIsOnboarded("CAR","V99"));
    }
}
