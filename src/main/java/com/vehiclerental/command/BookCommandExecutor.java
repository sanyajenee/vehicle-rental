package com.vehiclerental.command;

import com.vehiclerental.OutputPrinter;
import com.vehiclerental.model.Branch;
import com.vehiclerental.model.Command;
import com.vehiclerental.service.VehicleRentalService;

import java.util.List;

import static com.vehiclerental.util.CommonUtils.isInteger;
import static com.vehiclerental.util.CommonUtils.isValidTime;

public class BookCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "BOOK";
    private static final String FAIL_RESPONSE = "-1";

    public BookCommandExecutor(VehicleRentalService vehicleRentalService, OutputPrinter outputPrinter) {
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
        String startTimeString = args.get(2);
        String endTimeString = args.get(3);

        if (!vehicleRentalService.branchExists(branchName)) {
            outputPrinter.print(FAIL_RESPONSE);
            return false;
        }

        Branch branch = vehicleRentalService.getBranch(branchName);

        if (!branch.vehicleTypeIsOnboarded(vehicleType)) {
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
        String vehicleType = args.get(1);
        int startTime = Integer.parseInt(args.get(2));
        int endTime = Integer.parseInt(args.get(3));

        Branch branch = vehicleRentalService.getBranch(branchName);
        int cost = branch.bookVehicle(vehicleType, startTime, endTime);

        outputPrinter.print(String.valueOf(cost));
    }
}
