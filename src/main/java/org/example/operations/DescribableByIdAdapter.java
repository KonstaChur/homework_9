package org.example.operations;

import org.example.game_objects.UObject;
import org.example.game_objects.UObjectWithId;

import java.util.Set;

public class DescribableByIdAdapter implements DescribableById {
    private final UObjectWithId uObject;

    public DescribableByIdAdapter(UObjectWithId uObject) {
        this.uObject = uObject;
    }

    @Override
    public UObject getObjectById(String id) {
        return (UObject) uObject.getProperty(id);
    }

    @Override
    public void setObjectById(String id, UObject object) {
        uObject.setProperty(id, object);
    }

    @Override
    public Set<String> getAllIds() {
        return uObject.getAllIds();
    }
}
