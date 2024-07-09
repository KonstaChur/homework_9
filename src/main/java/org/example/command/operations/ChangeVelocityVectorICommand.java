package org.example.command.operations;

import org.example.command.ICommand;
import org.example.exception.ExceptionHandler;
import org.example.game_objects.UObject;
import org.example.ioc.IoC;
import org.example.operations.Rotatable;

public class ChangeVelocityVectorICommand implements ICommand {
    private final Rotatable rotatableAdapter;

    public ChangeVelocityVectorICommand(UObject rotatableObject) {
        this.rotatableAdapter = IoC.resolve("RotatableAdapter", rotatableObject);
    }

    @Override
    public void execute() {
        try {
            int direction = rotatableAdapter.getDirection()
                    .orElseThrow(IllegalStateException::new);
            int directionsNumber = rotatableAdapter.getDirectionsNumber()
                    .orElseThrow(IllegalStateException::new);
            int velocity = rotatableAdapter.getVelocity()
                    .orElseThrow(IllegalStateException::new);
            var velocityVector = new double[]{
                    velocity * Math.cos((double) direction / 360 * directionsNumber),
                    velocity * Math.sin((double) direction / 360 * directionsNumber)};
            rotatableAdapter.setVelocityVector(velocityVector);
        } catch (IllegalStateException ex) {
            ExceptionHandler
                    .handle(ex, this)
                    .execute();
            throw new IllegalStateException("error when try to rotate");
        }
    }
}
