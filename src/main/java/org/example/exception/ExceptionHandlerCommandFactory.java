package org.example.exception;

import com.google.common.collect.HashBasedTable;
import org.example.command.ICommand;
import org.example.command.exception.DefaultHandleExceptionICommand;
import com.google.common.collect.Table;
import org.example.command.exception.LogErrorExceptionICommand;
import org.example.command.exception.QueueEnrichExceptionICommand;
import org.example.command.log.LogQueueSizeICommand;

public abstract class ExceptionHandlerCommandFactory {

    private static Table<String, String, Runnable> commands;
    private static ICommand ICommandVal;
    private static Exception exceptionVal;
    private static ICommand exceptionICommand;

    private ExceptionHandlerCommandFactory() {
        throw new IllegalStateException("Exception handler command class");
    }

    public static ICommand getInstance(ICommand ICommand, Exception exception) {
        setCommandVal(ICommand);
        setExceptionVal(exception);
        if (commands == null) {initialize();}
        commands
                .row(ICommand.getClass().getName())
                .getOrDefault(
                        exception.getClass().getName(),
                        () -> exceptionICommand = new DefaultHandleExceptionICommand(ICommand, exception)
                ).run();

        return exceptionICommand;
    }

    public static void setCommandVal(ICommand ICommandVal) {
        ExceptionHandlerCommandFactory.ICommandVal = ICommandVal;
    }

    public static void setExceptionVal(Exception exceptionVal) {
        ExceptionHandlerCommandFactory.exceptionVal = exceptionVal;
    }

    private static void initialize() {
        commands = HashBasedTable.create();
        commands.put(
                LogQueueSizeICommand.class.getName(),
                RuntimeException.class.getName(),
                () -> setExceptionCommand(new LogErrorExceptionICommand(ICommandVal, exceptionVal)));
        commands.put(
                LogQueueSizeICommand.class.getName(),
                IllegalStateException.class.getName(),
                () -> setExceptionCommand(new QueueEnrichExceptionICommand(ICommandVal)));
    }

    public static void setExceptionCommand(ICommand exceptionICommand) {
        ExceptionHandlerCommandFactory.exceptionICommand = exceptionICommand;
    }
}
