package task_03;

public class ThreeDeckShip extends Ship {
    ThreeDeckShip(){
        state = State.HEALTHY;
        size = 3;
    }

    ThreeDeckShip(Position head, Direction direction){
        state = State.HEALTHY;
        size = 3;
        this.head = head;
        this.direction = direction;
    }
}
