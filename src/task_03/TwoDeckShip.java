package task_03;

public class TwoDeckShip extends Ship {
    TwoDeckShip(){
        state = State.HEALTHY;
        size = 2;
    }

    TwoDeckShip(Position head, Direction direction){
        state = State.HEALTHY;
        size = 2;
        this.head = head;
        this.direction = direction;
    }
}
