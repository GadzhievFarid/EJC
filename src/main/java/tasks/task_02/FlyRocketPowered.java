package tasks.task_02;

import java.util.concurrent.ThreadLocalRandom;

public class FlyRocketPowered implements FlyBehavior {
    public int speed;

    public void fly() {
        int randomNum = ThreadLocalRandom.current().nextInt(90, 100);
        speed = randomNum >> 1;
    }

    @Override
    public int getSpeed() {
        return speed;
    }
}
