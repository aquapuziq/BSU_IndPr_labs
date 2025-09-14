import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int calculateUpperDiagonallySum(int[][] matrix){
        int sum = 0;
        int dim = matrix.length;
        for (int i = 0; i < dim; i++) {
            sum += matrix[i][dim - 1 - i];
        }
        return sum;
    }

    public static int[][] resizeMatrix(int[][] matrix, int newDim){
        int newMatrix[][] = new int[newDim][newDim];
        for(int i = 0; i < newDim; ++i) {
            for(int j = 0; j < newDim; ++j) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    public static List<Integer> upperSums (List<Integer> sums, int[][] matrix){
        int dim = matrix.length;
        for(int i = 1; i < dim; i++){
            int[][] tmpMatrix = resizeMatrix(matrix, dim - i);
            int tmpSum = calculateUpperDiagonallySum(tmpMatrix);
            sums.add(tmpSum);
        }
        return sums;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        int topNum = 100;

        System.out.print("Введите размерность матрицы: ");
        int dim = in.nextInt();

        int[][] matrix = new int[dim][dim];

        for(int i = 0; i < dim; ++i) {
            for(int j = 0; j < dim; ++j) {
                matrix[i][j] = rand.nextInt(topNum);
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
        List<Integer> elementsSums = new ArrayList<>();
        elementsSums = upperSums(elementsSums, matrix);
        System.out.println(elementsSums);
    }
}