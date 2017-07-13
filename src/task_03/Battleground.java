package task_03;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Battleground {
    ArrayList<Ship> ships;
    int numberOfAliveShips;
    private int[][] board;

    Battleground() {
        this.numberOfAliveShips = 10;
        this.ships = new ArrayList<>();
        this.board = new int[16][16];
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
                board[i][j] = 0;
    }

    public int[][] getBoard() {
        return board;
    }

    Direction getRandomDirection() {
        int temp = ThreadLocalRandom.current().nextInt(0, 4);
        Direction direction = Direction.getByCode(temp);
        return direction;
    }

    void markNeighbours(int x, int y, int value) {
        if (board[x - 1][y] != 1 && board[x - 1][y] != 3)
            board[x - 1][y] = value;

        if (board[x - 1][y + 1] != 1 && board[x - 1][y + 1] != 3)
            board[x - 1][y + 1] = value;

        if (board[x][y + 1] != 1 && board[x][y + 1] != 3)
            board[x][y + 1] = value;

        if (board[x + 1][y + 1] != 1 && board[x + 1][y + 1] != 3)
            board[x + 1][y + 1] = value;

        if (board[x + 1][y] != 1 && board[x + 1][y] != 3)
            board[x + 1][y] = value;

        if (board[x + 1][y - 1] != 1 && board[x + 1][y - 1] != 3)
            board[x + 1][y - 1] = value;

        if (board[x][y - 1] != 1 && board[x][y - 1] != 3)
            board[x][y - 1] = value;

        if (board[x - 1][y - 1] != 1 && board[x - 1][y - 1] != 3)
            board[x - 1][y - 1] = value;
    }

    void markNeighbours(Ship ship, int value) {
        for (Deck deck : ship.decks) {
            markNeighbours(deck.getPosition().x, deck.getPosition().y, value);
        }
    }

    void addShip(Ship ship) {
        boolean shipAdded;
        do {
            shipAdded = true;
            int headx = ThreadLocalRandom.current().nextInt(3, 13);
            int heady = ThreadLocalRandom.current().nextInt(3, 13);
            ship.head = new Position(headx, heady);
            ship.direction = getRandomDirection();
            switch (ship.direction) {
                case UP:
                    for (int i = 0; i < ship.getSize(); i++) {
                        if ((heady + i < 3) || (heady + i > 12)) {
                            shipAdded = false;
                            break;
                        }
                        if (board[headx][heady + i] != 0) {
                            shipAdded = false;
                            break;
                        }
                        ship.decks[i].position = new Position(headx, heady + i);
                    }
                    break;
                case RIGHT:
                    for (int i = 0; i < ship.getSize(); i++) {
                        if ((headx + i < 3) || (headx + i > 12)) {
                            shipAdded = false;
                            break;
                        }
                        if (board[headx + i][heady] != 0) {
                            shipAdded = false;
                            break;
                        }
                        ship.decks[i].position = new Position(headx + i, heady);
                    }
                    break;
                case DOWN:
                    for (int i = 0; i < ship.getSize(); i++) {
                        if ((heady - i < 3) || (heady - i > 12)) {
                            shipAdded = false;
                            break;
                        }
                        if (board[headx][heady - i] != 0) {
                            shipAdded = false;
                            break;
                        }
                        ship.decks[i].position = new Position(headx, heady - i);
                    }
                    break;
                case LEFT:
                    for (int i = 0; i < ship.getSize(); i++) {
                        if ((headx - i < 3) || (headx - i > 12)) {
                            shipAdded = false;
                            break;
                        }
                        if (board[headx - i][heady] != 0) {
                            shipAdded = false;
                            break;
                        }
                        ship.decks[i].position = new Position(headx - i, heady);
                    }
                    break;
            }
        } while (!shipAdded);

        ships.add(ship);

        switch (ship.direction) {
            case UP:
                for (int i = 0; i < ship.getSize(); i++)
                    board[ship.head.x][ship.head.y + i] = 1;
                break;
            case RIGHT:
                for (int i = 0; i < ship.getSize(); i++)
                    board[ship.head.x + i][ship.head.y] = 1;
                break;
            case DOWN:
                for (int i = 0; i < ship.getSize(); i++)
                    board[ship.head.x][ship.head.y - i] = 1;
                break;
            case LEFT:
                for (int i = 0; i < ship.getSize(); i++)
                    board[ship.head.x - i][ship.head.y] = 1;
                break;
        }
        markNeighbours(ship, 2);
    }

    Ship getShipByCoordinates(int x, int y) {
        for (Ship ship : ships) {
            for (int i = 0; i < ship.getSize(); i++) {
                if (ship.decks[i].position.equals(new Position(x, y)))
                    return ship;
            }
        }
        return null;
    }
}
