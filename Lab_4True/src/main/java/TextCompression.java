import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextCompression {
    public static void initialization(){
        Scanner in = new Scanner(System.in);
        List<String> lines = new ArrayList<>();

        System.out.println("Введите текст для сжатия:\n");
        while(true){
            String line = in.nextLine();
            if(line.isEmpty())
                break;
            String resLine = compressLine(line);
            lines.add(resLine);
        }

        String text = String.join("\n", lines);
        System.out.println("Текст после сжатия:\n");
        System.out.print(text);
    }
    public static String compressLine(String line) {
        if (line == null)
            throw new IllegalArgumentException("Строка не может быть пустой");
        int n = line.length();

        StringBuilder outLine = new StringBuilder(n);
        char prevSymbol = line.charAt(0);
        int count = 1;

        for (int i = 1; i < n; i++) {
            char c = line.charAt(i);
            if (c == prevSymbol) {
                count++;
            }
            else {
                if (count == 1) {
                    outLine.append(prevSymbol);
                } else {
                    outLine.append(prevSymbol).append(count);
                }
                prevSymbol = c;
                count = 1;
                }
        }
        if (count == 1) {
            outLine.append(prevSymbol);
        }
        else {
            outLine.append(prevSymbol).append(count);
        }
        return outLine.toString();
    }
}
