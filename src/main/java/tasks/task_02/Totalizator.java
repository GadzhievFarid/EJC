package tasks.task_02;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Totalizator {


    static private Duck[] init(Duck ducks[]) {
        for (int i = 0; i < 5; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 5);
            Duck currentDuck;
            switch (randomNum) {
                case 0:
                    currentDuck = new BaliDuck();
                    break;
                case 1:
                    currentDuck = new PekingDuck();
                    break;
                case 2:
                    currentDuck = new RubberDuck();
                    break;
                case 3:
                    currentDuck = new TorrentDuck();
                    break;
                case 4:
                    currentDuck = new FalklandSteamerDuck();
                    break;
                default:
                    currentDuck = new PekingDuck();
            }
            ducks[i] = currentDuck;
        }
        return ducks;
    }

    void letsPlay() throws InterruptedException {
        int cash = 500;
        System.out.println("Welcome to the totalizator");

        while (cash > 0) {
            Duck[] ducks = new Duck[5];
            init(ducks);

            System.out.println("You have " + cash + "$");
            System.out.println("Choose your bet:");
            for (int i = 0; i < 5; i++) {
                System.out.println((i + 1) + ". " + ducks[i].getClass().getSimpleName());
            }

            int chosenDuck;
            Scanner sc = new Scanner(System.in);
            chosenDuck = sc.nextInt();

            System.out.println("You chose " + chosenDuck + ". " +
                    ducks[chosenDuck - 1].getClass().getSimpleName() + ".");

            String anim = "|/-\\";
            for (int x = 0; x < 6; x++) {
                String data = "\r" + anim.charAt(x % anim.length()) + " " + x * 20 + "%";
                System.out.print(data);
                Thread.sleep(500);
            }
            System.out.println();
            int maxSpeed = 0;
            int indMaxSpeed = 0;
            for (int i = 0; i < 5; i++) {
                ducks[i].performFly();
                int currentSpeed = ducks[i].flyBehavior.getSpeed();
                if (maxSpeed < currentSpeed) {
                    maxSpeed = currentSpeed;
                    indMaxSpeed = i;
                }
            }

            System.out.println((indMaxSpeed + 1) + "." +
                    ducks[indMaxSpeed].getClass().getSimpleName() + " finished first!");

            if (ducks[chosenDuck - 1].flyBehavior.getSpeed() >= maxSpeed) {
                System.out.println("Your duck flew with speed "
                        + ducks[chosenDuck - 1].flyBehavior.getSpeed() + " and it was the fastest!");
                System.out.println("You won!");
                cash += 200;
            } else {
                System.out.println("Your duck flew with speed "
                        + ducks[chosenDuck - 1].flyBehavior.getSpeed() + ", but "
                        + (indMaxSpeed + 1) + "." + ducks[indMaxSpeed].getClass().getSimpleName() +
                        " was faster: it's speed was " + ducks[indMaxSpeed].flyBehavior.getSpeed() + ".");
                System.out.println("You lost!");
                cash -= 200;
            }
        }

        System.out.println("Good luck next time!");
    }
}
