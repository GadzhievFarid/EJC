package task_03;
/**
 * The Player class provides ability to shoot ships.
 * */
public class Player {
    Battleground battleground;
    boolean toGiveUp;
    Player() {
        this.battleground = new Battleground();
        this.toGiveUp = false;
    }

    boolean shoot(int x, int y, Battleground battleground) {
        if (battleground.getBoard()[x][y] == State.EMPTY) {
            battleground.getBoard()[x][y] = State.MISS;
            return false;
        } else if (battleground.getBoard()[x][y] == State.HEALTHY) {
            battleground.getBoard()[x][y] = State.HIT;
            battleground.getShipByCoordinates(x, y).getDeck(x, y).isHit = true;
            if (battleground.getShipByCoordinates(x, y).isDead()) {
                battleground.numberOfAliveShips--;
                battleground.markNeighbours(battleground.getShipByCoordinates(x, y), State.MISS);
            }
            return true;
        } else if (battleground.getBoard()[x][y] == State.UNAVAILABLE) {
            battleground.getBoard()[x][y] = State.MISS;
            return false;
        } else {
            System.out.println(Colors.RED + "You already shot there!");
            return true;
        }
    }
}
