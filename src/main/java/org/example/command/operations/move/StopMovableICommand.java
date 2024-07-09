package org.example.command.operations.move;

import org.example.command.ICommand;
import org.example.command.EmptyICommand;
import org.example.exception.ExceptionHandler;
import org.example.exception.exceptions.CommandException;
import org.example.operations.Movable;

public class StopMovableICommand implements ICommand {
    private final Movable movableAdapter;

    public StopMovableICommand(Movable movableAdapter) {
        this.movableAdapter = movableAdapter;
    }

    @Override
    public void execute() {
        try {
            movableAdapter.getMovement()
                    .orElseThrow(() -> new CommandException("Error when finish movable"))
                    .inject(new EmptyICommand());
            movableAdapter.setMovement(null);
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
