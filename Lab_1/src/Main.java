import java.util.InputMismatchException;
import java.util.Scanner;

class TaylorSeries {

    private static double Calc_eps(int k) {
        double eps = 1;
        for (int i = 0; i < k; i++) {
            eps /= 10;
        }
        return eps;
    }

    private static double Calc_pow(double x, int n) {
        double pow_x = 1;
        for (int i = 0; i < n; i++) {
            pow_x *= x;
        }
        return pow_x;
    }

    private static long Calc_fac(int n){
        long fac_x = 1;
        for (int i = 2; i <= n; i++) {
            fac_x *= i;
        }
        return fac_x;
    }

    private static double Calc_abs(double x) {
        if (x < 0){
            x *= (-1);
        }
        return x;
    }

    public static double Calc_sinh(double x, int k) {
        double shx = 0;
        double eps = Calc_eps(k);
        for (int i = 0; ; i++){
            int n = i * 2 + 1;
            long fac_n = Calc_fac(n);
            double term = Calc_pow(x, n) / fac_n;

        }
    }
}
public class Main {

    public static void main(String[] args) {
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
                System.out.println("Ошибка: введите корректное число");
                in.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: число должно быть натуральным");
            }
        }
    }
}