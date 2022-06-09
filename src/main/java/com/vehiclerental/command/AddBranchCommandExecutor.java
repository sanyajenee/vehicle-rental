package com.vehiclerental.command;

import com.vehiclerental.OutputPrinter;
import com.vehiclerental.model.*;
import com.vehiclerental.service.VehicleRentalService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vehiclerental.util.CommonUtils.splitIntoList;

public class AddBranchCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "ADD_BRANCH";
    private static final String DELIMITER = ",";
    private static final String FAIL_RESPONSE = "FALSE";
    private static final String SUCCESS_RESPONSE = "TRUE";

    public AddBranchCommandExecutor(VehicleRentalService vehicleRentalService, OutputPrinter outputPrinter) {
        super(vehicleRentalService, outputPrinter);
    }

    @Override
    public boolean isValid(Command command) {
        List<String> args = command.getArgs();

        if (!command.getCommandName().equals(COMMAND_NAME)) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        if (args.size() != 2) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        String branchName = args.get(0);

        if (vehicleRentalService.branchExists(branchName)) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        return true;
    }

    @Override
    public void execute(Command command) {
        List<String> args = command.getArgs();

        String branchName = args.get(0);
        List<String> vehicleTypes = splitIntoList(args.get(1), DELIMITER);

        Branch branch = new Branch(branchName, new BranchStrategy(getDefaultDynamicPricingStrategy(), getDefaultLeaseStrategy()));
        branch.onboardVehicleTypes(vehicleTypes);

        vehicleRentalService.addBranch(branch);

        outputPrinter.print(SUCCESS_RESPONSE);
    }

    public static Map<String, PricingStrategy> getDefaultDynamicPricingStrategy() {
        Map<String, PricingStrategy> dynamicPricingStrategy = new HashMap<>();
        dynamicPricingStrategy.put("CAR", new PricingStrategy(80, 10));
        return dynamicPricingStrategy;
    }

    public static Comparator<Vehicle> getDefaultLeaseStrategy() {
        return (v1, v2) -> {
            if (v1.getId().equals(v2.getId())) {
                return 0;
            }

            int priceCompare = Integer.compare(v1.getPrice(), v2.getPrice());

            if (priceCompare == 0) {
                return v1.getId().compareTo(v2.getId());
            }

            return priceCompare;
        };
    }
}
