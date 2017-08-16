package tasks.task_11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import static tasks.task_11.Config.*;

class DataParser {

    private File[] getAllCSVFiles(String dirName) {
        File dir = new File(dirName);
        return dir.listFiles((dir1, filename) -> filename.endsWith(extension));
    }

    void parse() {
        PriorityBlockingQueue<Data> dataQueue = new PriorityBlockingQueue<>();
        File[] files = getAllCSVFiles(inputDirectory);
        int threadNumber = files.length >= maxThreadNumber ? maxThreadNumber : files.length;

        ExecutorService service = Executors.newFixedThreadPool(threadNumber);
        for (int i = 0; i < files.length; i++) {
            service.execute(new ThreadHandler(dataQueue, files[i]));
            System.out.println("File " + (i + 1) + " has been processed");
        }
        service.shutdown();
        try {
            if (!service.awaitTermination(60, TimeUnit.SECONDS)) {
                service.shutdownNow();
                if (!service.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("service did not terminate");
            }
        } catch (InterruptedException ie) {
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }

        ArrayList<Data> dataArray = new ArrayList<>();
        dataQueue.drainTo(dataArray);

        groupByUserAndSites(dataArray);

        printToFile(dataArray);
        System.out.println("Job's done!");
    }

    private void groupByUserAndSites(ArrayList<Data> dataArray) {
        for (int i = 0; i < dataArray.size() - 1; i++) {
            Data currentElement = dataArray.get(i);
            Data nextElement = dataArray.get(i + 1);
            if ((currentElement.getUserName().equals(nextElement.getUserName())) &&
                    currentElement.getUrl().equals(nextElement.getUrl())) {
                currentElement.setTimeSpent(currentElement.getTimeSpent()
                        + nextElement.getTimeSpent());
                dataArray.remove(nextElement);
                i--;
            }
        }
    }

    private void printToFile(ArrayList<Data> dataArray) {
        try (PrintWriter pw = new PrintWriter(new File(outputDirectory + "report" + extension))) {
            pw.println(headerOutput);
            for (Data data : dataArray) {
                pw.println(data.toString(false));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
