package org.example.exception;

import org.example.command.ICommand;

public class ExceptionHandler {
    private ExceptionHandler() {
        throw new IllegalStateException("Exception handler class");
    }

    public static ICommand handle(Exception exception, ICommand ICommand) {
        return ExceptionHandlerCommandFactory.getInstance(ICommand, exception);
    }
}
