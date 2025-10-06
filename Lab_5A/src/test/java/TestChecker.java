import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TestChecker {

    @Test
    void testDefaultCase() throws IOException {
        Path tmpFile = Files.createTempFile("test", ".txt");
        String text = "Egg! BAlls hi. Hello WoRLd. I Hi like our world";

        Files.writeString(tmpFile, text);
        Set<String> result = FileChecker.extractUniqueWords(tmpFile.toString());

        assertEquals(Set.of("egg", "balls", "hello", "i", "like", "our", "world", "hi"), result);
    }

    @Test
    void testEmptyFile() throws IOException {
        Path tmpFile = Files.createTempFile("empty", ".txt");
        Files.writeString(tmpFile, "");

        Set<String> result = FileChecker.extractUniqueWords(tmpFile.toString());

        assertTrue(result.isEmpty());
    }

    @Test
    void testNumsAndSym() throws IOException {
        Path tmpFile = Files.createTempFile("symbols", ".txt");
        String text = "Hi8 our egg is4 great! #morgen.";
        Files.writeString(tmpFile, text);

        Set<String> result = FileChecker.extractUniqueWords(tmpFile.toString());

        assertEquals(Set.of("hi", "is", "great", "our", "egg", "morgen"), result);
    }
}

