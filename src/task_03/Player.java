package task_03;

public class Player {
    Battleground battleground;

    Player() {
        this.battleground = new Battleground();
    }

    boolean shoot(int x, int y, Battleground battleground) {
        if (battleground.getBoard()[x][y] == 0) {
            battleground.getBoard()[x][y] = 4;
            return false;
        } else if (battleground.getBoard()[x][y] == 1) {
            battleground.getBoard()[x][y] = 3;
            battleground.getShip(x, y).getDeck(x, y).isHit = true;
            if (battleground.getShip(x, y).isDead()) {
                battleground.numberOfAliveShips--;
                battleground.markNeighbours(battleground.getShip(x, y), 4);
            }
            return true;
        } else if (battleground.getBoard()[x][y] == 2) {
            battleground.getBoard()[x][y] = 4;
            return false;
        } else {
            System.out.println(Colors.RED + "You already shot there!");
            return true;
        }
    }
}
