import java.util.InputMismatchException;
import java.util.Scanner;

public class TaylorSeries {

    public static void startProgram(){
        int k = 0; float x = 0;
        Scanner in = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Введите значение k: ");
                k = in.nextInt();
                System.out.print("Введите значение x: ");
                x = in.nextFloat();
                if (k <= 0) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите число корректно\n");
                in.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: число k должно быть натуральным\n");
            }
        }

        double mySinhx = mySinh(x, k);
        String res_1 = String.format("%.3f", mySinhx);
        System.out.println("Значение sinhx вычисленное приближенно: " + res_1);

        double stSinhx = Math.sinh(x);
        String res_2 = String.format("%.3f", stSinhx);
        System.out.print("Значение sinhx вычисленное через стандартную функцию Java: " + res_2);
    }

    private static double calculationEpsilon(int k) {
        double epsilon = 1;
        for (int i = 0; i < k; i++) {
            epsilon /= 10;
        }
        return epsilon;
    }

    private static double pow(double x, int n) {
        double pow_x = 1;
        for (int i = 0; i < n; i++) {
            pow_x *= x;
        }
        return pow_x;
    }

    private static long calculationFactorial(int n){
        long factorial_x = 1;
        for (int i = 2; i <= n; i++) {
            factorial_x *= i;
        }
        return factorial_x;
    }

    private static double abs(double x) {
        if (x < 0){
            x *= (-1);
        }
        return x;
    }

    private static double mySinh(double x, int k) {
        double shx = 0;
        double epsilon = calculationEpsilon(k);
        for (int i = 0; ; i++){
            int n = i * 2 + 1;
            long factorial_n = calculationFactorial(n);
            double term = pow(x, n) / factorial_n;
            double term_abs = abs(term);

            if (term_abs > Double.MAX_VALUE){
                System.out.println("Ошибка: при вычислении очередного слагаемого, его значение" +
                        "\nвышло за пределы размерности типа данных double\n");

                shx += term;
                break;
            }
            if (term_abs < epsilon){
                break;
            }
            shx += term;
        }
        return shx;
    }
}
