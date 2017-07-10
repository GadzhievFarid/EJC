package task_03;

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
