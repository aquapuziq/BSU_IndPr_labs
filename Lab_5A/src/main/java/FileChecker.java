import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FileChecker {
    public static void initialization() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        try {
            Set<String> uniqueWords = extractUniqueWords(filePath);

            System.out.println("Найденные уникальные слова " + uniqueWords.size() + ":");
            for (String word : uniqueWords) {
                System.out.println(word);
            }

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public static Set<String> extractUniqueWords(String filePath) throws IOException {
        String line = Files.readString(Path.of(filePath));
        String[] words = line.split("[^a-zA-Z]+");
        Set<String> uniqueWords = new HashSet<>();

        for (String word : words) {
            if (!word.isEmpty()) {
                uniqueWords.add(word.toLowerCase());
            }
        }

        return uniqueWords;
    }
}
