package main.java.task_03;

import java.util.HashMap;
import java.util.Map;

/**
 * The Direction class provides direction in which ship will be building.
 */
public enum Direction {
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);
    static Map<Integer, Direction> map = new HashMap<>();

    static {
        for (Direction catalog : Direction.values()) {
            map.put(catalog.id, catalog);
        }
    }

    public int id;

    Direction(int id) {
        this.id = id;
    }

    public static Direction getByCode(int code) {
        return map.get(code);
    }
}
