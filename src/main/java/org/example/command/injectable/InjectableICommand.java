package org.example.command.injectable;


import org.example.command.ICommand;

public class InjectableICommand implements Injectable, ICommand {
    private ICommand ICommandImpl;

    public InjectableICommand() {
    }

    public InjectableICommand(ICommand ICommandImpl) {
        this.ICommandImpl = ICommandImpl;
    }

    @Override
    public void execute() {
        ICommandImpl.execute();
    }

    @Override
    public void inject(ICommand ICommand) {
        ICommandImpl = ICommand;
    }
}
