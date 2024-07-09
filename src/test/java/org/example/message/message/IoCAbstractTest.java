package org.example.message.message;

import org.example.ioc.adapter.InitAdapterGeneratorICommand;
import org.example.ioc.scope_based.InitScopeBasedIoCImplICommand;
import org.example.operations.Fuelable;
import org.example.operations.Movable;
import org.example.operations.Rotatable;
import org.junit.jupiter.api.BeforeAll;

public class IoCAbstractTest {
    @BeforeAll
    static void init() {
        new InitScopeBasedIoCImplICommand().execute();
        new InitAdapterGeneratorICommand(Movable.class).execute();
        new InitAdapterGeneratorICommand(Rotatable.class).execute();
        new InitAdapterGeneratorICommand(Fuelable.class).execute();

    }
}
