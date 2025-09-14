import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestMatrix {

    @Test
    void testCalculateDiagonallySum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        assertEquals(15, Main.calculateDiagonallySum(matrix));
    }

    @Test
    void testResizeUpMatrix() {
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };
        int[][] resized = Main.resizeUpMatrix(matrix, 1);
        assertArrayEquals(new int[][]{{1}}, resized);
    }

    @Test
    void testResizeDownMatrix() {
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };
        int[][] resized = Main.resizeDownMatrix(matrix, 1);
        assertArrayEquals(new int[][]{{4}}, resized);
    }

    @Test
    void testSumsOfDiagonals() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        List<Integer> sums = new ArrayList<>();
        sums = Main.sumsOfDiagonals(sums, matrix);
        List<Integer> expected = Arrays.asList(6, 1, 14, 9);
        assertTrue(sums.containsAll(expected));
    }

    @Test
    void testEmptyMatrixThrows() {
        int[][] empty = new int[0][0];
        assertThrows(IllegalArgumentException.class,
                () -> Main.calculateDiagonallySum(empty));
    }
}
