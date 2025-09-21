import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcessingText {
    public static boolean checkWordOrNum(String word) {
        return word.matches("\\d+");
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

            if(checkWordOrNum(word)){
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

    public static void initialization(){
        Scanner in = new Scanner(System.in);
        List<StringBuilder> lines = new ArrayList<>();

        System.out.println("Введите текст для форматирования:");
        while(true) {
            String line = in.nextLine();
            if (line.isEmpty()){
                break;
            }

            StringBuilder resLine = ProcessingText.deleteNumsInLine(line);
            lines.add(resLine);
        }
        String text = String.join("\n", lines);
        System.out.println("Текст после форматирования: ");
        System.out.print(text);
    }
}
