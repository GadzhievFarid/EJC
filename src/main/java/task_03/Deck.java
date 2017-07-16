package main.java.task_03;

/**
 * The Deck class provides base object for ships building.
 */
public class Deck {
    boolean isHit;
    Position position;

    Deck() {
        this.isHit = false;
    }

    public Position getPosition() {
        return position;
    }
}
