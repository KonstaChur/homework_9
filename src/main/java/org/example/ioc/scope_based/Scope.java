package org.example.ioc.scope_based;

@FunctionalInterface
interface Scope {
    Object resolve(String key, Object[] args);
}
