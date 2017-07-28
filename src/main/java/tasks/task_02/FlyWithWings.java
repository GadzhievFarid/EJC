package tasks.task_02;

import java.util.concurrent.ThreadLocalRandom;

public class FlyWithWings implements FlyBehavior {
    public int speed;

    @Override
    public int getSpeed() {
        return speed;
    }

    public void fly() {
        int randomNum = ThreadLocalRandom.current().nextInt(15, 25);
        speed = randomNum << 1;
    }
}
