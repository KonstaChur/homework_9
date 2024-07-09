package org.example.operations;


import org.example.game_objects.UObject;

import java.util.Set;

public interface DescribableById {
    UObject getObjectById(String id);

    void setObjectById(String id, UObject object);

    Set<String> getAllIds();
}
