package org.example.command.operations;

import org.example.command.ICommand;
import org.example.exception.exceptions.CheckFuelCommandException;
import org.example.exception.exceptions.CommandException;
import org.example.game_objects.UObject;
import org.example.ioc.IoC;
import org.example.operations.Fuelable;
public class BurnFuelICommand implements ICommand {
    private final Fuelable fuelableAdapter;

    public BurnFuelICommand(UObject fuelableObject) {
        this.fuelableAdapter = IoC.resolve("FuelableAdapter", fuelableObject);
    }

    @Override
    public void execute() {
        try {
            double fuel = fuelableAdapter.getFuel()
                    .orElseThrow(() -> new CheckFuelCommandException("no fuel found"));
            double fuelConsumption = fuelableAdapter.getFuelConsumption()
                    .orElseThrow(() -> new CheckFuelCommandException("no fuel consumption found"));
            fuelableAdapter.setFuel(fuel - fuelConsumption);
        } catch (Exception ex) {
            throw new CommandException("Error when try to burn fuel. verdict: " + ex.getMessage());
        }
    }
}
