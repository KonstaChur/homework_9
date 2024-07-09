package org.example.game_objects.spaceship;

import org.example.game_objects.UObjectWithId;

import java.util.HashMap;
import java.util.Set;

public class DescribableByIdSpaceshipsObject implements UObjectWithId {
    private static DescribableByIdSpaceshipsObject instance;
    private final HashMap<String, Object> spaceships;

    public DescribableByIdSpaceshipsObject(){
        this.spaceships = new HashMap<>();
    }

    public static synchronized DescribableByIdSpaceshipsObject getInstance() {
        if (instance == null) {instance = new DescribableByIdSpaceshipsObject();}
        return instance;
    }

    @Override
    public Object getProperty(String spaceshipId) {
        return spaceships.getOrDefault(spaceshipId, new SpaceshipObject());
    }

    @Override
    public void setProperty(String spaceshipId, Object spaceship) {
        spaceships.put(spaceshipId, spaceship);
    }

    @Override
    public Set<String> getAllIds() {
        return spaceships.keySet();
    }
}
