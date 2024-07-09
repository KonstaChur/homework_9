package org.example.command.operations;

import org.example.command.ICommand;
import org.example.exception.ExceptionHandler;
import org.example.game_objects.UObject;
import org.example.ioc.IoC;
import org.example.operations.Rotatable;

public class RotateICommand implements ICommand {
    private final Rotatable rotatableAdapter;

    public RotateICommand(UObject rotatableObject) {
        this.rotatableAdapter = IoC.resolve("RotatableAdapter", rotatableObject);
    }

    @Override
    public void execute() {
        try {
            int direction = rotatableAdapter.getDirection()
                    .orElseThrow(IllegalStateException::new);
            int directionsNumber = rotatableAdapter.getDirectionsNumber()
                    .orElseThrow(IllegalStateException::new);
            int newDirection = 2 * direction % directionsNumber;
            rotatableAdapter.setDirection(newDirection);
        } catch (Exception ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
        }
    }
}
