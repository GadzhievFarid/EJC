package task_02;

public class FalklandSteamerDuck extends Duck {
    public FalklandSteamerDuck() {
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
