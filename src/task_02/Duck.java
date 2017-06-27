package task_02;

public abstract class Duck {
    FlyBehavior flyBehavior;

    public Duck(){

    }

    public abstract void display();

    public abstract void setSpeed();

    public void perfomFly(){
        flyBehavior.fly();
    }

}
