package tasks.task_11;

import org.apache.commons.lang.RandomStringUtils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents data stored in single string in *.csv file.
 */
public class Data implements Comparable<Data> {
    /**
     * Number of strings in all files.
     */
    private static int count = 0;
    
    private int id;
    private String url;
    private long timeSpent;
    private String userName;

    Data(String[] values) {
        this.id = Integer.parseInt(values[0]);
        this.url = values[1];
        this.timeSpent = Long.parseLong(values[2]);
        this.userName = values[3];
    }

    Data() {
        char[] userLetters = {'r', 'a', 'n'};
        char[] urlLetters = {'d', 'o', 'm'};
        count++;
        this.id = count;
        this.url = "www." + RandomStringUtils.random(3, urlLetters) + ".com";
        this.timeSpent = ThreadLocalRandom.current().nextInt(0, 500_000);
        this.userName = RandomStringUtils.random(3, userLetters);
    }

    long getTimeSpent() {
        return timeSpent;
    }

    void setTimeSpent(long timeSpent) {
        this.timeSpent = timeSpent;
    }

    String getUrl() {
        return url;
    }

    String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return id +
                "," + url +
                "," + timeSpent +
                "," + userName;
    }

    /**
     * String representation of data with (for data generating) or without id (for report).
     */
    public String toString(boolean withId) {
        if (withId) {
            return id +
                    "," + url +
                    "," + timeSpent +
                    "," + userName;
        } else {
            return userName +
                    "," + url +
                    "," + timeSpent;
        }
    }

    @Override
    public int compareTo(Data anotherData) {
        return this.userName.compareTo(anotherData.userName);
    }
}
