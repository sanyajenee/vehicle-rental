package vehiclerental.model;

import com.vehiclerental.model.Branch;
import com.vehiclerental.model.BranchStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.vehiclerental.command.AddBranchCommandExecutor.getDefaultDynamicPricingStrategy;
import static com.vehiclerental.command.AddBranchCommandExecutor.getDefaultLeaseStrategy;
import static com.vehiclerental.util.CommonUtils.areSetsEqual;
import static org.junit.jupiter.api.Assertions.*;

class BranchTest {
    private Branch branch;

    @BeforeEach
    public void setup() {
        branch = new Branch("B1", new BranchStrategy(getDefaultDynamicPricingStrategy(), getDefaultLeaseStrategy()));
        branch.onboardVehicleTypes(List.of("CAR", "BIKE"));
        branch.addVehicle("CAR", "V1", 200);
        branch.addVehicle("BIKE", "BK1", 100);
    }

    @Test
    void testVehicleTypeIsOnboarded() {
        assertTrue(branch.vehicleTypeIsOnboarded("CAR"));
        assertFalse(branch.vehicleTypeIsOnboarded("RADISH"));
    }

    @Test
    void testVehicleIdIsOnboarded() {
        assertTrue(branch.vehicleIdIsOnboarded("CAR","V1"));
        assertFalse(branch.vehicleIdIsOnboarded("BIKE","V2"));
    }

    @Test
    void testBookVehicle() {
        int actual = branch.bookVehicle("BIKE", 2, 5);
        int expected = 300;

        assertEquals(expected, actual);

        actual = branch.bookVehicle("BIKE", 4, 22);
        expected = -1;

        assertEquals(expected, actual);

        actual = branch.bookVehicle("BIKE", 0, 0);
        expected = 0;

        assertEquals(expected, actual);

        actual = branch.bookVehicle("SPACESHIP", 2, 5);
        expected = -2;

        assertEquals(expected, actual);


        branch.addVehicle("CAR", "V2", 300);
        branch.addVehicle("CAR", "V3", 400);
        branch.addVehicle("CAR", "V4", 500);
        branch.addVehicle("CAR", "V5", 600);

        branch.bookVehicle("CAR", 2, 3);
        branch.bookVehicle("CAR", 2, 3);
        branch.bookVehicle("CAR", 2, 3);
        branch.bookVehicle("CAR", 2, 3);

        actual = branch.bookVehicle("CAR",2, 3);
        expected = 660;

        assertEquals(expected, actual);
    }

    @Test
    void testListAvailableVehicles() {
        branch.bookVehicle("CAR", 2, 3);
        Set<String> actual = branch.listAvailableVehicles(3, 6);
        Set<String> expected = new HashSet<>(List.of("V1", "BK1"));

        assertTrue(areSetsEqual(expected, actual));

        branch.bookVehicle("CAR", 5, 6);
        actual = branch.listAvailableVehicles(3, 6);
        expected = new HashSet<>(List.of("BK1"));

        assertTrue(areSetsEqual(expected, actual));
    }
}
