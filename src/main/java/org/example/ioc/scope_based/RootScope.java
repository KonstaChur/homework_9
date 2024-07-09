package org.example.ioc.scope_based;

import java.util.function.BiFunction;

class RootScope implements Scope {
    private final BiFunction<String, Object[], Object> strategy;

    public RootScope(BiFunction<String, Object[], Object> strategy) {
        this.strategy = strategy;
    }

    @Override
    public Object resolve(String key, Object... args) {
        return strategy.apply(key, args);
    }
}
