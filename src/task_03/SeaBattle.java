package task_03;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class SeaBattle {

    void drawBoards(Battleground playerBattleground, Battleground aiBattleground) {
        char asciiSquare = '\u2588';
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
            char aLetter = 65;
            System.out.print(Colors.RESET + (char) (aLetter + i - 3) + " ");
            for (int j = 3; j < 13; j++) {
                if (playerBattleground.getBoard()[i][j] == State.EMPTY)
                    System.out.print(Colors.WHITE + asciiSquare + " ");
                else if (playerBattleground.getBoard()[i][j] == State.HEALTHY) {
                    System.out.print(Colors.CYAN + asciiSquare + " ");
                } else if (playerBattleground.getBoard()[i][j] == State.UNAVAILABLE) {
                    System.out.print(Colors.WHITE + asciiSquare + " ");
                } else if (playerBattleground.getBoard()[i][j] == State.HIT) {
                    System.out.print(Colors.RED + asciiSquare + " ");
                } else if (playerBattleground.getBoard()[i][j] == State.MISS) {
                    System.out.print(Colors.YELLOW + asciiSquare + " ");
                }
            }
            System.out.print("      ");
            System.out.print(Colors.RESET + (char) (aLetter + i - 3) + " ");
            for (int j = 3; j < 13; j++) {
                if (aiBattleground.getBoard()[i][j] == State.EMPTY)
                    System.out.print(Colors.WHITE + asciiSquare + " ");
                else if (aiBattleground.getBoard()[i][j] == State.HEALTHY) {
                    System.out.print(Colors.WHITE + asciiSquare + " ");
                } else if (aiBattleground.getBoard()[i][j] == State.UNAVAILABLE) {
                    System.out.print(Colors.WHITE + asciiSquare + " ");
                } else if (aiBattleground.getBoard()[i][j] == State.HIT) {
                    System.out.print(Colors.RED + asciiSquare + " ");
                } else if (aiBattleground.getBoard()[i][j] == State.MISS) {
                    System.out.print(Colors.YELLOW + asciiSquare + " ");
                }
            }
            System.out.println();
        }
    }

    void turnPlayer(Player player, Player ai) {

        boolean rightInput = false;
        char x = ' ';
        int y = -1;
        while (!rightInput) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            x = input.charAt(0);
            y = scanner.nextInt();
            if (((int) x > 64 && (int) x < 91 || (int) x > 96 || (int) x < 123)
                    && y > 0 && y < 11)
                rightInput = true;
            else
                System.out.println("You enter wrong coordinates. Try again.");
        }
        if ((int) x > 74)
            x = (char) (x - 32);
        if (player.shoot(x - 62, y + 2, ai.battleground)
                && ai.battleground.numberOfAliveShips != 0) {
            drawBoards(player.battleground, ai.battleground);
            System.out.println(Colors.RED + "Shoot again!");
            turnPlayer(player, ai);
        }
    }

    void turnAI(Player player, Player ai) {
        int x = ThreadLocalRandom.current().nextInt(3, 13);
        int y = ThreadLocalRandom.current().nextInt(3, 13);
        if (player.battleground.getBoard()[x][y] == State.MISS) {
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

        player.battleground.init();
        ai.battleground.init();

        System.out.println(Colors.CYAN + "Greetings! Let's play Sea battle!");
        System.out.println();
        drawBoards(player.battleground, ai.battleground);
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
