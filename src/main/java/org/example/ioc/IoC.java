package org.example.ioc;

import java.util.function.BiFunction;

public class IoC {
    private static BiFunction<String, Object[], Object> strategy = IoC::defaultStrategy;

    public static BiFunction<String, Object[], Object> getStrategy() {
        return strategy;
    }

    public static void setStrategy(BiFunction<String, Object[], Object> strategy) {
        IoC.strategy = strategy;
    }

    public static <T> T resolve(String key, Object... args) {
        return (T) strategy.apply(key, args);
    }

    private static Object defaultStrategy(String key, Object... args) {
        if ("IoC.SetupStrategy".equals(key)) {
            return new SetupStrategyICommand((BiFunction<String, Object[], Object>) args[0]);
        } else if ("IoC.Default".equals(key)) {
            return IoC.strategy;
        } else {
            throw new IllegalArgumentException("Unknown IoC dependency key " + key);
        }
    }
}
