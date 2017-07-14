package task_03;
/**
 * Ship is the abstract base class for all ships with different number of decks.
 * */
public abstract class Ship {
    protected int size;
    Deck decks[];
    Position head;
    Direction direction;

    public int getSize() {
        return size;
    }

    abstract boolean isDead();

    abstract Deck getDeck(int x, int y);
}
