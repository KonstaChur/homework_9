package org.example.queue;

import java.util.HashMap;

public class Games {
    private static Games instance;
    private final HashMap<String, LinkedListCommandQueue> games;

    public Games() {
        this.games = new HashMap<>();
    }

    public static synchronized Games getInstance() {
        if (instance == null) {instance = new Games();}
        return instance;
    }

    public LinkedListCommandQueue getGameQueueById(String gameId) {
        return games.get(gameId);
    }

    public void setGameQueue(String gameId, LinkedListCommandQueue queue) {
        games.put(gameId, queue);
    }
}
