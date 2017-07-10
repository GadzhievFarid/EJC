package task_03;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class SeaBattle {

    void drawBoards(Battleground playerBattleground, Battleground aiBattleground) {
        char square = '\u2588';
        System.out.print("   ");
        for (int i = 0; i < 10; i++) {
            System.out.print(Colors.RESET + (i + 1) + "  ");
        }
        System.out.print("        ");
        for (int i = 0; i < 10; i++) {
            System.out.print(Colors.RESET + (i + 1) + "  ");
        }
        System.out.println();
        for (int i = 3; i < 13; i++) {
            char coord = 65;
            System.out.print(Colors.RESET + (char) (coord + i - 3) + " ");
            for (int j = 3; j < 13; j++) {
                if (playerBattleground.getBoard()[i][j] == 0)
                    System.out.print(Colors.WHITE + square + " ");
                else if (playerBattleground.getBoard()[i][j] == 1) {
                    System.out.print(Colors.CYAN + square + " ");
                } else if (playerBattleground.getBoard()[i][j] == 2) {
                    System.out.print(Colors.WHITE + square + " ");
                } else if (playerBattleground.getBoard()[i][j] == 3) {
                    System.out.print(Colors.RED + square + " ");
                } else if (playerBattleground.getBoard()[i][j] == 4) {
                    System.out.print(Colors.YELLOW + square + " ");
                }
            }
            System.out.print("      ");
            System.out.print(Colors.RESET + (char) (coord + i - 3) + " ");
            for (int j = 3; j < 13; j++) {
                if (aiBattleground.getBoard()[i][j] == 0)
                    System.out.print(Colors.WHITE + square + " ");
                else if (aiBattleground.getBoard()[i][j] == 1) {
                    System.out.print(Colors.WHITE + square + " ");
                } else if (aiBattleground.getBoard()[i][j] == 2) {
                    System.out.print(Colors.WHITE + square + " ");
                } else if (aiBattleground.getBoard()[i][j] == 3) {
                    System.out.print(Colors.RED + square + " ");
                } else if (aiBattleground.getBoard()[i][j] == 4) {
                    System.out.print(Colors.YELLOW + square + " ");
                }
            }
            System.out.println();
        }
    }

    void turnPlayer(Player player, Player ai) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        char x = input.charAt(0);
        int y = scanner.nextInt();
        if (player.shoot(x - 62, y + 2, ai.battleground)
                && ai.battleground.numberOfAliveShips != 0) {
            System.out.println(Colors.RED + "Shoot again!");
            drawBoards(player.battleground, ai.battleground);
            turnPlayer(player, ai);
        }
    }

    void turnAI(Player player, Player ai) {
        int x = ThreadLocalRandom.current().nextInt(3, 13);
        int y = ThreadLocalRandom.current().nextInt(3, 13);
        if (player.battleground.getBoard()[x][y] == 4) {
            turnAI(player, ai);
            return;
        }
        System.out.println(Colors.CYAN + "Computer shot at " + (char) (x + 62) + " " + (y - 2));
        if (ai.shoot(x, y, player.battleground)
                && player.battleground.numberOfAliveShips != 0) {
            drawBoards(player.battleground, ai.battleground);
            turnAI(player, ai);
        }
    }

    void letsPlay() {
        Player player = new Player();
        Player ai = new Player();

        player.battleground.addShip(new FourDeckShip());
        player.battleground.addShip(new ThreeDeckShip());
        player.battleground.addShip(new ThreeDeckShip());
        player.battleground.addShip(new TwoDeckShip());
        player.battleground.addShip(new TwoDeckShip());
        player.battleground.addShip(new TwoDeckShip());
        player.battleground.addShip(new OneDeckShip());
        player.battleground.addShip(new OneDeckShip());
        player.battleground.addShip(new OneDeckShip());
        player.battleground.addShip(new OneDeckShip());

        ai.battleground.addShip(new FourDeckShip());
        ai.battleground.addShip(new ThreeDeckShip());
        ai.battleground.addShip(new ThreeDeckShip());
        ai.battleground.addShip(new TwoDeckShip());
        ai.battleground.addShip(new TwoDeckShip());
        ai.battleground.addShip(new TwoDeckShip());
        ai.battleground.addShip(new OneDeckShip());
        ai.battleground.addShip(new OneDeckShip());
        ai.battleground.addShip(new OneDeckShip());
        ai.battleground.addShip(new OneDeckShip());

        drawBoards(player.battleground, ai.battleground);

        System.out.println(Colors.CYAN + "Greetings! Let's play Sea battle!");
        while (true) {
            System.out.println(Colors.CYAN + "Enter coordinates to shoot!");
            turnPlayer(player, ai);
            turnAI(player, ai);
            drawBoards(player.battleground, ai.battleground);
            if (ai.battleground.numberOfAliveShips == 0) {
                System.out.println(Colors.GREEN + "Congratulations! You won!");
                break;
            }
            if (player.battleground.numberOfAliveShips == 0) {
                System.out.println(Colors.RED + "You lost!");
                break;
            }
        }
    }
}
