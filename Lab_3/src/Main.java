import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static boolean checkWordOrNum(String word) {
        if (word.isEmpty()) return false;

        for (int i = 0; i < word.length(); i++) {
            if (!Character.isDigit(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<StringBuilder> lines = new ArrayList<>();

        System.out.println("Введите текст для форматировнаия:");
        while(true) {
            String line = in.nextLine();
            if (line.isEmpty()){
                break;
            }

            StringBuilder resLine = new StringBuilder();
            String[] words = line.split(" ");

            for (String word : words){
                if (!checkWordOrNum(word)){
                    resLine.append(word).append(" ");
                }
            }
            lines.add(resLine);
        }
        String text = String.join("\n", lines);
        System.out.print(text);
    }
}