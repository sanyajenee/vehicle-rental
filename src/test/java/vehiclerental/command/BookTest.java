package vehiclerental.command;

import com.vehiclerental.command.CommandExecutor;
import com.vehiclerental.model.Command;
import com.vehiclerental.util.CommonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest extends CommandExecutorTest {
    CommandExecutor addBranchCommandExecutor = commandExecutorFactory.getCommandExecutor("ADD_BRANCH");
    CommandExecutor addVehicleCommandExecutor = commandExecutorFactory.getCommandExecutor("ADD_VEHICLE");
    CommandExecutor bookCommandExecutor = commandExecutorFactory.getCommandExecutor("BOOK");

    @BeforeEach
    public void setup() {
        addBranchCommandExecutor.execute(new Command("ADD_BRANCH B1 CAR"));
        addVehicleCommandExecutor.execute(new Command("ADD_VEHICLE B1 CAR V1 100"));
    }

    @Test
    void testBookIsValid() {
        validate(bookCommandExecutor,"BOOK ", Boolean.FALSE);
        validate(bookCommandExecutor,"LETS    BREAK  IT  SO BAD IT   NEVER  RECOVERS", Boolean.FALSE);
        validate(bookCommandExecutor,"BOOK LETS    BREAK  IT  SO  BAD IT   NEVER  RECOVERS ", Boolean.FALSE);

        validate(bookCommandExecutor,"BOOK B1 CAR 1 4", Boolean.TRUE);

        validate(bookCommandExecutor,"BOOK B1 CAR 3 22", Boolean.TRUE);

        validate(bookCommandExecutor,"BOOK B99 CAR 1 4", Boolean.FALSE);
        validate(bookCommandExecutor,"BOOK B1 VAN 1 4", Boolean.FALSE);
        validate(bookCommandExecutor,"BOOK B1 CAR START 4", Boolean.FALSE);
        validate(bookCommandExecutor,"BOOK B1 CAR 3 END", Boolean.FALSE);
        validate(bookCommandExecutor,"BOOK B1 CAR -1 1", Boolean.FALSE);
        validate(bookCommandExecutor,"BOOK B1 CAR 16 25", Boolean.FALSE);
        validate(bookCommandExecutor,"BOOK B1 CAR 7 6", Boolean.FALSE);
    }

    @Test
    void testBookExecute() {
        bookCommandExecutor.execute(new Command("BOOK B1 CAR 3 7"));

        Set<String> expected = new HashSet<>();
        Set<String> actual = vehicleRentalService.getBranch("B1").listAvailableVehicles(3, 7);

        assertTrue(CommonUtils.areSetsEqual(expected, actual));
    }
}
