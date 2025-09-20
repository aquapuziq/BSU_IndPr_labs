import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static boolean checkWordOrNum(String word) {
        if (word.isEmpty()) return true;

        for (int i = 0; i < word.length(); i++) {
            if (!Character.isDigit(word.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static StringBuilder deleteNumsInLine(String line){
        StringBuilder resLine = new StringBuilder();
        String[] words = line.split(" ");

        for (String word : words){
            if (word.matches("^[,.]*\\d+[,.]*$")){
                String punctuation = word.replaceAll("\\d+", "");
                if(!punctuation.isEmpty()){
                    resLine.append(punctuation);
                }
                continue;
            }

            if(!checkWordOrNum(word)){
                continue;
            }

            if (!word.isEmpty()){
                if(!resLine.isEmpty()){
                    resLine.append(" ");
                }
                resLine.append(word);
            }
        }
        return resLine;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<StringBuilder> lines = new ArrayList<>();

        System.out.println("Введите текст для форматирования:");
        while(true) {
            String line = in.nextLine();
            if (line.isEmpty()){
                break;
            }

            StringBuilder resLine = deleteNumsInLine(line);
            lines.add(resLine);
        }
        String text = String.join("\n", lines);
        System.out.println("Текст после форматирования: ");
        System.out.print(text);
    }
}