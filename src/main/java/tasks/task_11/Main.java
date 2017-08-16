package tasks.task_11;

public class Main {
    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generateFiles();

        DataParser parser = new DataParser();
        parser.parse();
    }
}
