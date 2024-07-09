package org.example.command.log;


import lombok.extern.slf4j.Slf4j;
import org.example.command.ICommand;

@Slf4j
public class LogErrorICommand implements ICommand {
    private final ICommand ICommand;
    private final Exception exception;

    public LogErrorICommand(ICommand ICommand, Exception exception) {
        this.ICommand = ICommand;
        this.exception = exception;
    }

    @Override
    public void execute() {
        log.error(
                "Error in operation: {}, verdict: {}",
                ICommand.getClass().getName(),
                exception.getMessage());
    }
}
