package task_03;

;

public class ThreeDeckShip extends Ship {
    ThreeDeckShip() {
        this.size = 3;
        this.decks = new Deck[this.size];
        for (int i = 0; i < this.size; i++) {
            decks[i] = new Deck();
        }
    }

    public int getSize() {
        return size;
    }

    public Deck[] getDecks() {
        return decks;
    }

    public boolean isDead() {
        for (int i = 0; i < this.size; i++) {
            if (!decks[i].isHit) {
                return false;
            }
        }
        return true;
    }

    public Deck getDeckByCoordinates(int x, int y) {
        for (Deck deck : this.decks) {
            if (deck.position.equals(new Position(x, y))) {
                return deck;
            }
        }
        return null;
    }
}
