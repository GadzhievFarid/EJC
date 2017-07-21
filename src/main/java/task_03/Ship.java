package task_03;

/**
 * Ship is the abstract base class for all ships with different number of decks.
 */
public abstract class Ship {
    protected int size;
    Deck decks[];
    Position head;
    Direction direction;

    abstract public int getSize();

    abstract public Deck[] getDecks();

    abstract public boolean isDead();

    abstract public Deck getDeckByCoordinates(int x, int y);
}

