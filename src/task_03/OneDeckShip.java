package task_03;

public class OneDeckShip extends Ship {
    OneDeckShip(){
        state = State.HEALTHY;
        size = 1;
    }

    OneDeckShip(Position head, Direction direction){
        state = State.HEALTHY;
        size = 1;
        this.head = head;
        this.direction = direction;
    }
}
