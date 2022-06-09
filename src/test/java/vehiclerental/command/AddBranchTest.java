package vehiclerental.command;

import com.vehiclerental.command.CommandExecutor;
import com.vehiclerental.model.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddBranchTest extends CommandExecutorTest {
    CommandExecutor addBranchCommandExecutor = commandExecutorFactory.getCommandExecutor("ADD_BRANCH");

    @BeforeEach
    public void setup() {
        addBranchCommandExecutor.execute(new Command("ADD_BRANCH B1 CAR"));
    }

    @Test
    void testAddBranchIsValid() {
        validate(addBranchCommandExecutor, "ADD_BRANCH ", Boolean.FALSE);
        validate(addBranchCommandExecutor, "LETS    BREAK  IT  SO BAD IT   NEVER  RECOVERS", Boolean.FALSE);
        validate(addBranchCommandExecutor, "ADD_BRANCH LETS    BREAK  IT  SO  BAD IT   NEVER  RECOVERS ", Boolean.FALSE);

        validate(addBranchCommandExecutor, "ADD_BRANCH B2 CAR", Boolean.TRUE);
        validate(addBranchCommandExecutor, "ADD_BRANCH B1 CAR,VAN", Boolean.FALSE);
    }

    @Test
    void testAddBranchExecute() {
        addBranchCommandExecutor.execute(new Command("ADD_BRANCH B99 POLICE_CAR"));

        assertTrue(vehicleRentalService.branchExists("B99"));
        assertTrue(vehicleRentalService.getBranch("B99").vehicleTypeIsOnboarded("POLICE_CAR"));
    }
}
