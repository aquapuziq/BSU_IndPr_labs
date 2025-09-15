import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> lines = new ArrayList<>();

        System.out.println("Введите текст для форматировнаия:");
        while(true) {
            String line = in.nextLine();
            if (line.isEmpty()){
                break;
            }
            lines.add(line);
        }
        String text = String.join("\n", lines);
        System.out.print(text);
    }
}