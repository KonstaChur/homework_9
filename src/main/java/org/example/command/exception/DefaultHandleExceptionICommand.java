package org.example.command.exception;

import org.example.command.ICommand;
import org.example.command.log.LogErrorICommand;

public class DefaultHandleExceptionICommand implements ICommand {
    private final ICommand ICommand;
    private final Exception exception;

    public DefaultHandleExceptionICommand(ICommand ICommand, Exception exception) {
        this.ICommand = ICommand;
        this.exception = exception;
    }
    @Override
    public void execute() {
        new LogErrorICommand(ICommand, exception).execute();
    }
}
