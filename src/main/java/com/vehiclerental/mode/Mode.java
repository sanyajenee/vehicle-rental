package com.vehiclerental.mode;

import com.vehiclerental.OutputPrinter;
import com.vehiclerental.command.CommandExecutor;
import com.vehiclerental.command.CommandExecutorFactory;
import com.vehiclerental.model.Command;

import java.io.IOException;

public abstract class Mode {
    protected CommandExecutorFactory commandExecutorFactory;
    protected OutputPrinter outputPrinter;

    protected Mode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.outputPrinter = outputPrinter;
    }

    public abstract void start(String[] args) throws IOException;

    protected void process(Command command) {
        String commandName = command.getCommandName();
        CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(commandName);

        if (commandExecutor.isValid(command)) {
            commandExecutor.execute(command);
        }
    }
}
