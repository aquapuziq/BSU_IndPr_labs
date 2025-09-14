import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        int topNum = 100;
        System.out.print("Введите число столбцов матрицы: ");
        int n = in.nextInt();
        System.out.print("Введите число строк матрицы: ");
        int m = in.nextInt();
        int[][] matrix = new int[m][n];

        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                matrix[i][j] = rand.nextInt(topNum);
                System.out.print(matrix[i][j] + " ");
            }

            System.out.print("\n");
        }

    }
}