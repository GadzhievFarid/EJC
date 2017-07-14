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
        char row = ' ';
        int column = -1;
        while (!rightInput) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            row = input.charAt(0);
            if(row == 'q' || row == 'Q'){
                player.toGiveUp = true;
                return;
            }
            column = scanner.nextInt();
            if (Character.isLetter(row) && ((int) row > 64 && (int) row < 75 || (int) row > 96 && (int) row < 107)
                    && column > 0 && column < 11)
                rightInput = true;
            else
                System.out.println("You enter wrong coordinates. Try again.");
        }
        if ((int) row > 74)
            row = (char) (row - 32);
        if (player.shoot(row - 62, column + 2, ai.battleground)
                && ai.battleground.numberOfAliveShips != 0) {
            drawBoards(player.battleground, ai.battleground);
            System.out.println(Colors.RED + "Shoot again!");
            turnPlayer(player, ai);
        }
    }

    void turnAI(Player player, Player ai) {
        int row = ThreadLocalRandom.current().nextInt(3, 13);
        int column = ThreadLocalRandom.current().nextInt(3, 13);
        if (player.battleground.getBoard()[row][column] == State.MISS) {
            turnAI(player, ai);
            return;
        }
        System.out.println(Colors.CYAN + "Computer shot at " + (char) (row + 62) + " " + (column - 2));
        if (ai.shoot(row, column, player.battleground)
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
        System.out.println("If you want to leave type q");
        System.out.println();
        drawBoards(player.battleground, ai.battleground);
        while (player.toGiveUp == false) {
            System.out.println(Colors.CYAN + "Enter coordinates to shoot!");
            turnPlayer(player, ai);
            if(player.toGiveUp == true){
                System.out.println("It's a pity that you give up.");
                break;
            }
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
