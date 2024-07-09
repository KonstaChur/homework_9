package org.example.command.exception;


import org.example.command.ICommand;
import org.example.command.log.LogErrorICommand;
import org.example.command.queue.RerunLastOperationICommand;

public class TryTwiceOrLogExceptionICommand implements ICommand {
    private final ICommand ICommand;
    private Exception exception;
    private int errorsCounter;

    public TryTwiceOrLogExceptionICommand(ICommand ICommand, Exception exception) {
        this.ICommand = ICommand;
        this.exception = exception;
        this.errorsCounter = 0;
    }

    @Override
    public void execute() {
        while (errorsCounter < 2) {
            try {
                new RerunLastOperationICommand().execute();
                break;
            } catch (Exception ex) {
                exception = ex;
                errorsCounter += 1;
            }
        }
        if (errorsCounter == 2){
            new LogErrorICommand(ICommand, exception).execute();
        }
    }
}
