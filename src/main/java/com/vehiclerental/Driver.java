package com.vehiclerental;

import com.vehiclerental.command.CommandExecutorFactory;
import com.vehiclerental.mode.Mode;
import com.vehiclerental.mode.ModeFactory;
import com.vehiclerental.service.VehicleRentalService;

import java.io.IOException;

public class Driver {
    public static void main(String[] args) throws IOException {
        VehicleRentalService vehicleRentalService = new VehicleRentalService();
        OutputPrinter outputPrinter = new OutputPrinter();

        CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(vehicleRentalService, outputPrinter);

        ModeFactory modeFactory = new ModeFactory(commandExecutorFactory, outputPrinter);
        Mode executionMode = modeFactory.getModeHandler(args);
        executionMode.start(args);
    }
}
