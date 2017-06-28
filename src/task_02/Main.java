package task_02;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        Duck[] ducks = new Duck[5];
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

        for (int i = 0; i < 5; i++) {
            ducks[i].performFly();
            //System.out.println(ducks[i].toString());
            System.out.println(ducks[i].getClass().getSimpleName() + ": " + ducks[i].getSpeed());
        }
    }
}
