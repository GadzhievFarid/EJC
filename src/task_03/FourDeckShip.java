package task_03;

public class FourDeckShip extends Ship {
    FourDeckShip() {
        this.size = 4;
        this.decks = new Deck[this.size];
        for (int i = 0; i < this.size; i++) {
            decks[i] = new Deck();
        }
    }

    @Override
    boolean isDead() {
        for (int i = 0; i < this.size; i++) {
            if (!decks[i].isHit)
                return false;
        }
        return true;
    }

    @Override
    Deck getDeck(int x, int y) {
        for (Deck deck : this.decks) {
            if (deck.position.equals(new Position(x, y)))
                return deck;
        }
        return null;
    }
}
