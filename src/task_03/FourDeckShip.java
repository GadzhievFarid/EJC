package task_03;

public class FourDeckShip extends Ship {
    FourDeckShip() {
        state = State.HEALTHY;
        size = 4;
    }

    FourDeckShip(Position head, Direction direction) {
        state = State.HEALTHY;
        size = 4;
        this.head = head;
        this.direction = direction;
    }

}
