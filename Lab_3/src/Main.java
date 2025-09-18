import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static boolean checkWordOrNum(String word) {
        if (word.isEmpty()) return false;

        for (int i = 0; i < word.length(); i++) {
            if (!Character.isDigit(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static StringBuilder deleteNumsInLine(String line){
        StringBuilder resLine = new StringBuilder();
        String[] words = line.split(" ");

        for (String word : words){
            char lastChar = word.charAt(word.length() - 1);
            if(lastChar == '.' || lastChar == ','){
                word = word.substring(0, word.length() - 1);

                if (!checkWordOrNum(word)){
                    resLine.append(word).append(lastChar).append(" ");
                } else {
                    if(resLine.length() > 0 && resLine.charAt(resLine.length() - 1) == ' '){
                        resLine.deleteCharAt(resLine.length() - 1);
                    }
                    resLine.append(lastChar).append(" ");
                }
                continue;
            }

            if (!checkWordOrNum(word)){
                resLine.append(word).append(" ");
            }

        }
        return resLine;
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
            StringBuilder resLine = deleteNumsInLine(line);

            lines.add(resLine);
        }
        String text = String.join("\n", lines);
        System.out.print(text);
    }
}