package org.example.command.operations.macro;

import org.example.command.ICommand;
import org.example.command.MacroICommand;
import org.example.command.operations.BurnFuelICommand;
import org.example.command.operations.CheckFuelICommand;
import org.example.command.operations.move.MoveLinearICommand;
import org.example.exception.exceptions.CommandException;
import org.example.game_objects.UObject;

public class LinearMoveWithFuelConsumptionMacroICommand implements ICommand {
    private final ICommand[] ICommands;

    public LinearMoveWithFuelConsumptionMacroICommand(UObject object) {
        this.ICommands = new ICommand[] {
                new CheckFuelICommand(object),
                new MoveLinearICommand(object),
                new BurnFuelICommand(object)
        };
    }

    @Override
    public void execute() {
        try {
            new MacroICommand(ICommands).execute();
        } catch (Exception ex) {
            throw new CommandException("error when move with fuel consumption");
        }
    }
}

