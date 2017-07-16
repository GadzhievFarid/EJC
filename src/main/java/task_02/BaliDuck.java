package task_02;

public class BaliDuck extends Duck {
    public BaliDuck() {
        flyBehavior = new FlyWithWings();
    }

    @Override
    public int getSpeed() {
        return flyBehavior.getSpeed();
    }
}
