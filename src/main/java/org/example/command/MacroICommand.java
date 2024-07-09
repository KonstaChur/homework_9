package org.example.command;


import org.example.exception.exceptions.CommandException;

import java.util.Arrays;

public class MacroICommand implements ICommand {
    private final ICommand[] ICommands;

    public MacroICommand(ICommand[] ICommands) {
        this.ICommands = ICommands;
    }

    @Override
    public void execute() {
        try {
            Arrays.stream(ICommands).iterator()
                    .forEachRemaining(ICommand::execute);
        } catch (Exception ex) {
            throw new CommandException("error when perform macro command");
        }
    }
}
