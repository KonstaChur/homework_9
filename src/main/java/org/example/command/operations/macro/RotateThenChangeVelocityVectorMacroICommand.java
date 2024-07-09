package org.example.command.operations.macro;

import org.example.command.ICommand;
import org.example.command.MacroICommand;
import org.example.command.operations.ChangeVelocityVectorICommand;
import org.example.command.operations.RotateICommand;
import org.example.exception.exceptions.CommandException;
import org.example.game_objects.UObject;

public class RotateThenChangeVelocityVectorMacroICommand implements ICommand {
    private final ICommand[] ICommands;

    public RotateThenChangeVelocityVectorMacroICommand(UObject rotatableObject) {
        this.ICommands = new ICommand[] {
                new RotateICommand(rotatableObject),
                new ChangeVelocityVectorICommand(rotatableObject)
        };
    }

    @Override
    public void execute() {
        try {
            new MacroICommand(ICommands).execute();
        } catch (Exception ex) {
            throw new CommandException("error when try to rotate with changing velocity vector");
        }
    }
}
