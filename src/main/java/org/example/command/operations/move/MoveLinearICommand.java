package org.example.command.operations.move;

import org.example.command.ICommand;
import org.example.exception.ExceptionHandler;
import org.example.exception.exceptions.CheckFuelCommandException;
import org.example.exception.exceptions.CommandException;
import org.example.game_objects.UObject;
import org.example.ioc.IoC;
import org.example.operations.Movable;

public class MoveLinearICommand implements ICommand {
    private final UObject movableObject;
    private final Movable movableAdapter;

    public MoveLinearICommand(UObject movableObject) {
        this.movableObject = movableObject;
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
    }

    @Override
    public void execute() {
        try {
            var velocityVector = getLinearVelocityVector();
            new MoveICommand(movableObject, velocityVector).execute();
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
            throw new CommandException("error when linear move. verdict: " + ex.getMessage());
        }
    }

    private double[] getLinearVelocityVector() {
        var velocity = movableAdapter.getVelocity()
                .orElseThrow(() -> new CheckFuelCommandException("no object velocity found"));
        return new double[]{velocity, 0};
    }
}
