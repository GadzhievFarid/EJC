package tasks.task_11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static tasks.task_11.Config.*;

class DataGenerator {
    void generateFiles() {
        for (int i = 0; i < 12; i++) {
            try (PrintWriter pw = new PrintWriter(new File(inputDirectory + (i + 1) + extension))) {
                pw.println(headerInput);
                for (int j = 0; j < 10; j++) {
                    Data data = new Data();
                    pw.println(data.toString(true));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
