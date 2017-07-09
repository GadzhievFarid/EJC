package task_03;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Battleground {
    ArrayList<Ship> ships;
    private int[][] board;

    Battleground() {
        ships = new ArrayList<>();
        board = new int[16][16];
        for (int i = 0; i < 16; i++)
            for (int j = 0; j < 16; j++)
                board[i][j] = 0;
    }

    Direction getDirection(Ship ship) {
        int temp = ThreadLocalRandom.current().nextInt(0, 4);
        Direction direction = Direction.getByCode(temp);
        return direction;
    }

    void markNeighbours(int x, int y) {
        if (board[x - 1][y] != 1)
            board[x - 1][y] = 2;

        if (board[x - 1][y + 1] != 1)
            board[x - 1][y + 1] = 2;

        if (board[x][y + 1] != 1)
            board[x][y + 1] = 2;

        if (board[x + 1][y + 1] != 1)
            board[x + 1][y + 1] = 2;

        if (board[x + 1][y] != 1)
            board[x + 1][y] = 2;

        if (board[x + 1][y - 1] != 1)
            board[x + 1][y - 1] = 2;

        if (board[x][y - 1] != 1)
            board[x][y - 1] = 2;

        if (board[x - 1][y - 1] != 1)
            board[x - 1][y - 1] = 2;
    }

    void addShip(Ship ship) {
        boolean shipAdded;
        do {
            shipAdded = true;
            int headx = ThreadLocalRandom.current().nextInt(3, 13);
            int heady = ThreadLocalRandom.current().nextInt(3, 13);
            ship.head = new Position(headx, heady);
            ship.direction = getDirection(ship);
            switch (ship.direction) {
                case UP:
                    for (int i = 0; i < ship.size; i++) {
                        if ((headx + i < 3) || (headx + i > 12)) {
                            shipAdded = false;
                            break;
                        }
                        if (board[headx][heady + i] != 0) {
                            shipAdded = false;
                            break;
                        }
                    }
                    break;
                case RIGHT:
                    for (int i = 0; i < ship.size; i++) {
                        if ((headx + i < 3) || (headx + i > 12)) {
                            shipAdded = false;
                            break;
                        }
                        if (board[headx + i][heady] != 0) {
                            shipAdded = false;
                            break;
                        }
                    }
                    break;
                case DOWN:
                    for (int i = 0; i < ship.size; i++) {
                        if ((heady - i < 3) || (heady - i > 12)) {
                            shipAdded = false;
                            break;
                        }
                        if (board[headx][heady - i] != 0) {
                            shipAdded = false;
                            break;
                        }
                    }
                case LEFT:
                    for (int i = 0; i < ship.size; i++) {
                        if ((headx - i < 3) || (headx - i > 12)) {
                            shipAdded = false;
                            break;
                        }
                        if (board[headx - i][heady] != 0) {
                            shipAdded = false;
                            break;
                        }
                    }
            }
        } while (!shipAdded);

        ships.add(ship);

        switch (ship.direction) {
            case UP:
                for (int i = 0; i < ship.size; i++) {
                    board[ship.head.x][ship.head.y + i] = 1;
                    markNeighbours(ship.head.x, ship.head.y + i);
                }
                break;
            case RIGHT:
                for (int i = 0; i < ship.size; i++) {
                    board[ship.head.x + i][ship.head.y] = 1;
                    markNeighbours(ship.head.x + i, ship.head.y);
                }
                break;
            case DOWN:
                for (int i = 0; i < ship.size; i++) {
                    board[ship.head.x][ship.head.y - i] = 1;
                    markNeighbours(ship.head.x, ship.head.y - i);
                }
                break;
            case LEFT:
                for (int i = 0; i < ship.size; i++) {
                    board[ship.head.x - i][ship.head.y] = 1;
                    markNeighbours(ship.head.x - i, ship.head.y);
                }
                break;
        }
    }

    void drawBoard() {
        char square = '\u2588';
        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 3; i < 13; i++) {
            char coord = 65;
            System.out.print(Colors.RESET + (char)(coord + i - 3) + " ");
            for (int j = 3; j < 13; j++) {

                if (board[i][j] == 0)
                    System.out.print(Colors.WHITE + square);
                else if (board[i][j] == 1) {
                    System.out.print(Colors.CYAN + square);
                } else if (board[i][j] == 2) {
                    System.out.print(Colors.YELLOW + square);
                }
            }
            System.out.println();
        }
    }
}
