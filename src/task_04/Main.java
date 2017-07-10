package task_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String s1 = reader.readLine();
            Main main = new Main();
            System.out.println(main.returnOdd(s1));
            System.out.println(main.isPalindrome(s1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String returnOdd(String s1) {
        String s2 = "";
        for (int i = 0; i < s1.length(); i += 2)
            s2 += s1.charAt(i);
        return s2;
    }

    boolean isPalindrome(String s) {
        s = s.replaceAll(" ", "");
        StringBuilder reverseS = new StringBuilder(s);
        reverseS = reverseS.reverse();
        return reverseS.toString().equalsIgnoreCase(s);
    }

}
