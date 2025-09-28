import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class DeleteComments {
    public static void initialization(){
        Scanner in = new Scanner(System.in);
        List<String> consoleCode = new ArrayList<>();

        System.out.println("Введите Java-код для форматирования:\n");
        while(true){
            String line = in.nextLine();
            if(line.isEmpty())
                break;
            consoleCode.add(line);
        }

        String code = String.join("\n", consoleCode);
        String codeWithoutComments = deleteComments(code);
        System.out.println("Текст после форматирования:\n");
        System.out.print(codeWithoutComments);
    }

    public static String deleteComments(String code){
        if (code == null){
            System.out.println("Код не был введен");
            return "";
        }

        code = code.replaceAll("\\s*//.*", "");
        code = code.replaceAll("/\\*[\\s\\S]*?\\*/", "");
        return code;
    }
}
