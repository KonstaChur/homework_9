package org.example.command.operations.move;

import org.example.command.ICommand;
import org.example.command.MacroICommand;
import org.example.command.injectable.Injectable;
import org.example.command.injectable.InjectableICommand;
import org.example.command.queue.AddElementInQueueICommand;
import org.example.game_objects.UObject;
import org.example.ioc.IoC;
import org.example.operations.Movable;

public class PrepareMovableICommand implements ICommand {
    private final Movable movableAdapter;
    private final Injectable injectable;
    private final MacroICommand macroCommand;

    public PrepareMovableICommand(UObject movableObject, ICommand moveICommand) {
        this.movableAdapter = IoC.resolve("MovableAdapter", movableObject);
        this.injectable = new InjectableICommand();
        var repeatableCommand = new AddElementInQueueICommand((ICommand) injectable);
        this.macroCommand = new MacroICommand(new ICommand[] {
                moveICommand, repeatableCommand
        });
    }

    @Override
    public void execute() {
        injectable.inject(macroCommand);
        movableAdapter.setMovement(injectable);
    }
}
