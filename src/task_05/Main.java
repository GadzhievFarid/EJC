package task_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        String input = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main main = new Main();
        System.out.println("Maximum number of same consecutive chars = " + main.numberOfSameConsecutiveChars(input));
        System.out.println("Number of different chars = " + main.numberOfDifferentChars(input));
    }

    int numberOfSameConsecutiveChars(String string) {
        int currentLength = 1;
        int maxLength = 0;
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i) == string.charAt(i - 1)) {
                currentLength++;
            }
            if (currentLength > maxLength) {
                maxLength = currentLength;
            }
            if (string.charAt(i) != string.charAt(i - 1)) {
                currentLength = 1;
            }
        }
        return maxLength;
    }

    int numberOfDifferentChars(String string) {
        HashSet set = new HashSet<>();
        for (int i = 0; i < string.length(); i++) {
            set.add(string.charAt(i));
        }
        return set.size();
    }
}
