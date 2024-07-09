package org.example.operations;

import org.example.command.injectable.Injectable;

import java.util.Optional;

public interface Movable {

    Optional<double[]> getPosition();

    void setPosition(double[] newValue);

    Optional<Integer> getVelocity();

    Optional<Injectable> getMovement();

    void setMovement(Injectable injectable);

    void finish();
}
