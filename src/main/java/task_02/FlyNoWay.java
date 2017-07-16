package task_02;

public class FlyNoWay implements FlyBehavior {
    public int speed;

    @Override
    public int getSpeed() {
        return speed;
    }

    public void fly() {
        speed = 0;
    }
}
