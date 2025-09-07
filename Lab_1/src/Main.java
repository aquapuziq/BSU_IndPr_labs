import java.util.InputMismatchException;
import java.util.Scanner;

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
                System.out.println("Ошибка: число k должно быть натуральным");
            }
        }

        TaylorSeries sinhx = new TaylorSeries();
        double mySinhx = sinhx.mySinh(x, k);
        String res_1 = String.format("%.3f", mySinhx);
        System.out.println("Значение sinhx вычисленное приближенно: " + res_1);

        double stSinhx = Math.sinh(x);
        String res_2 = String.format("%.3f", stSinhx);
        System.out.print("Значение sinhx вычисленное через стандартную функцию Java: " + res_2);
    }
}