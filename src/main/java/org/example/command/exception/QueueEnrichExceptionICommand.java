package org.example.command.exception;


import org.example.command.ICommand;
import org.example.command.queue.AddElementInQueueICommand;

public class QueueEnrichExceptionICommand implements ICommand {
    private final ICommand ICommand;

    public QueueEnrichExceptionICommand(ICommand ICommand) {
        this.ICommand = ICommand;
    }

    @Override
    public void execute() {
        new AddElementInQueueICommand(ICommand).execute();
    }
}
