package org.example.ioc;

import org.example.command.ICommand;

import java.util.function.BiFunction;

public class SetupStrategyICommand implements ICommand {
    private final BiFunction<String, Object[], Object> newStrategy;

    public SetupStrategyICommand(BiFunction<String, Object[], Object> newStrategy) {
        this.newStrategy = newStrategy;
    }

    @Override
    public void execute() {
        IoC.setStrategy(newStrategy);
    }
}
