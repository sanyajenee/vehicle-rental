package com.vehiclerental.mode;

import com.vehiclerental.OutputPrinter;
import com.vehiclerental.command.CommandExecutorFactory;
import com.vehiclerental.model.Command;

import java.io.*;

public class FileMode extends Mode{
    public static final String MODE_NAME = "FILE_MODE";
    public static final String FAIL_RESPONSE = "Invalid File - file not found";

    public FileMode(CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter) {
        super(commandExecutorFactory, outputPrinter);
    }

    @Override
    public void start(String[] args) throws IOException {
        String fileName = args[0];
        File fp = new File(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(fp))) {
            String input = reader.readLine();
            while (input != null) {
                Command command = new Command(input);
                process(command);
                input = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            outputPrinter.print(FAIL_RESPONSE);
        }
    }
}
