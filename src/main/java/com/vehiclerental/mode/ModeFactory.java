package com.vehiclerental.mode;

import com.vehiclerental.OutputPrinter;
import com.vehiclerental.command.CommandExecutorFactory;
import com.vehiclerental.exception.InvalidModeException;

import java.util.HashMap;
import java.util.Map;

public class ModeFactory {
    private final Map<String, Mode> modes;

    public ModeFactory(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter) {
        this.modes = new HashMap<>();

        this.modes.put(FileMode.MODE_NAME, new FileMode(commandExecutorFactory, outputPrinter));
    }

    public Mode getModeHandler(String[] args) {
        if (args.length == 1) {
            return modes.get(FileMode.MODE_NAME);
        }

        throw new InvalidModeException();
    }
}
