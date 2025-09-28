import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestDelete {

    @Test
    void testDeleteComments1() {
        String code1 = """
                public class DeleteComments { //инициализация класса
                    public static void initialization(){ // инициализация метода класса
                    }
                }
                """;
        String codeExpected1 = """
                public class DeleteComments {
                    public static void initialization(){
                    }
                }
                """;
        String codeActual1 = DeleteComments.deleteComments(code1);
        assertEquals(codeExpected1.trim(), codeActual1.trim());
    }
    @Test
    void testDeleteComments2() {
        String code2 = """
                public class Test {
                    public static void main(String[] args) {
                        System.out.println("Соболь, это яйцо!"); // а это комментарий
                        /* здоровый многострочный
                           комментарий */
                        System.out.println("LAB 4");
                        }
                        public int sum(int a, int b) {
                            return a + b;
                        }
                        /**   Это документационный комментарий
                              @param a Первое число.
                              @param b Второе число.
                              @return Сумма двух чисел.  */
                }
                """;

        String codeExpected2 = """
                public class Test {
                    public static void main(String[] args) {
                        System.out.println("Соболь, это яйцо!");

                        System.out.println("LAB 4");
                        }
                        public int sum(int a, int b) {
                            return a + b;
                        }

                }
                """;
        String codeActual2 = DeleteComments.deleteComments(code2);
        assertEquals(codeExpected2.trim().replaceAll("\\s+\n", "\n"),
                codeActual2.trim().replaceAll("\\s+\n", "\n"));
    }
}
