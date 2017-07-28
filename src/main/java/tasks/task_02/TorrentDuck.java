package tasks.task_02;

public class TorrentDuck extends Duck {
    public TorrentDuck() {
        flyBehavior = new FlyWithWings();
    }

    @Override
    public int getSpeed() {
        return flyBehavior.getSpeed();
    }
}
