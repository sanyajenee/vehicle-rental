package com.vehiclerental.command;

import com.vehiclerental.OutputPrinter;
import com.vehiclerental.model.Command;
import com.vehiclerental.service.VehicleRentalService;

public abstract class CommandExecutor {
    protected VehicleRentalService vehicleRentalService;
    protected OutputPrinter outputPrinter;

    protected CommandExecutor(VehicleRentalService vehicleRentalService, OutputPrinter outputPrinter) {
        this.vehicleRentalService = vehicleRentalService;
        this.outputPrinter = outputPrinter;
    }

    // might as well pass command.getArgs() instead of command
    // since we use only the args in both isValid and execute
    public abstract boolean isValid(Command command);
    public abstract void execute(Command command);
}
