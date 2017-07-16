package main.java.task_03;

import java.util.HashMap;
import java.util.Map;

/**
 * Indicates current state of a square on a battlefield.
 */
public enum State {
    EMPTY(0),
    HEALTHY(1),
    UNAVAILABLE(2),
    HIT(3),
    MISS(4);
    static Map<Integer, State> map = new HashMap<>();

    static {
        for (State catalog : State.values()) {
            map.put(catalog.id, catalog);
        }
    }

    public int id;

    State(int id) {
        this.id = id;
    }
}
