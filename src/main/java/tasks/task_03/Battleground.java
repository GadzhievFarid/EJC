package tasks.task_03;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The Battleground class provides board for the game
 * and contains all the ships on that board.
 */
public class Battleground {
    int numberOfAliveShips;

    private ArrayList<Ship> ships;
    private State[][] board;

    Battleground() {
        this.numberOfAliveShips = 10;
        this.ships = new ArrayList<>();
        this.board = new State[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                board[i][j] = State.EMPTY;
            }
        }
    }

    void init() {
        for (int i = 0; i < 4; i++) {
            this.addShip(new OneDeckShip());
            if (i < 3) {
                this.addShip(new TwoDeckShip());
            }
            if (i < 2) {
                this.addShip(new ThreeDeckShip());
            }
            if (i < 1) {
                this.addShip(new FourDeckShip());
            }
        }
    }

    public State[][] getBoard() {
        return board;
    }

    Direction getRandomDirection() {
        int temp = ThreadLocalRandom.current().nextInt(0, 4);
        Direction direction = Direction.getByCode(temp);
        return direction;
    }

    void markNeighbours(int row, int column, State state) {
        if (board[row - 1][column] != State.HEALTHY && board[row - 1][column] != State.HIT) {
            board[row - 1][column] = state;
        }

        if (board[row - 1][column + 1] != State.HEALTHY && board[row - 1][column + 1] != State.HIT) {
            board[row - 1][column + 1] = state;
        }

        if (board[row][column + 1] != State.HEALTHY && board[row][column + 1] != State.HIT) {
            board[row][column + 1] = state;
        }

        if (board[row + 1][column + 1] != State.HEALTHY && board[row + 1][column + 1] != State.HIT) {
            board[row + 1][column + 1] = state;
        }

        if (board[row + 1][column] != State.HEALTHY && board[row + 1][column] != State.HIT) {
            board[row + 1][column] = state;
        }

        if (board[row + 1][column - 1] != State.HEALTHY && board[row + 1][column - 1] != State.HIT) {
            board[row + 1][column - 1] = state;
        }

        if (board[row][column - 1] != State.HEALTHY && board[row][column - 1] != State.HIT) {
            board[row][column - 1] = state;
        }

        if (board[row - 1][column - 1] != State.HEALTHY && board[row - 1][column - 1] != State.HIT) {
            board[row - 1][column - 1] = state;
        }
    }

    void markNeighbours(Ship ship, State state) {
        for (Deck deck : ship.getDecks()) {
            markNeighbours(deck.getPosition().x, deck.getPosition().y, state);
        }
    }

    void addShip(Ship ship) {
        boolean isShipAdded;
        do {
            isShipAdded = true;
            int headX = ThreadLocalRandom.current().nextInt(3, 13);
            int headY = ThreadLocalRandom.current().nextInt(3, 13);
            ship.head = new Position(headX, headY);
            ship.direction = getRandomDirection();
            switch (ship.direction) {
                case UP:
                    for (int i = 0; i < ship.getSize(); i++) {
                        if ((headY + i < 3) || (headY + i > 12)) {
                            isShipAdded = false;
                            break;
                        }
                        if (board[headX][headY + i] != State.EMPTY) {
                            isShipAdded = false;
                            break;
                        }
                        ship.decks[i].position = new Position(headX, headY + i);
                    }
                    break;
                case RIGHT:
                    for (int i = 0; i < ship.getSize(); i++) {
                        if ((headX + i < 3) || (headX + i > 12)) {
                            isShipAdded = false;
                            break;
                        }
                        if (board[headX + i][headY] != State.EMPTY) {
                            isShipAdded = false;
                            break;
                        }
                        ship.decks[i].position = new Position(headX + i, headY);
                    }
                    break;
                case DOWN:
                    for (int i = 0; i < ship.getSize(); i++) {
                        if ((headY - i < 3) || (headY - i > 12)) {
                            isShipAdded = false;
                            break;
                        }
                        if (board[headX][headY - i] != State.EMPTY) {
                            isShipAdded = false;
                            break;
                        }
                        ship.decks[i].position = new Position(headX, headY - i);
                    }
                    break;
                case LEFT:
                    for (int i = 0; i < ship.getSize(); i++) {
                        if ((headX - i < 3) || (headX - i > 12)) {
                            isShipAdded = false;
                            break;
                        }
                        if (board[headX - i][headY] != State.EMPTY) {
                            isShipAdded = false;
                            break;
                        }
                        ship.decks[i].position = new Position(headX - i, headY);
                    }
                    break;
            }
        } while (!isShipAdded);

        ships.add(ship);

        switch (ship.direction) {
            case UP:
                for (int i = 0; i < ship.getSize(); i++) {
                    board[ship.head.x][ship.head.y + i] = State.HEALTHY;
                }
                break;
            case RIGHT:
                for (int i = 0; i < ship.getSize(); i++) {
                    board[ship.head.x + i][ship.head.y] = State.HEALTHY;
                }
                break;
            case DOWN:
                for (int i = 0; i < ship.getSize(); i++) {
                    board[ship.head.x][ship.head.y - i] = State.HEALTHY;
                }
                break;
            case LEFT:
                for (int i = 0; i < ship.getSize(); i++) {
                    board[ship.head.x - i][ship.head.y] = State.HEALTHY;
                }
                break;
        }
        markNeighbours(ship, State.UNAVAILABLE);
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
