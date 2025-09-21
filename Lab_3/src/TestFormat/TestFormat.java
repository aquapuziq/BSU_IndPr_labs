import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestFormat {

    @Test
    void testCheckWordOrNum(){
        String word = "test";
        assertFalse(Main.checkWordOrNum(word));

        String numWord = "123";
        assertTrue(Main.checkWordOrNum(numWord));

        String numWordWithPunctuation = "..123.,,";
        assertFalse(Main.checkWordOrNum(numWordWithPunctuation));
    }

    @Test
    void testDeleteNumsInLine(){
        String line = "Test line. Hello, World.";
        assertEquals(line, Main.deleteNumsInLine(line).toString());

        String lineWithNums = "Test 123 numLine. Hello, a4 World. 23. ,,244";
        String resLine = "Test numLine. Hello, a4 World..,,";
        assertEquals(resLine, Main.deleteNumsInLine(lineWithNums).toString());
    }
}
