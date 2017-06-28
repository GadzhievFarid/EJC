package task_02;

public class PekingDuck extends Duck {
    public PekingDuck() {
        flyBehavior = new FlyNoWay();
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
