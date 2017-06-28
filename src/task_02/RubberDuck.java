package task_02;

public class RubberDuck extends Duck {
    public RubberDuck() {
        flyBehavior = new FlyRocketPowered();
    }

    @Override
    public int getSpeed() {
        return flyBehavior.getSpeed();
    }

    @Override
    public void setSpeed(int value) {

    }

    @Override
    public void display() {

    }

}
