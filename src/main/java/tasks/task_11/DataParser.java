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

/**
 *
 * */
class DataParser {
    /**
     * Gets all files in a given directory with given extension.
     *
     * @param dirName   name of directory.
     * @param extension extension of files.
     * @return array of files.
     */
    private File[] getAllCSVFiles(String dirName, String extension) {
        File dir = new File(dirName);
        return dir.listFiles((dir1, filename) -> filename.endsWith(extension));
    }

    /**
     * Parses files in multithreading and creates report.
     */
    void parse() {
        PriorityBlockingQueue<Data> dataQueue = new PriorityBlockingQueue<>();
        File[] files = getAllCSVFiles(inputDirectory, extension);
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

    /**
     * Groups data by users and sites and sums time spent on the same sites by the same user.
     *
     * @param dataArray array with Data to group.
     */
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

    /**
     * Prints report to output file.
     *
     * @param dataArray array with Data to print.
     */
    private void printToFile(ArrayList<Data> dataArray) {
        new File(outputDirectory).mkdir();
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
