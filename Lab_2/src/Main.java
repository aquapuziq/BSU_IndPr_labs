import java.util.*;
import java.lang.Math;
import java.util.InputMismatchException;

public class Main {

    public static int calculateDiagonallySum(int[][] matrix){
        if (matrix == null || matrix.length == 0) {
            throw new IllegalArgumentException("Матрица пустая или null");
        }

        int sum = 0;
        int dim = matrix.length;
        for (int i = 0; i < dim; i++) {
            sum += Math.abs(matrix[i][dim - 1 - i]);
        }
        return sum;
    }

    public static int[][] resizeUpMatrix(int[][] matrix, int newDim){
        int newMatrix[][] = new int[newDim][newDim];
        for(int i = 0; i < newDim; ++i) {
            for(int j = 0; j < newDim; ++j) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    public static int[][] resizeDownMatrix(int[][] matrix, int newDim){
        int newMatrix[][] = new int[newDim][newDim];
        for(int i = 0; i < newDim; ++i) {
            for(int j = 0; j < newDim; ++j) {
                newMatrix[i][j] = matrix[newDim - i][newDim - j];
            }
        }
        return newMatrix;
    }

    public static List<Integer> sumsOfDiagonals (List<Integer> sums, int[][] matrix){
        int dim = matrix.length;
        for(int i = 1; i < dim; i++){
            int[][] tmpUpMatrix = resizeUpMatrix(matrix, dim - i);
            int tmpSum = calculateDiagonallySum(tmpUpMatrix);
            sums.add(tmpSum);
        }

        int[][] tmpMatrix = resizeDownMatrix(matrix, dim - 1);
        for(int i = 1; i < dim; i++){
            int tmpDownMatrix[][] = resizeUpMatrix(tmpMatrix, dim - i);
            int tmpSum = calculateDiagonallySum(tmpDownMatrix);
            sums.add(tmpSum);
        }
        return sums;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        int dim = 0, topNum = 99, bottomNum = - 99;
        while (true) {
            try {
                System.out.print("Введите размерность матрицы: ");
                dim = in.nextInt();

                if (dim <= 0) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите число корректно\n");
                in.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: число размерности должно быть натуральным\n");
            }
        }
        int[][] matrix = new int[dim][dim];

        for(int i = 0; i < dim; ++i) {
            for(int j = 0; j < dim; ++j) {
                matrix[i][j] = rand.nextInt(topNum - bottomNum + 1) + bottomNum;
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }

        List<Integer> elementsSums = new ArrayList<>();
        elementsSums = sumsOfDiagonals(elementsSums, matrix);
        System.out.println("Суммы модулей элементов диагоналей," +
                " параллельных побочной диагонали: " + elementsSums);

        int minNum = Collections.min(elementsSums);
        System.out.println("Минимальная сумма модулей диагонали: " + minNum);
    }
}