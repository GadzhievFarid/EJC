package task_03;

public class Main {


    public static void main(String[] args) {
        Battleground battleground = new Battleground();
        battleground.addShip(new FourDeckShip());
        battleground.addShip(new ThreeDeckShip());
        battleground.addShip(new ThreeDeckShip());
        battleground.addShip(new TwoDeckShip());
        battleground.addShip(new TwoDeckShip());
        battleground.addShip(new TwoDeckShip());
        battleground.addShip(new OneDeckShip());
        battleground.addShip(new OneDeckShip());
        battleground.addShip(new OneDeckShip());
        battleground.addShip(new OneDeckShip());
        battleground.drawBoard();
    }
}
