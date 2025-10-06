import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestFormat {

    @Test
    void testCheckWordOrNum(){
        String word1 = "test";
        assertFalse(ProcessingText.checkWordOrNum(word1));

        String word2 = "te23st";
        assertFalse(ProcessingText.checkWordOrNum(word2));

        String numWord = "123";
        assertTrue(ProcessingText.checkWordOrNum(numWord));

        String numWordWithPunctuation = "..123.,,";
        assertFalse(ProcessingText.checkWordOrNum(numWordWithPunctuation));
    }

    @Test
    void testDeleteNumsInLine(){
        String line = "Test line. Hello, World.";
        assertEquals(line, ProcessingText.deleteNumsInLine(line).toString());

        String lineWithNums = "Test 123 numLine. Hello, a4 World. 23. ,,244";
        assertEquals("Test numLine. Hello, a4 World..,,", ProcessingText.deleteNumsInLine(lineWithNums).toString());
    }
}
