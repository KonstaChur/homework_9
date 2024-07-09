package org.example.command.operations;

import org.example.command.ICommand;
import org.example.game_objects.UObject;

public class UpdateObjectPropertyICommand implements ICommand {
    private final UObject uObject;
    private final String key;
    private final Object newValue;

    public UpdateObjectPropertyICommand(UObject uObject, String key, Object newValue) {
        this.uObject = uObject;
        this.key = key;
        this.newValue = newValue;
    }

    @Override
    public void execute() {
        uObject.setProperty(key, newValue);
    }
}
