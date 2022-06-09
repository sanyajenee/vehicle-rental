package com.vehiclerental.command;

import com.vehiclerental.OutputPrinter;
import com.vehiclerental.exception.InvalidCommandException;
import com.vehiclerental.service.VehicleRentalService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {
    private final Map<String, CommandExecutor> commands;

    public CommandExecutorFactory(VehicleRentalService vehicleRentalService, OutputPrinter outputPrinter) {
        this.commands = new HashMap<>();

        this.commands.put(AddBranchCommandExecutor.COMMAND_NAME, new AddBranchCommandExecutor(vehicleRentalService, outputPrinter));
        this.commands.put(AddVehicleCommandExecutor.COMMAND_NAME, new AddVehicleCommandExecutor(vehicleRentalService, outputPrinter));
        this.commands.put(BookCommandExecutor.COMMAND_NAME, new BookCommandExecutor(vehicleRentalService, outputPrinter));
        this.commands.put(DisplayVehiclesCommandExecutor.COMMAND_NAME, new DisplayVehiclesCommandExecutor(vehicleRentalService, outputPrinter));
    }

    public CommandExecutor getCommandExecutor(String commandName) {
        if (commands.containsKey(commandName)) {
            return commands.get(commandName);
        }

        throw new InvalidCommandException();
    }
}
