package vehiclerental.service;

import com.vehiclerental.model.Branch;
import com.vehiclerental.model.BranchStrategy;
import com.vehiclerental.service.VehicleRentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.vehiclerental.command.AddBranchCommandExecutor.getDefaultDynamicPricingStrategy;
import static com.vehiclerental.command.AddBranchCommandExecutor.getDefaultLeaseStrategy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VehicleRentalServiceTest {
    private VehicleRentalService vehicleRentalService;

    @BeforeEach
    public void setup() {
        vehicleRentalService = new VehicleRentalService();
    }

    @Test
    void testBranchExists() {
        vehicleRentalService.addBranch(new Branch("B1", new BranchStrategy(getDefaultDynamicPricingStrategy(), getDefaultLeaseStrategy())));
        assertTrue(vehicleRentalService.branchExists("B1"));
        assertFalse(vehicleRentalService.branchExists("B99"));
    }
}
