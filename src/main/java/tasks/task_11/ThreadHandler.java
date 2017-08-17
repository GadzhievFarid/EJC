package tasks.task_11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.PriorityBlockingQueue;

import static tasks.task_11.Config.delimiter;

/**
 * Thread for reading data from file.
 */
public class ThreadHandler implements Runnable {
    private PriorityBlockingQueue<Data> dataQueue;
    private File file;

    ThreadHandler(PriorityBlockingQueue<Data> dataQueue, File file) {
        this.file = file;
        this.dataQueue = dataQueue;
    }

    /**
     * Reads data from file and adds to dataQueue.
     */
    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            reader.readLine();
            while ((currentLine = reader.readLine()) != null) {
                String[] values = currentLine.split(delimiter);
                Data data = new Data(values);
                dataQueue.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
