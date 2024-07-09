package org.example.command.operations;


import org.example.command.ICommand;
import org.example.command.operations.move.StopMovableICommand;
import org.example.operations.Movable;

public class StopICommand implements ICommand {
    private final ICommand ICommand;

    public StopICommand(Movable movableAdapter) {
        this.ICommand = new StopMovableICommand(movableAdapter);
    }

    @Override
    public void execute() {
        ICommand.execute();
    }
}
