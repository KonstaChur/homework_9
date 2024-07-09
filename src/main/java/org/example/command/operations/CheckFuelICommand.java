package org.example.command.operations;

import org.example.command.ICommand;
import org.example.exception.exceptions.CheckFuelCommandException;
import org.example.exception.exceptions.CommandException;
import org.example.game_objects.UObject;
import org.example.ioc.IoC;
import org.example.operations.Fuelable;

public class CheckFuelICommand implements ICommand {
    private final Fuelable fuelableAdapter;

    public CheckFuelICommand(UObject fuelableObject) {
        this.fuelableAdapter = IoC.resolve("FuelableAdapter", fuelableObject);
    }

    @Override
    public void execute() {
        try {
            double fuel = fuelableAdapter.getFuel()
                    .orElseThrow(() -> new CheckFuelCommandException("no fuel found"));
            double fuelConsumption = fuelableAdapter.getFuelConsumption()
                    .orElseThrow(() -> new CheckFuelCommandException("no fuel consumption found"));
            if (fuel < fuelConsumption) {
                throw new CheckFuelCommandException("not enough fuel");
            }
        } catch (Exception ex) {
            throw new CommandException("Error when try to check fuel. verdict: " + ex.getMessage());
        }
    }
}
