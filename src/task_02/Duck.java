package task_02;

public abstract class Duck {
    protected int speed;
    FlyBehavior flyBehavior;

    public Duck() {

    }

    public abstract void display();

    public abstract int getSpeed();

    public abstract void setSpeed(int value);

    public void performFly() {
        flyBehavior.fly();
    }

}
