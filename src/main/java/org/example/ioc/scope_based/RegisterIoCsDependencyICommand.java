package org.example.ioc.scope_based;

import org.example.command.ICommand;
import org.example.exception.exceptions.CommandException;

import java.util.function.Function;

public class RegisterIoCsDependencyICommand implements ICommand {
    private final String key;
    private final Function<Object[], Object> strategy;

    public RegisterIoCsDependencyICommand(String key, Function<Object[], Object> strategy) {
        this.key = key;
        this.strategy = strategy;   
    }

    @Override
    public void execute() {
        try {
            ScopeBasedResolveDependencyStrategy
                    .getCurrentScopes()
                    .get()
                    .getDependencies()
                    .putIfAbsent(key, strategy);
        } catch (Exception ex) {
            throw new CommandException("Dependency registration error");
        }
    }
}
