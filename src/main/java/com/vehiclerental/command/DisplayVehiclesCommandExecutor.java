package com.vehiclerental.command;

import com.vehiclerental.OutputPrinter;
import com.vehiclerental.model.Branch;
import com.vehiclerental.model.Command;
import com.vehiclerental.service.VehicleRentalService;

import java.util.List;

import static com.vehiclerental.util.CommonUtils.isInteger;
import static com.vehiclerental.util.CommonUtils.isValidTime;

public class DisplayVehiclesCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "DISPLAY_VEHICLES";
    private static final String DELIMITER = ",";
    private static final String FAIL_RESPONSE = "FAIL";

    public DisplayVehiclesCommandExecutor(VehicleRentalService vehicleRentalService, OutputPrinter outputPrinter) {
        super(vehicleRentalService, outputPrinter);
    }

    @Override
    public boolean isValid(Command command) {
        List<String> args = command.getArgs();

        if (!command.getCommandName().equals(COMMAND_NAME)) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        if (args.size() != 3) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        String branchName = args.get(0);
        String startTimeString = args.get(1);
        String endTimeString = args.get(2);

        if (!vehicleRentalService.branchExists(branchName)) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        if (!isInteger(startTimeString)) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        if (!isInteger(endTimeString)) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        int startTime = Integer.parseInt(startTimeString);
        int endTime = Integer.parseInt(endTimeString);

        if (!isValidTime(startTime)) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        if (!isValidTime(endTime)) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        if (startTime >= endTime) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        return true;
    }

    @Override
    public void execute(Command command) {
        List<String> args = command.getArgs();

        String branchName = args.get(0);
        int startTime = Integer.parseInt(args.get(1));
        int endTime = Integer.parseInt(args.get(2));

        Branch branch = vehicleRentalService.getBranch(branchName);

        StringBuilder sb = new StringBuilder();

        for (String vehicle: branch.listAvailableVehicles(startTime, endTime)) {
            sb.append(vehicle);
            sb.append(DELIMITER);
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        outputPrinter.print(String.valueOf(sb));
    }
}
