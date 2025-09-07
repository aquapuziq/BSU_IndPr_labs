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

    private static double Calc_fac(int n){
        double fac_x = 1;
        for (int i = 2; i <= n; i++) {
            fac_x *= i;
        }
        return fac_x;
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