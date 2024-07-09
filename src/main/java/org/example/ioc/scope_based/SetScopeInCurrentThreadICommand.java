package org.example.ioc.scope_based;


import org.example.command.ICommand;

class SetScopeInCurrentThreadICommand implements ICommand {
    private final ScopeImpl scope;

    public SetScopeInCurrentThreadICommand(ScopeImpl scope) {
        this.scope = scope;
    }

    @Override
    public void execute() {
        ScopeBasedResolveDependencyStrategy.getCurrentScopes().set(scope);
    }
}
