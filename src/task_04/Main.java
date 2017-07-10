package task_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        String input = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main main = new Main();
        System.out.println(main.returnOdd(input));
        System.out.println(main.isPalindrome(input));
    }

    /**
    * Дано слово s1. Получить слово s2, образованное нечетными буквами слова s1.
    */
    String returnOdd(String string) {
        String oddString = "";
        for (int i = 0; i < string.length(); i += 2)
            oddString += string.charAt(i);
        return oddString;
    }

    /**
     * Проверить, является ли "перевертышем" (см. задачу 9.78) следующая сим-вольная строка после удаления из нее всех пробелов:
     * а) АРГЕНТИНА МАНИТ НЕГРА;
     * б) ПОТ КАК ПОТОП;
     * в) А РОЗА УПАЛА НА ЛАПУ АЗОРА.
     * Во всех задачах последние символы "_", полученные после удаления пробе-лов, не учитывать.
     */
    boolean isPalindrome(String string) {
        string = string.replaceAll(" ", "");
        StringBuilder reversedString = new StringBuilder(string);
        reversedString = reversedString.reverse();
        return reversedString.toString().equalsIgnoreCase(string);
    }
}
