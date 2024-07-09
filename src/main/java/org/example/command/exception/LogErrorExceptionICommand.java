package org.example.command.exception;


import org.example.command.ICommand;
import org.example.command.log.LogErrorICommand;

public class LogErrorExceptionICommand implements ICommand {
    private final ICommand ICommand;
    private final Exception exception;

    public LogErrorExceptionICommand(ICommand ICommand, Exception exception) {
        this.ICommand = ICommand;
        this.exception = exception;
    }

    @Override
    public void execute() {
        new LogErrorICommand(ICommand, exception).execute();
    }
}
