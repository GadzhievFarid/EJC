package main.java.task_06;

public class Shop extends Thread {
    private static final int priceOfSausage = 10;
    private static int amountOfMoney = 60;

    public static void main(String[] args) {
        for (int i = 0; i < amountOfMoney/priceOfSausage + 1; i++) {
            new Thread(() -> buySausage()).start();
        }

    }

    private synchronized static void buySausage() {
        if (amountOfMoney >= priceOfSausage) {
            amountOfMoney -= priceOfSausage;
            System.err.println("Sausage was bought. New amount of money: " + amountOfMoney);
        } else {
            System.err.println("Not enough money");
        }
    }
}
