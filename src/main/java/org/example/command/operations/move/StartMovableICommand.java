package org.example.command.operations.move;


import org.example.command.ICommand;
import org.example.exception.ExceptionHandler;
import org.example.exception.exceptions.CommandException;
import org.example.game_objects.UObject;
import org.example.ioc.IoC;
import org.example.operations.Movable;

public class StartMovableICommand implements ICommand {
    private final Movable movableAdapter;

    public StartMovableICommand(UObject movableObject) {
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
    }

    @Override
    public void execute() {
        try {
            ((ICommand) movableAdapter.getMovement()
                    .orElseThrow(() -> new CommandException("Error when start movable")))
                    .execute();
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
