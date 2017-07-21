package task_06;

import java.io.IOException;

public class Rabbit extends Thread {
    public static volatile boolean flag = true;

    public static void main(String[] args) {
        new stopEating().start();
        new eating().start();
    }

    public static class stopEating extends Thread {
        @Override
        public void run() {
            try {
                int stop = System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            flag = false;
            System.err.println("Rabbit is full");
        }
    }

    public static class eating extends Thread {
        @Override
        public void run() {
            while (flag) {
                System.err.println("eating...");
            }
        }
    }
}
