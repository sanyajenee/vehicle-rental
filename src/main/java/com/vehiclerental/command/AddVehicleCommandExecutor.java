package com.vehiclerental.command;

import com.vehiclerental.OutputPrinter;
import com.vehiclerental.model.Branch;
import com.vehiclerental.model.Command;
import com.vehiclerental.service.VehicleRentalService;

import java.util.List;

import static com.vehiclerental.util.CommonUtils.isInteger;

public class AddVehicleCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "ADD_VEHICLE";
    private static final String FAIL_RESPONSE = "FALSE";
    private static final String SUCCESS_RESPONSE = "TRUE";

    public AddVehicleCommandExecutor(VehicleRentalService vehicleRentalService, OutputPrinter outputPrinter) {
        super(vehicleRentalService, outputPrinter);
    }

    @Override
    public boolean isValid(Command command) {
        List<String> args = command.getArgs();

        if (!command.getCommandName().equals(COMMAND_NAME)) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        if (args.size() != 4) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        String branchName = args.get(0);
        String vehicleType = args.get(1);
        String vehicleId = args.get(2);
        String priceString = args.get(3);

        if (!vehicleRentalService.branchExists(branchName)) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        Branch branch = vehicleRentalService.getBranch(branchName);

        if (!branch.vehicleTypeIsOnboarded(vehicleType)) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        if (branch.vehicleIdIsOnboarded(vehicleType, vehicleId)) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        if (!isInteger(priceString)) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        return true;
    }

    @Override
    public void execute(Command command) {
        List<String> args = command.getArgs();

        String branchName = args.get(0);
        String vehicleType = args.get(1);
        String vehicleId = args.get(2);
        Integer price = Integer.parseInt(args.get(3));

        Branch branch = vehicleRentalService.getBranch(branchName);
        branch.addVehicle(vehicleType, vehicleId, price);

        outputPrinter.print(SUCCESS_RESPONSE);
    }
}
