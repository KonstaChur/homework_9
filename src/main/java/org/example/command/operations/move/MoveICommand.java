package org.example.command.operations.move;

import org.example.command.ICommand;
import org.example.game_objects.UObject;
import org.example.ioc.IoC;
import org.example.operations.Movable;
import org.example.utils.VectorUtils;

public class MoveICommand implements ICommand {
    private final Movable movableAdapter;
    private final double[] velocityVector;

    public MoveICommand(UObject movableObject, double[] velocityVector) {
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
        this.velocityVector = velocityVector;
    }

    @Override
    public void execute() {
        try {
            double[] currentPosition = movableAdapter.getPosition()
                    .orElseThrow(IllegalStateException::new);
            movableAdapter.setPosition(VectorUtils.add(currentPosition, velocityVector));
        } catch (IllegalStateException ex) {
            throw new IllegalStateException("error when try to move");
        }
    }
}
