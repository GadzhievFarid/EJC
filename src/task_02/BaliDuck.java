package task_02;

public class BaliDuck extends Duck {
    public BaliDuck() {
        flyBehavior = new FlyWithWings();
    }

    @Override
    public void setSpeed(int value) {
        this.speed = value;
    }

    @Override
    public int getSpeed() {
        return flyBehavior.getSpeed();
    }

    @Override
    public void display() {

    }
}
