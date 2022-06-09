package com.vehiclerental.model;

import com.vehiclerental.exception.InvalidCommandException;

import java.util.List;

import static com.vehiclerental.util.CommonUtils.splitIntoList;

public class Command {
    private static final String SPACE = " ";
    private final String commandName;
    private final List<String> args;

    public Command(String input) {
        List<String> rawArgs = splitIntoList(input, SPACE);

        if (rawArgs.isEmpty()) {
            throw new InvalidCommandException();
        }

        this.commandName = rawArgs.get(0).toUpperCase();
        rawArgs.remove(0);

        this.args = rawArgs;
    }

    public String getCommandName() {
        return commandName;
    }

    public List<String> getArgs() {
        return args;
    }
}
