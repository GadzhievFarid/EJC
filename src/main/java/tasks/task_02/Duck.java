package tasks.task_02;

public abstract class Duck {
    protected int speed;
    FlyBehavior flyBehavior;

    public Duck() {

    }

    public abstract int getSpeed();

    public void performFly() {
        flyBehavior.fly();
    }
}
